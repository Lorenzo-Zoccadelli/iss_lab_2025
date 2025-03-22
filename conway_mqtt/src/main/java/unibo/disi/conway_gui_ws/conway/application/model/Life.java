package unibo.disi.conway_gui_ws.conway.application.model;


/*
 * Il core di game of life
 * Non ha dipendenze da dispositivi di input/output
 * Non ha regole di controllo del gioco 
 */

public class Life {
    private int rows=0;
    private int cols=0;
    private Grid grid;
    private Grid nextGrid;
 
    public Life( int rowsNum, int colsNum ) {
        this.rows   = rowsNum;
        this.cols   = colsNum;
        createGrids();
    }

    public int getRowsNum(){
        return rows;
    }
    public int getColsNum(){
        return cols;
    }

    public void  createGrids() {
    	grid = new Grid(rows, cols);
        nextGrid = new Grid(rows, cols);
    }

    public void resetGrids() {
         createGrids();
    }


    private int countNeighborsLive(int row, int col) {
    	int count = 0;
    	
    	for(int i=-1; i<2; i++) {
    		for(int j=-1; j<2; j++) {
    			if(!(i==0 && j==0) && row+i>=0 && col+j>=0 && row+i<rows && col+j<cols) {
    				if(getCellState(row+i, col+j) == CellValue.ALIVE) count++;
    			}
    		}
    	}
        return count;
    }



    public void computeNextGen() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int n = countNeighborsLive(i,j);
                applyRules(i, j, n);
            }
        }
        copyAndResetGrid();
    }

    private void copyAndResetGrid() {
    	grid = nextGrid;
    	nextGrid = new Grid(rows, cols);
    }

    private void applyRules(int row, int col, int numNeighbors) {
        if (getCellState(row, col) == CellValue.ALIVE) {
            if (numNeighbors < 2) { //muore per isolamento
            	nextGrid.getCellAt(row, col).setValue(CellValue.DEAD);
            } else if (numNeighbors == 2 || numNeighbors == 3) { //sopravvive
            	nextGrid.getCellAt(row, col).setValue(CellValue.ALIVE);
            } else if (numNeighbors > 3) { //muore per sovrappopolazione
            	nextGrid.getCellAt(row, col).setValue(CellValue.DEAD);
            }
        }

        else if (getCellState(row, col) == CellValue.DEAD) {
            if (numNeighbors == 3) { //riproduzione
                nextGrid.getCellAt(row, col).setValue(CellValue.ALIVE);
            }
        }
    }

    public void switchCellState(int i, int j){
        if( getCellState(i, j) == CellValue.DEAD) grid.getCellAt(i, j).setValue(CellValue.ALIVE);       
        else if( getCellState(i, j) == CellValue.ALIVE) grid.getCellAt(i, j).setValue(CellValue.DEAD);  
    }

    public  CellValue getCellState( int i, int j  ) {
        return   grid.getCellAt(i, j).getValue();
    }
    
    public  Cell getCell( int i, int j  ) {
        return   grid.getCellAt(i, j);
    }
 


}
