package layoutproject;

public class Size {

	final private int width;
	final private int height;
	static final Size ZERO_SIZE = new Size(0, 0);

	public Size(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Size addSpaceAround(int spaceAround) {
		return new Size(width + 2 * spaceAround, height + 2 * spaceAround);
	}

	@Override
	public String toString() {
		return "Size [width=" + width + ", height=" + height + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Size other = (Size) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	public Size add(int deltaX, int deltaY) {
		return new Size(deltaX + width, deltaY + height);
	}

	public Size add(Size size) {
		return new Size(size.getWidth() + width, size.getHeight() + height);
	}

	public Size extendHorizontally(int widthIncrement) {
		return new Size(width + widthIncrement, height);
	}

}
