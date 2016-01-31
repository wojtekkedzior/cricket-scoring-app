package beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BattingOverBeanTest {

	private static final int X_COORDINATE = 123;
	private static final int Y_COORDINATE = 456;
	private BattingOverBean bean;

	@Before
	public void setUp() {
		bean = new BattingOverBean(1, 1);
	}

	@Test
	public void testBattingOverBean() {
		bean = new BattingOverBean(5, 6);
		assertEquals(5, bean.getOverNumber());
		assertEquals(6, bean.getBowlerId());
	}

	@Test
	public void testGetRunsForOver() {
		assertEquals(0, bean.getRunsForOver());

		bean.addRun(1, 0, 0);
		assertEquals(1, bean.getRunsForOver());

		bean.addRun(2, 0, 0);
		assertEquals(3, bean.getRunsForOver());

		bean.addRun(3, 0, 0);
		assertEquals(6, bean.getRunsForOver());

		bean.addRun(4, 0, 0);
		assertEquals(10, bean.getRunsForOver());

		bean.addRun(5, 0, 0);
		assertEquals(15, bean.getRunsForOver());

		bean.addRun(6, 0, 0);
		assertEquals(21, bean.getRunsForOver());

		// Test batsman facing an extra
		bean.addRun(-1, 0, 0);
		assertEquals(21, bean.getRunsForOver());

	}

	@Test
	public void testAddRun() {
		assertEquals(0, bean.getRunsForOver());
		bean.addRun(1, 0, 0);
		assertEquals(1, bean.getRunsForOver());
	}

	@Test
	public void testAddRunWithInt() {
		assertEquals(0, bean.getRunsForOver());
		bean.addRun(1);
		assertEquals(1, bean.getRunsForOver());
	}

	@Test
	public void testGetOverNumber() {
		assertEquals(1, bean.getOverNumber());
	}

	@Test
	public void testGetBowlerId() {
		assertEquals(1, bean.getBowlerId());
	}

	@Test
	public void testGetBallsFaced() {
		assertEquals(0, bean.getBallsFaced().intValue());

		bean.addRun(1, 0, 0);
		assertEquals(1, bean.getBallsFaced().intValue());

		bean.addRun(1, 0, 0);
		assertEquals(2, bean.getBallsFaced().intValue());

		// Batsman just faced an extra delivery
		bean.addRun(-1, 0, 0);
		assertEquals(3, bean.getBallsFaced().intValue());

		bean.addRun(1, 0, 0);
		assertEquals(4, bean.getBallsFaced().intValue());
	}

	@Test
	public void testGetFours() {
		assertTrue(bean.getNumberOfRuns(4) == 0);
		bean.addRun(3);
		bean.addRun(4);
		bean.addRun(4);
		bean.addRun(5);
		bean.addRun(6);

		assertTrue(bean.getNumberOfRuns(4) == 2);
	}

	@Test
	public void testGetSixes() {
		assertTrue(bean.getNumberOfRuns(6) == 0);
		bean.addRun(3);
		bean.addRun(4);
		bean.addRun(5);
		bean.addRun(6);
		bean.addRun(6);

		assertTrue(bean.getNumberOfRuns(6) == 2);
	}

	@Test
	public void testGetRawX() {
		bean.addRun(3);
		Coordinate coordinate = bean.getCordinatesOfAllRuns().get(0);
		assertTrue(coordinate.getRawX() == 0);
		assertTrue(coordinate.getRun() == 3);

		bean.addRun(1, X_COORDINATE, Y_COORDINATE);
		coordinate = bean.getCordinatesOfAllRuns().get(1);
		assertTrue(coordinate.getRawX() == X_COORDINATE);
		assertTrue(coordinate.getRun() == 1);
	}

	@Test
	public void testGetRawY() {
		bean.addRun(3);
		Coordinate coordinate = bean.getCordinatesOfAllRuns().get(0);
		assertTrue(coordinate.getRawY() == 0);
		assertTrue(coordinate.getRun() == 3);

		bean.addRun(1, X_COORDINATE, Y_COORDINATE);
		coordinate = bean.getCordinatesOfAllRuns().get(1);
		assertTrue(coordinate.getRawY() == Y_COORDINATE);
		assertTrue(coordinate.getRun() == 1);
	}
}
