package conway;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import conway.device.ConwayInputMock;

//By default, JUnit comes with a bundled copy of hamcrest-core

public class conway25JavaTest {
private static ConwayInputMock cim;

	private static Life life;
	private static LifeController cc;
	
	@BeforeClass
	public static void setup() {
		System.out.println("setup");
    	//configureTheSystem
        life           = new Life( 3,3 );
        cc   = new LifeController(life);   
        //cim = new ConwayInputMock(cc,life);		
	}
	
	@After
	public void down() {
		System.out.println("down");
	}
	
	@Test
	public void test1() {
		System.out.println("ok test1");
		
		life.switchCellState( 1, 0 );
		life.switchCellState( 1, 1 );
		life.switchCellState( 1, 2 );
		
		life.computeNextGen();
		
		Grid newGrid = new Grid(3, 3);
		newGrid.getCellAt(0, 1).setValue(CellValue.ALIVE);
		newGrid.getCellAt(1, 1).setValue(CellValue.ALIVE);
		newGrid.getCellAt(2, 1).setValue(CellValue.ALIVE);
		
		assertTrue(life.getGrid().equals(newGrid));		
		
		//cim.simulateUserControl();
		//assert ??
	}
	
	@Test
	public void yyy() {
		System.out.println("ok yyy");
	}
}

//Con gradlew test, controllare - logs/apptest.log - build/reports/tests/test/index.html

