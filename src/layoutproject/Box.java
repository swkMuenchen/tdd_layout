/*
 *  Freeplane - mind map editor
 *  Copyright (C) 2013 Dimitry
 *
 *  This file author is Dimitry
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package layoutproject;

/**
 * @author Dimitry Polivaev
 * 20.02.2013
 */
public class Box {
	private Size size;
	private Position position;

	public Box(Size size, Position position) {
		this.size = size;
		this.position = position;
    }

	public Box addSpaceAround(int spaceAround) {
	    return new Box(size.addSpaceAround(spaceAround), position.moveTo(-spaceAround, -spaceAround));
    }

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((position == null) ? 0 : position.hashCode());
	    result = prime * result + ((size == null) ? 0 : size.hashCode());
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
	    Box other = (Box) obj;
	    if (position == null) {
		    if (other.position != null)
			    return false;
	    }
	    else if (!position.equals(other.position))
		    return false;
	    if (size == null) {
		    if (other.size != null)
			    return false;
	    }
	    else if (!size.equals(other.size))
		    return false;
	    return true;
    }

	@Override
    public String toString() {
	    return "Box [size=" + size + ", position=" + position + "]";
    }
	
	
}
