package group2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Staff_Test {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Staff staff = new Staff("Alex", "alexmail", "12345678", "Elm Street", "1234", 1);
		
		// change 3
		assertEquals("1234", staff.getPassword());
		//fail("Not yet implemented");
	}

}
