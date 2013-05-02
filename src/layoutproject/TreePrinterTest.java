package layoutproject;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import layoutproject.TreePrinter.HtmlColor;

import org.junit.Test;

public class TreePrinterTest {
    private static final int ZOOM = 10;
    private static final int INITIAL_COLOR = 192;
    private static final String CANVAS_ID = "canvasId";
    private static final String CANVAS_CLASS = "canvasClass";
    private TreePrinter treePrinter;
    private String fileName = "x.html";

    public TreePrinterTest() {
        treePrinter = new TreePrinter();
        treePrinter.setCanvasId(CANVAS_ID);
        treePrinter.setCanvasClass(CANVAS_CLASS);
        treePrinter.setZoom(ZOOM);
        treePrinter.setOffset(new Position(10, 10));
    }

    //
    // utilities
    //
    @Test
    public void util_tag_wraps_content_in_xml_tag() {
        assertEquals("<x>abc</x>", TreePrinter.tag("x", "abc"));
    }

    @Test
    public void util_joinLines_joins_with_newlines() {
        assertEquals("", TreePrinter.joinLines());
        assertEquals("x", TreePrinter.joinLines("x"));
        assertEquals("x\ny", TreePrinter.joinLines("x", "y"));
    }

    @Test
    public void util_htmlGrey_generates_correct_html_color_strings() {
        assertEquals("#000000", HtmlColor.grey(0).toString());
        assertEquals("#FFFFFF", HtmlColor.grey(255).toString());
    }

    //
    // html
    //
    @Test
    public void canvasElement_has_configurable_id_and_class() {
        assertEquals("test setup broken", canvasElement(CANVAS_ID, CANVAS_CLASS), treePrinter.canvasElement());
        treePrinter.setCanvasId("blaId");
        treePrinter.setCanvasClass("blaClass");
        assertEquals(canvasElement("blaId", "blaClass"), treePrinter.canvasElement());
    }

    @Test
    public void html_for_null_tree_contains_canvas_and_no_script() {
        assertEquals("<!DOCTYPE html><html><body>\n" + treePrinter.canvasElement() + "\n</body></html>",
            treePrinter.htmlDocument(null));
    }

    @Test
    public void writeHtmlFile_generates_file() throws Exception {
        treePrinter.writeHtmlFile(fileName, null);
        final String actual = "<!DOCTYPE html><html><body>\n" + treePrinter.canvasElement() + "\n</body></html>";
        assertEquals(actual, fileToString(fileName));
    }

    private String canvasElement(String canvasId, String canvasClass) {
        return "<canvas id=\"" + canvasId + "\" class=\"" + canvasClass + "\" width=\"200\" height=\"200\">"
                + TreePrinter.CANVAS_NOT_SUPPORTED_WARNING + "</canvas>";
    }

    // FIXME: use commons 1.6 compatible FileUtils.readFileToString()
    private static String fileToString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    //
    // script
    //
    @Test
    public void script_for_null_tree_is_empty() {
        Tree tree = new StubTree(Size.ZERO_SIZE);
        assertEquals("", treePrinter.script(tree));
    }

    @Test
    public void script_for_hidden_node_is_empty() {
        Tree tree = new StubTree(Size.ZERO_SIZE);
        assertEquals("", treePrinter.script(tree));
    }

    @Test
    public void script_for_node_at_origin() {
        final Tree tree = new StubTree(new Size(1, 2));
        final HtmlColor color = HtmlColor.grey(INITIAL_COLOR);
        // offset = 10, 10
        // zoom = 10
        assertEquals(TreePrinter.tag("script", //
            TreePrinter.joinLines( //
                treePrinter.scriptIntro() //
                , "ctx.fillStyle=\"" + color + "\";" //
                , "ctx.fillRect(100, 100, 10, 20);" //
                , "ctx.strokeStyle=\"" + TreePrinter.ARC_COLOR + "\";" //
                , "ctx.beginPath();" //
                , "ctx.arc(100, 100, " + TreePrinter.ARC_RADIUS + ", 0, Math.PI*2, true); " //
                , "ctx.stroke();" //
            )), treePrinter.script(tree));
    }

    @Test
    public void script_for_repositioned_node() {
        final Position position = new Position(1, 2);
        final Size size = new Size(3, 4);
        final Tree tree = new StubTree(size);
        tree.setPosition(position);
        final HtmlColor color = HtmlColor.grey(INITIAL_COLOR);
        // offset = 10, 10
        // zoom = 10
        assertEquals(TreePrinter.tag("script", //
            TreePrinter.joinLines( //
                treePrinter.scriptIntro() //
                , "ctx.fillStyle=\"" + color + "\";" //
                , "ctx.fillRect(110, 120, 30, 40);" //
                , "ctx.strokeStyle=\"" + TreePrinter.ARC_COLOR + "\";" //
                , "ctx.beginPath();" //
                , "ctx.arc(110, 120, " + TreePrinter.ARC_RADIUS + ", 0, Math.PI*2, true); " //
                , "ctx.stroke();" //
            )), treePrinter.script(tree));
    }

    //-4        CC
    //-3        CC
    //-2        CC
    //-1          
    // 0 NN-----  
    @Test
    public void script_for_node_with_child() {
        final LayoutingTree tree = new LayoutingTree();
        tree.setNode(new Node(new Size(2, 1)));
        final StubTree child = new StubTree(new Size(2, 3));
        tree.addChild(child);
        tree.layoutChildren();
        final HtmlColor color = HtmlColor.grey(INITIAL_COLOR);
        treePrinter.writeHtmlFile("treePrinterTest.html", tree);
        // offset = 10, 10
        // zoom = 10
        // parent: [0, 0, 9, 5]
        // child: [6, -4, 2, 3]
        int y0 = ZOOM * (treePrinter.getOffset().getY() - tree.getSize().getHeight());
        assertEquals(TreePrinter.tag("script", //
            TreePrinter.joinLines( //
                treePrinter.scriptIntro() //
                // bounding box
                , "ctx.fillStyle=\"" + color + "\";" //
                , "ctx.fillRect(100, " + y0 + ", 90, 50);" //
                , "ctx.strokeStyle=\"" + TreePrinter.ARC_COLOR + "\";" //
                , "ctx.beginPath();" //
                , "ctx.arc(100, " + y0 + ", " + TreePrinter.ARC_RADIUS + ", 0, Math.PI*2, true); " //
                , "ctx.stroke();" //
                // node
                , "ctx.fillStyle=\"" + color.darker() + "\";" //
                , "ctx.fillRect(100, " + y0 + ", 20, 10);" //
                , "ctx.strokeStyle=\"" + TreePrinter.ARC_COLOR + "\";" //
                , "ctx.beginPath();" //
                , "ctx.arc(100, " + y0 + ", " + TreePrinter.ARC_RADIUS + ", 0, Math.PI*2, true); " //
                , "ctx.stroke();" //
                // child
                , "ctx.fillStyle=\"" + color.darker() + "\";" //
                , "ctx.fillRect(160, 60, 20, 30);" //
                , "ctx.strokeStyle=\"" + TreePrinter.ARC_COLOR + "\";" //
                , "ctx.beginPath();" //
                , "ctx.arc(160, 60, " + TreePrinter.ARC_RADIUS + ", 0, Math.PI*2, true); " //
                , "ctx.stroke();" //
            )), treePrinter.script(tree));
    }
}
