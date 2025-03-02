package conway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LifeControllerTest {

	private Life genericConfig;
	private Life toEmptyConfig;
	private Life periodicConfig;
	private LifeController genericController;
	private LifeController toEmptyController;
	private LifeController periodicController;

	@Before
	public void init() {
		genericConfig = new Life(6, 6);
		genericController = new LifeController(genericConfig, 5);
		genericConfig.switchCellState(3, 2);
		genericConfig.switchCellState(3, 3);
		genericConfig.switchCellState(3, 4);
		genericConfig.switchCellState(4, 3);
		genericConfig.switchCellState(5, 4);
		
		
		toEmptyConfig = new Life(5, 5);
		toEmptyController = new LifeController(toEmptyConfig, 5);
		toEmptyConfig.switchCellState(0, 0);
		toEmptyConfig.switchCellState(1, 1);
		toEmptyConfig.switchCellState(2, 2);
		toEmptyConfig.switchCellState(3, 3);
		toEmptyConfig.switchCellState(4, 4);
		
		
		periodicConfig = new Life(6, 6);
		periodicController = new LifeController(periodicConfig, 5);
		periodicConfig.switchCellState(1, 2);
		periodicConfig.switchCellState(2, 2);
		periodicConfig.switchCellState(3, 2);
		
		
		
	}
	
	@Test
	public void testGeneric() {
		Grid g = new Grid(6, 6);
		g.getCellAt(1, 2).setValue(CellValue.ALIVE);
		g.getCellAt(1, 3).setValue(CellValue.ALIVE);
		g.getCellAt(2, 1).setValue(CellValue.ALIVE);
		g.getCellAt(2, 4).setValue(CellValue.ALIVE);
		g.getCellAt(3, 4).setValue(CellValue.ALIVE);
		g.getCellAt(4, 1).setValue(CellValue.ALIVE);
		g.getCellAt(4, 2).setValue(CellValue.ALIVE);
		g.getCellAt(4, 3).setValue(CellValue.ALIVE);
		
		genericController.start();

		
		assertEquals(genericController.getGrid(), g);
	}
	
	@Test
	public void testPeriodic() {
		Grid g = new Grid(6, 6);
		g.getCellAt(2, 1).setValue(CellValue.ALIVE);
		g.getCellAt(2, 2).setValue(CellValue.ALIVE);
		g.getCellAt(2, 3).setValue(CellValue.ALIVE);
		
		periodicController.start();
		
		assertEquals(periodicController.getGrid(), g);
	}
	
	@Test
	public void testToEmpty() {
		Grid g = new Grid(5, 5);
		
		toEmptyController.start();
		
		assertEquals(toEmptyController.getGrid(), g);
	}
}
