package Modellayer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Player_test {
	Player player;

	@Before
	public void setUp(){
		player = new Player("alpha");
	}
	
	@Test
	public void test_getName_returnNotNullString(){
		assertEquals("alpha", player.getName());
	}
	
	@Test
	public void test_getPoints_returnTwo(){
		player.addPoints(2);
		assertEquals(2, player.getPoints());
	}
}

/*
 * package Modellayer;

public class Player {

	private int points;
	private String name;
	
	public Player(final String name) {
		this.name = name;
		points = 0;
	}
	
	
	public int getPoints(){
		return points;
	}
	
	public String getName(){
		return name;
	}
	 public void addPoints(int amount){
		 points += amount;
	 }
}
*/
