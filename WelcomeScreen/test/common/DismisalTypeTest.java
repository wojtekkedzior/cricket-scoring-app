package common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DismisalTypeTest {

	@Test
	public void testGet() {
		assertEquals(DismisalType.BOWLED, DismisalType.get(DismisalType.BOWLED.getDismissalTypeName()));
		assertEquals(DismisalType.RUNOUT, DismisalType.get(DismisalType.RUNOUT.getDismissalTypeName()));
		assertEquals(DismisalType.NON_STRIKER_RUNOUT, DismisalType.get(DismisalType.NON_STRIKER_RUNOUT.getDismissalTypeName()));
		assertEquals(DismisalType.CAUGHT, DismisalType.get(DismisalType.CAUGHT.getDismissalTypeName()));
		assertEquals(DismisalType.STUMPED, DismisalType.get(DismisalType.STUMPED.getDismissalTypeName()));
		assertEquals(DismisalType.LBW, DismisalType.get(DismisalType.LBW.getDismissalTypeName()));
		assertEquals(DismisalType.HIT_WICKET, DismisalType.get(DismisalType.HIT_WICKET.getDismissalTypeName()));
	}
}
