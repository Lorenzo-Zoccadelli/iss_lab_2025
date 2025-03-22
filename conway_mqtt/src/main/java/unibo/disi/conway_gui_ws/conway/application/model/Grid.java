package unibo.disi.conway_gui_ws.conway.application.model;

public class Grid {
	private int rows;
	private int cols;
	private Cell[][] grid;
	
	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols=cols;
		grid = new Cell[rows][cols];
		
		for (int i = 0; i < rows; i++) {
           for (int j = 0; j < cols; j++) {
               grid[i][j] = new Cell(CellValue.DEAD);
           }
       }
	}
	
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public Cell getCellAt(int i, int j){
		if(i<0 || j<0 || i>=rows || j>=cols) 
			throw new IndexOutOfBoundsException("indexes ("+i+", "+j+") are not valid indexes for matrix "+rows+"x"+cols);
		return grid[i][j];
	}
	
	
	
}
