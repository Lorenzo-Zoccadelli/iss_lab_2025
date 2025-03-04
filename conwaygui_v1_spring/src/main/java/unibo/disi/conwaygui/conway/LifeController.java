package unibo.disi.conwaygui.conway;

import java.util.concurrent.TimeUnit;

import unibo.disi.conwaygui.conway.device.ConwayOutput;


public class LifeController {
    private int generationTime = 1000;
    private  Life life;
    private IOutDev outdev;
    private int maxIterations;

    public LifeController(Life game, int maxIterations){  
        this.life = game;
        this.maxIterations=maxIterations;
        configureTheSystem();
     }

    protected void configureTheSystem() {
		//CommUtils.outyellow("LifeController | doJob ");
		life.createGrids();
        outdev = new ConwayOutput(   );		
    }
    
    //Called by ConwayInputMock
    public void start(){
    	outdev.displaySection("Initial");
		//La griglia è visualizzata con un ciclo
		displayGrid();
		play(); 		   	
    }
    
    protected void play() {
		//while (true) {
		for( int i=1;i<=maxIterations;i++){
			try {
				TimeUnit.MILLISECONDS.sleep(generationTime);
				outdev.displaySection("Epoch "+i);
				life.computeNextGen();
				//La griglia è visualizzata  'on the fly'
				displayGrid();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}    	
    }

	public void displayGrid() {
		outdev.displayCells(life);
	}
	
	public Grid getGrid() {
		return life.getGrid();
	}

}

