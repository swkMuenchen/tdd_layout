package layoutproject;


public class Size {

	private int width;
	private int height;
	static final Size ZERO_SIZE = new Size(0, 0);

		
	public Size(int width, int height) {
		super();
		this.width = width;
		this.height = height;
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
	
	
}
