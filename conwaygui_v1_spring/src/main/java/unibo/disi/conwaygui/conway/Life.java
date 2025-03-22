package unibo.disi.conwaygui.conway;


/*
 * Il core di game of life
 * Non ha dipendenze da dispositivi di input/output
 * Non ha regole di controllo del gioco 
 */

public class Life {
    //La struttura
    private int rows=0;
    private int cols=0;
    private Grid grid;
    private Grid nextGrid;
 
    public Life( int rowsNum, int colsNum ) {
        this.rows   = rowsNum;
        this.cols   = colsNum;
        createGrids();   //crea la struttura a griglia
    }

    public int getRowsNum(){
        return rows;
    }
    public int getColsNum(){
        return cols;
    }

    public Grid getGrid() {
    	return grid;
    }
    
    protected void  createGrids() {
    	grid = new Grid(rows, cols);
        nextGrid = new Grid(rows, cols);   
        //CommUtils.outyellow("Life | initializeGrids done");
    }

    public void resetGrids() {
         createGrids();
    }


    protected int countNeighborsLive(int row, int col) {
    	int count = 0;
    	
    	for(int i=-1; i<2; i++) {
    		for(int j=-1; j<2; j++) {
    			if(!(i==0 && j==0) && row+i>=0 && col+j>=0 && row+i<rows && col+j<cols) {
    				if(getCellState(row+i, col+j) == CellValue.ALIVE) count++;
    			}
    		}
    	}
    	//System.out.println("["+count+"]");
        return count;
    }



    protected void computeNextGen() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int n = countNeighborsLive(i,j);
                applyRules(i, j, n);
                //outdev.displayCell( ""+grid[i][j] );
            }
            //outdev.displayCell("\n");  //Va tolta nel caso della GUI?
        }
        copyAndResetGrid();
        //outdev.displayCell("\n");
    }

    protected void copyAndResetGrid() {
    	grid = nextGrid;
    	nextGrid = new Grid(rows, cols);
    }

    protected void applyRules(int row, int col, int numNeighbors) {
        //int numNeighbors = countNeighborsLive(row, col);
        //CELLA VIVA
        if (getCellState(row, col) == CellValue.ALIVE) {
            if (numNeighbors < 2) { //muore per isolamento
            	nextGrid.getCellAt(row, col).setValue(CellValue.DEAD);
            } else if (numNeighbors == 2 || numNeighbors == 3) { //sopravvive
            	nextGrid.getCellAt(row, col).setValue(CellValue.ALIVE);
            } else if (numNeighbors > 3) { //muore per sovrappopolazione
            	nextGrid.getCellAt(row, col).setValue(CellValue.DEAD);
            }
        }
        //CELLA MORTA
        else if (getCellState(row, col) == CellValue.DEAD) {
            if (numNeighbors == 3) { //riproduzione
                nextGrid.getCellAt(row, col).setValue(CellValue.ALIVE);
            }
        }
        //CommUtils.outgreen("Life applyRules " + nextGrid   );
    }

    public void switchCellState(int i, int j){
        if( getCellState(i, j) == CellValue.DEAD) grid.getCellAt(i, j).setValue(CellValue.ALIVE);       
        else if( getCellState(i, j) == CellValue.ALIVE) grid.getCellAt(i, j).setValue(CellValue.DEAD);  
    }

    public  CellValue getCellState( int i, int j  ) {
        return   grid.getCellAt(i, j).getValue();
    }
 


}
