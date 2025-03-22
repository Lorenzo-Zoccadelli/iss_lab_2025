package unibo.disi.conway_gui_ws.conway.application.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import unibo.disi.conway_gui_ws.conway.application.io.IInDev;
import unibo.disi.conway_gui_ws.conway.application.io.IOutDev;
import unibo.disi.conway_gui_ws.conway.application.model.Life;
import unibo.disi.conway_gui_ws.util.Timer;

@Component
public class LifeController {
    private int generationTime = 1000;
    
    private boolean running;
    
    private  Life life;
    private IOutDev outDev;
    private IInDev inDev;

    public LifeController(Life life, @Qualifier("outDev") IOutDev outDev, @Qualifier("inDev") IInDev inDev){
    	this.running = false;
        this.life = life;
        this.outDev = outDev;
        this.inDev = inDev;
        
        inDev.setStartHandler(this::onStart);
        inDev.setStopHandler(this::onStop);
        inDev.setExitHandler(this::onExit);
        inDev.setClearHandler(this::onClear);
        inDev.setCellSwitchHandler(this::onCellSwicth);
        
        life.createGrids();
    }
    
    
    public void start(){
    	Timer t = new Timer(0, this::play);	  
    	t.start();
    }
    
    synchronized public void play() { 
   	
		if(running) {
			life.computeNextGen();
	    	displayGrid();
			Timer t = new Timer(generationTime, this::play);	  
	    	t.start();
		}
    }

	public void displayGrid() {
		outDev.displayCells(life);
	}
	
	
	synchronized private void onStart() {
		if(!running) {		
			running = true;
			start();
		}
	}
	
	synchronized private void onStop() {
		if(running) {		
			running = false;
		}
	}

	private void onExit() {
		outDev.displayCells(life);
		System.exit(0);
	}
	
	synchronized private void onClear() {
		if(!running) {		
			running = false;
			life.resetGrids();
			outDev.displayCells(life);
		}
		
	}
	
	synchronized private void onCellSwicth(int i, int j) {
		if(!running) {
			life.switchCellState(i, j);
			outDev.displayCells(life);
		}
	}
	
}

