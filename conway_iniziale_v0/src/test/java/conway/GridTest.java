package conway;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GridTest {

	private Cell aliveCell;
	private Cell deadCell;
	
	@Before
	public void init() {
		aliveCell = new Cell(CellValue.ALIVE);
		deadCell = new Cell(CellValue.DEAD);
	}
	
	@Test
	public void testInitEmpty() {
		Grid g = new Grid(5, 3); 
		for(int i=0; i<5; i++) {
			for(int j=0; j<3; j++) {
				assertTrue(g.getCellAt(i, j).equals(deadCell));
			}
		}
	}
	
	@Test
	public void testInitModified() {
		Grid g = new Grid(3, 3); 
		g.getCellAt(0, 0).setValue(CellValue.ALIVE);
		g.getCellAt(0, 1).setValue(CellValue.ALIVE);
		g.getCellAt(0, 2).setValue(CellValue.ALIVE);
		g.getCellAt(1, 1).setValue(CellValue.ALIVE);
		g.getCellAt(2, 2).setValue(CellValue.ALIVE);
		
		assertTrue(g.getCellAt(0, 0).equals(aliveCell));
		assertTrue(g.getCellAt(0, 1).equals(aliveCell));
		assertTrue(g.getCellAt(0, 2).equals(aliveCell));
		assertTrue(g.getCellAt(1, 0).equals(deadCell));
		assertTrue(g.getCellAt(1, 1).equals(aliveCell));
		assertTrue(g.getCellAt(1, 2).equals(deadCell));
		assertTrue(g.getCellAt(2, 0).equals(deadCell));
		assertTrue(g.getCellAt(2, 1).equals(deadCell));
		assertTrue(g.getCellAt(2, 2).equals(aliveCell));
	}
	
	@Test
	public void testEquals() {
		Grid[] g = new Grid[2];
		g[0] = new Grid(3, 3); 
		g[1] = new Grid(3, 3); 
		for(int i=0; i<2; i++) {
			g[i].getCellAt(0, 0).setValue(CellValue.ALIVE);
			g[i].getCellAt(0, 1).setValue(CellValue.ALIVE);
			g[i].getCellAt(0, 2).setValue(CellValue.ALIVE);
			g[i].getCellAt(1, 1).setValue(CellValue.ALIVE);
			g[i].getCellAt(2, 2).setValue(CellValue.ALIVE);
		}
		
		assertTrue(g[0].equals(g[1]));
	}
	
	
	
}
