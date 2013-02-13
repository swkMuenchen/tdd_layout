package layoutproject;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class NodeContentTest {
	@Test
	public void initalNodeContent_hasSize0_0() {
		final NodeContent nodeContent = new NodeContent();
		assertThat(nodeContent.getSize(), is(new Size(0, 0)));
	}
}
