package conway.device;

import conway.CellValue;
import conway.IOutDev;
import conway.Life;

//
public class ConwayOutput implements IOutDev{
 
	@Override
	public void displayCells(Life life) {	
		for(int i=0; i<life.getRowsNum(); i++) {
			for(int j=0; j<life.getColsNum(); j++) {
				if(life.getCellState(i, j) == CellValue.DEAD)
					System.out.print("0 ");
				else if(life.getCellState(i, j) == CellValue.ALIVE)
					System.out.print("1 ");
			}
			System.out.println();
		}
	}

	@Override
	public void displaySection(String sectionName) {
		System.out.println("---------"+sectionName+"----------");
		
	}
	
}
