package layoutproject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TreePrinter {
    public static class HtmlColor {
        private static final double FACTOR_DARKER = 0.7;
        private int r;
        private int g;
        private int b;

        public HtmlColor(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public static HtmlColor grey(int value) {
            return new HtmlColor(value, value, value);
        }

        @Override
        public String toString() {
            return String.format("#%02X%02X%02X", r, g, b);
        }

        public HtmlColor darker() {
            return new HtmlColor((int) (r * 0.7), (int) (r * 0.7), (int) (r * FACTOR_DARKER));
        }
    }

    public static final String CANVAS_NOT_SUPPORTED_WARNING = "Your browser does not support the HTML5 canvas tag.";
    static final HtmlColor ARC_COLOR = new HtmlColor(0, 0, 255);
    static final int ARC_RADIUS = 3;
    private String canvasId = "treeCanvas";
    private String canvasClass = "treeCanvas";
    private double zoom = 10;
    private Position offset = new Position(5, 5);

    public String canvasElement() {
        return "<canvas id=\"" + getCanvasId() + "\" class=\"" + getCanvasClass() + "\" width=\"200\" height=\"200\">"
                + TreePrinter.CANVAS_NOT_SUPPORTED_WARNING + "</canvas>";
    }

    public String getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(String canvasId) {
        this.canvasId = canvasId;
    }

    public String getCanvasClass() {
        return canvasClass;
    }

    public void setCanvasClass(String canvasClass) {
        this.canvasClass = canvasClass;
    }

    public String script(Tree tree) {
        if (tree != null && !tree.getSize().equals(Size.ZERO_SIZE)) {
            HtmlColor htmlColor = HtmlColor.grey(192);
            return tag("script", joinLines( //
                scriptIntro() //
                , scriptForTreeRecursive(tree, offset, htmlColor)));
        }
        return "";
    }

    private String scriptForTreeRecursive(Tree tree, Position offset, HtmlColor htmlColor) {
        final StringBuilder stringBuilder = new StringBuilder();
        final Position treePosition = position(tree).moveTo(offset.getX(), offset.getY());
        stringBuilder.append(rectangle(treePosition, tree.getSize(), htmlColor));
        final Node node = node(tree);
        if (node != null) {
            stringBuilder.append("\n");
            stringBuilder.append(rectangle(treePosition, node.getSize(), htmlColor.darker()));
        }
        for (Tree childTree : children(tree)) {
            stringBuilder.append("\n");
            stringBuilder.append(scriptForTreeRecursive(childTree, offset, htmlColor.darker()));
        }
        return stringBuilder.toString();
    }

    private Node node(Tree tree) {
        try {
            final Field[] fields = tree.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals("node")) {
                    field.setAccessible(true);
                    return (Node) field.get(tree);
                }
            }
            return null;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Tree> children(Tree tree) {
        try {
            final ArrayList<Tree> children = new ArrayList<Tree>();
            final Field[] fields = tree.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getName().equals("child") || field.getName().equals("childTree")) {
                    field.setAccessible(true);
                    children.add((Tree) field.get(tree));
                }
                else if (field.getName().equals("children")) {
                    field.setAccessible(true);
                    children.addAll((Collection<? extends Tree>) field.get(tree));
                }
            }
            return children;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Position position(Tree tree) {
        if (tree.getPosition() != null)
            return tree.getPosition();
        return new Position(0, -tree.getSize().getHeight());
    }

    private String rectangle(Position position, Size size, HtmlColor htmlColor) {
        int x = zoom(position.getX());
        int y = zoom(position.getY());
        int width = zoom(size.getWidth());
        int height = zoom(size.getHeight());
        return joinLines( //
            "ctx.fillStyle=\"" + htmlColor + "\";" //
            , "ctx.fillRect(" + x + ", " + y + ", " + width + ", " + height + ");" //
            , "ctx.strokeStyle=\"" + ARC_COLOR + "\";" //
            , "ctx.beginPath();" //
            , "ctx.arc(" + x + ", " + y + ", " + ARC_RADIUS + ", 0, Math.PI*2, true); " //
            , "ctx.stroke();" //
        );
    }

    private int zoom(final int x) {
        return (int) (x * zoom);
    }

    String scriptIntro() {
        int halfLength = zoom(Math.min(offset.getX(), offset.getY()));
        final int offsetX = zoom(offset.getX());
        final int offsetY = zoom(offset.getY());
        return joinLines( //
            "var c=document.getElementById(\"" + canvasId + "\");" //
            , "var ctx=c.getContext(\"2d\");" //
            , "ctx.strokeStyle=\"#00FF00\";" //
            , "ctx.translate(0.5, 0.5);" //
            , "ctx.lineWidth = 1;" //
            , "ctx.beginPath();" //
            , "ctx.moveTo(" + (offsetX - halfLength) + "," + offsetY + ");" //
            , "ctx.lineTo(" + (offsetX + halfLength) + "," + offsetY + ");" //
            , "ctx.stroke();" //
            , "ctx.beginPath();" //
            , "ctx.moveTo(" + offsetX + "," + (offsetY - halfLength) + ");" //
            , "ctx.lineTo(" + offsetX + "," + (offsetY + halfLength) + ");" //
            , "ctx.stroke();");
    }

    public String htmlDocument(Tree tree) {
        return "<!DOCTYPE html><html><body>\n" + canvasElement() + "\n" + script(tree) + "</body></html>";
    }

    // FIXME: use StringUtils or guava Joiner instead
    public static String joinLines(String... lines) {
        final String separator = "\n";
        final StringBuffer buf = new StringBuffer();
        for (int i = 0; i < lines.length; i++) {
            if (i > 0) {
                buf.append(separator);
            }
            buf.append(lines[i]);
        }
        return buf.toString();
    }

    public static String tag(String elementName, String content) {
        return "<" + elementName + ">" + content + "</" + elementName + ">";
    }

    public void writeHtmlFile(String fileName, Tree tree) {
        if (fileName == null)
            throw new IllegalArgumentException("fileName is null");
        final String html = htmlDocument(tree);
        // FIXME: use FileUtils
        writeStringToFile(fileName, html);
    }

    private static void writeStringToFile(final String fileName, final String html) {
        BufferedWriter out = null;
        try {
            // don't care for encoding since we are not using special chars
            out = new BufferedWriter(new FileWriter(fileName));
            out.write(html);
            out.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (out != null)
                try {
                    out.close();
                }
                catch (IOException e) {
                    // can't help it
                }
        }
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public Position getOffset() {
        return offset;
    }

    public void setOffset(Position offsetPosition) {
        this.offset = offsetPosition;
    }
}
