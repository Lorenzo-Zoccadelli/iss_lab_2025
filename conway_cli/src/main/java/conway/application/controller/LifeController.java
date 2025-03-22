package conway.application.controller;

import java.util.concurrent.TimeUnit;

import conway.application.io.IOutDev;
import conway.application.model.Life;
import conway.device.ConwayOutputCli;



public class LifeController {
    private int generationTime = 1000;
    private  Life life;
    private IOutDev outdev;

    public LifeController(Life game){  
        this.life = game;
        configureTheSystem();
     }

    protected void configureTheSystem() {
		life.createGrids();
        outdev = new ConwayOutputCli();		
    }
    
    
    public void start(){
    	outdev.displaySection("Initial");
    	
		displayGrid();
		play(); 		   	
    }
    
    protected void play() {
		for( int i=1;i<=5;i++){
			try {
				TimeUnit.MILLISECONDS.sleep(generationTime);
				outdev.displaySection("Epoch "+i);
				life.computeNextGen();
				
				displayGrid();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}    	
    }

	public void displayGrid() {
		outdev.displayCells(life);
	}

}

