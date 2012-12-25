package layoutproject;

public interface MapNode extends Node {

	public void setContent(ContentNode content);

	public ContentNode getContent();

	public Iterable<MapNode> getChildren();

	public MapNode getChild(int index);

}