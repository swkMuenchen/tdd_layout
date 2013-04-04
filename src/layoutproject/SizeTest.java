package layoutproject;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SizeTest {

	@Test
	public void extendSize1x1HorizontallyBy2_is_3x1() {
		assertThat(new Size(1, 1).extendHorizontally(2), is(new Size(3, 1)));
	}

}
