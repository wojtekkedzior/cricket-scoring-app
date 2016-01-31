package beans;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import common.DismisalType;

public class BattingWicketBeanTest {

	private BattingWicketBean wicketBean;

	@Before
	public void setUp(){
		wicketBean = new BattingWicketBean(1, DismisalType.BOWLED, 2, 3, 9);

		assertEquals(Integer.valueOf(1), wicketBean.getBowlerId());
		assertEquals(DismisalType.BOWLED, wicketBean.getDismisalType());
		assertEquals(2, wicketBean.getOverNumber());
		assertEquals(3, wicketBean.getBallNumber());

		wicketBean = new BattingWicketBean(1, DismisalType.RUNOUT, 1, 1, 9);
	}

	@Test
	public void testBattingWicketBeanIntDismisalTypeIntIntInt() {
		wicketBean = new BattingWicketBean(1, DismisalType.BOWLED, 2, 3, 4);

		assertEquals(Integer.valueOf(1), wicketBean.getBowlerId());
		assertEquals(DismisalType.BOWLED, wicketBean.getDismisalType());
		assertEquals(2, wicketBean.getOverNumber());
		assertEquals(3, wicketBean.getBallNumber());
		assertEquals(4, wicketBean.getFielderId());
	}

	@Test
	public void testGetDismisalType() {
		assertEquals(DismisalType.RUNOUT, wicketBean.getDismisalType());
	}

	@Test
	public void testSetDismisalType() {
		assertEquals(DismisalType.RUNOUT, wicketBean.getDismisalType());
		wicketBean.setDismisalType(DismisalType.CAUGHT);
		assertEquals(DismisalType.CAUGHT, wicketBean.getDismisalType());
	}

	@Test
	public void testGetBowlerId() {
		assertEquals(Integer.valueOf(1), wicketBean.getBowlerId());
	}

	@Test
	public void testSetBowlerId() {
		assertEquals(Integer.valueOf(1), wicketBean.getBowlerId());
		wicketBean.setBowlerId(3);
		assertEquals(Integer.valueOf(3), wicketBean.getBowlerId());
	}

	@Test
	public void testGetOverNumber() {
		assertEquals(1, wicketBean.getOverNumber());
	}

	@Test
	public void testSetOverNumber() {
		assertEquals(1, wicketBean.getOverNumber());
		wicketBean.setOverNumber(5);
		assertEquals(5, wicketBean.getOverNumber());
	}

	@Test
	public void testGetBallNumber() {
		assertEquals(1, wicketBean.getBallNumber());
	}

	@Test
	public void testSetBallNumber() {
		assertEquals(1, wicketBean.getBallNumber());
		wicketBean.setBallNumber(4);
		assertEquals(4, wicketBean.getBallNumber());
	}

	@Test
	public void testGetFielderId() {
		assertEquals(9, wicketBean.getFielderId());
	}

	@Test
	public void testSetFielderId() {
		assertEquals(9, wicketBean.getFielderId());
		wicketBean.setFielderId(10);
		assertEquals(10, wicketBean.getFielderId());
	}

}
