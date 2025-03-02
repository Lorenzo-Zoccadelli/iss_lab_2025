package conway;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LifeTest {
	private Life genericConfig;
	private Life toEmptyConfig;
	private Life periodicConfig;

	@Before
	public void init() {
		genericConfig = new Life(6, 6);
		genericConfig.switchCellState(3, 2);
		genericConfig.switchCellState(3, 3);
		genericConfig.switchCellState(3, 4);
		genericConfig.switchCellState(4, 3);
		genericConfig.switchCellState(5, 4);
		
		toEmptyConfig = new Life(5, 5);
		toEmptyConfig.switchCellState(0, 0);
		toEmptyConfig.switchCellState(1, 1);
		toEmptyConfig.switchCellState(2, 2);
		toEmptyConfig.switchCellState(3, 3);
		toEmptyConfig.switchCellState(4, 4);
		
		periodicConfig = new Life(6, 6);
		periodicConfig.switchCellState(1, 2);
		periodicConfig.switchCellState(2, 2);
		periodicConfig.switchCellState(3, 2);
		
		
	}
	
	@Test
	public void testInitGrid() {
		Grid g = new Grid(6, 6);
		g.getCellAt(3, 2).setValue(CellValue.ALIVE);
		g.getCellAt(3, 3).setValue(CellValue.ALIVE);
		g.getCellAt(3, 4).setValue(CellValue.ALIVE);
		g.getCellAt(4, 3).setValue(CellValue.ALIVE);
		g.getCellAt(5, 4).setValue(CellValue.ALIVE);
		
		assertTrue(genericConfig.getGrid().equals(g));
		
	}
	
	@Test
	public void testGeneric1() {
		Grid g = new Grid(6, 6);
		g.getCellAt(2, 3).setValue(CellValue.ALIVE);
		g.getCellAt(3, 2).setValue(CellValue.ALIVE);
		g.getCellAt(3, 3).setValue(CellValue.ALIVE);
		g.getCellAt(3, 4).setValue(CellValue.ALIVE);
		g.getCellAt(4, 2).setValue(CellValue.ALIVE);
		
		genericConfig.computeNextGen();
		
		assertTrue(genericConfig.getGrid().equals(g));
		
	}
	
	@Test
	public void testGeneric2() {
		Grid g = new Grid(6, 6);
		g.getCellAt(2, 2).setValue(CellValue.ALIVE);
		g.getCellAt(2, 3).setValue(CellValue.ALIVE);
		g.getCellAt(2, 4).setValue(CellValue.ALIVE);
		g.getCellAt(3, 2).setValue(CellValue.ALIVE);
		g.getCellAt(3, 4).setValue(CellValue.ALIVE);
		g.getCellAt(4, 2).setValue(CellValue.ALIVE);
		
		genericConfig.computeNextGen();
		genericConfig.computeNextGen();
		
		assertTrue(genericConfig.getGrid().equals(g));
		
	}
	
	@Test
	public void testGeneric3() {
		Grid g = new Grid(6, 6);
		g.getCellAt(1, 3).setValue(CellValue.ALIVE);
		g.getCellAt(2, 2).setValue(CellValue.ALIVE);
		g.getCellAt(2, 4).setValue(CellValue.ALIVE);
		g.getCellAt(3, 1).setValue(CellValue.ALIVE);
		g.getCellAt(3, 2).setValue(CellValue.ALIVE);
		g.getCellAt(3, 4).setValue(CellValue.ALIVE);
		g.getCellAt(4, 3).setValue(CellValue.ALIVE);
		
		genericConfig.computeNextGen();
		genericConfig.computeNextGen();
		genericConfig.computeNextGen();
		
		assertTrue(genericConfig.getGrid().equals(g));
		
	}
	
	@Test
	public void testPeriodic1() {
		Grid g = new Grid(6, 6);
		g.getCellAt(2, 1).setValue(CellValue.ALIVE);
		g.getCellAt(2, 2).setValue(CellValue.ALIVE);
		g.getCellAt(2, 3).setValue(CellValue.ALIVE);
		periodicConfig.computeNextGen();
		
		assertTrue(periodicConfig.getGrid().equals(g));
		
	}
	
	@Test
	public void testPeriodic2() {
		Grid g = new Grid(6, 6);
		g.getCellAt(1, 2).setValue(CellValue.ALIVE);
		g.getCellAt(2, 2).setValue(CellValue.ALIVE);
		g.getCellAt(3, 2).setValue(CellValue.ALIVE);
		
		periodicConfig.computeNextGen();
		periodicConfig.computeNextGen();
		
		assertTrue(periodicConfig.getGrid().equals(g));
		
	}
	
	@Test
	public void testPeriodic3() {
		Grid g = new Grid(6, 6);
		g.getCellAt(2, 1).setValue(CellValue.ALIVE);
		g.getCellAt(2, 2).setValue(CellValue.ALIVE);
		g.getCellAt(2, 3).setValue(CellValue.ALIVE);
		
		periodicConfig.computeNextGen();
		periodicConfig.computeNextGen();
		periodicConfig.computeNextGen();
		
		assertTrue(periodicConfig.getGrid().equals(g));
		
	}
	
	
	@Test
	public void testEmpty1() {
		Grid g = new Grid(5, 5);
		g.getCellAt(1, 1).setValue(CellValue.ALIVE);
		g.getCellAt(2, 2).setValue(CellValue.ALIVE);
		g.getCellAt(3, 3).setValue(CellValue.ALIVE);
		
		toEmptyConfig.computeNextGen();
		
		assertTrue(toEmptyConfig.getGrid().equals(g));
		
	}
	
	@Test
	public void testEmpty2() {
		Grid g = new Grid(5, 5);
		g.getCellAt(2, 2).setValue(CellValue.ALIVE);
		
		toEmptyConfig.computeNextGen();
		toEmptyConfig.computeNextGen();
		
		assertTrue(toEmptyConfig.getGrid().equals(g));
		
	}
	
	@Test
	public void testEmpty3() {
		Grid g = new Grid(5, 5);
		
		toEmptyConfig.computeNextGen();
		toEmptyConfig.computeNextGen();
		toEmptyConfig.computeNextGen();
		
		assertTrue(toEmptyConfig.getGrid().equals(g));
		
	}
	
}
