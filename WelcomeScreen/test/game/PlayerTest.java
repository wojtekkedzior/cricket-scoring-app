package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import common.BattingStatus;
import common.BowlingStatus;


public class PlayerTest {

	private Player player;

	@Before
	public void setUp() {
		player = new Player("Wojtek", 1);
	}

	@Test
	public void testGetId() {
		assertEquals(1, player.getId());
	}

	@Test
	public void testSetId() {
		assertEquals(1, player.getId());
		player.setId(2);
		assertEquals(2, player.getId());
	}

	@Test
	public void testPlayer() {
		Player p = new Player("Player", 9);
		assertEquals(9, p.getId());
		assertEquals("Player", p.getName());
	}

	@Test
	public void testGetBattingScore() {
		assertNotNull(player.getBattingScore());
	}

	@Test
	public void testGetBowlingScore() {
		assertNotNull(player.getBowlingScore());
	}

	@Test
	public void testGetName() {
		assertEquals("Wojtek", player.getName());
	}
	
	@Test
	public void testGetBattingStatus() {
		assertTrue(player.getBattingStatus().equals(BattingStatus.Available));
		player.setBattingStatus(BattingStatus.Out);
		assertTrue(player.getBattingStatus().equals(BattingStatus.Out));
	}
	
	@Test
	public void testGetBowlingStatus() {
		assertTrue(player.getBowlingStatus().equals(BowlingStatus.Available));
		player.setBowlingStatus(BowlingStatus.BowledOut);
		assertTrue(player.getBowlingStatus().equals(BowlingStatus.BowledOut));
	}
	
	@Test
	public void testHashCode() {
		assertTrue(player.hashCode() != 0);
		
		player.setBattingStatus(null);
		player.setBowlingStatus(null);
		assertTrue(player.hashCode() != 0);
		
		player = new Player(null, 1);
		assertTrue(player.hashCode() != 0);
	}
	
	@Test
	public void testEquals() {
		assertTrue(player.equals(player));
		assertFalse(player.equals(null));
		assertFalse(player.equals(""));
		
		Player other = new Player("Wojtek", 1);
		other.setBattingStatus(BattingStatus.Out);
		other.setBowlingStatus(BowlingStatus.BowledOut);
		assertFalse(player.equals(other));
		
		other.setBattingStatus(player.getBattingStatus());
		assertFalse(player.equals(other));
		
		other = new Player("Wojtek", 1);
		other.setId(4);
		assertFalse(player.equals(other));
		
		other = new Player("Wojtek", 1);
		other.setId(1);
		assertTrue(player.equals(other));
		
		player = new Player(null, 1);
		assertFalse(player.equals(other));
		
		other = new Player(null, 1);
		assertTrue(player.equals(other));
		
		player = new Player("Wojtek", 1);
		other = new Player("WojtekZZZZZ", 1);
		assertFalse(player.equals(other));
	}
	
	@Test
	public void testToString() {
		assertEquals("Wojtek", player.toString());
	}
}
