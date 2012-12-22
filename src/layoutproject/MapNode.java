package layoutproject;


public interface MapNode extends Node {

	public abstract void setContent(ContentNode content);

	public abstract ContentNode getContent();

	public abstract Iterable<MapNode> getChildren();
	MapNode getChild(int index);

}