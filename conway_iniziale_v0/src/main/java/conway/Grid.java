package conway;

import java.util.Arrays;
import java.util.Objects;

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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(grid);
		result = prime * result + Objects.hash(cols, rows);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Grid other = (Grid) obj;
		
		if(cols != other.cols || rows != cols) return false;
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				if(!this.grid[i][j].equals(other.getCellAt(i, j)))
					return false;
			}
		}
		
		return true;
		//return cols == other.cols && Arrays.deepEquals(grid, other.grid) && rows == other.rows;
	}
	
	
	
	
}
