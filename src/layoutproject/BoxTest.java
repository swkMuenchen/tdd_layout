package layoutproject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class BoxTest {

	@Test
	public void boxWithAddedSpaceAround(){
		final int space = 1;
		final Box box = Box.EMTPY_AT_ORIGIN.addSpaceAround(space);
		final Size expectedSize = new Size(2*space, 2*space);
		final Position expectedPosition = new Position(-space, -space);
		assertThat(box, is(new Box(expectedSize, expectedPosition)));
	}

}
