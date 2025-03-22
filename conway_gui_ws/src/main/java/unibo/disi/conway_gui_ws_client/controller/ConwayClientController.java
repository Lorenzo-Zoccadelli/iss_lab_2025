package unibo.disi.conway_gui_ws_client.controller;

import unibo.disi.conway_gui_ws.conway.application.io.IOutDev;
import unibo.disi.conway_gui_ws.conway.application.model.CellValue;
import unibo.disi.conway_gui_ws.conway.application.model.Life;
import unibo.disi.conway_gui_ws_client.dev.ConwayOutputCli;
import unibo.disi.conway_gui_ws_client.io.ConwayClientDev;

public class ConwayClientController {
	private Life life;
	private ConwayClientDev dev;
	
	public ConwayClientController() {
		this.life = new Life(20, 20);
		this.dev = ConwayClientDev.getInstance();
		dev.setCellUpdateHandler(this::onCellUpdate);
	}
	
	public void start() {
		dev.sendStartRequest();
	}
	
	public void stop() {
		dev.sendStopRequest();
	}
	
	public void clear() {
		dev.sendClearRequest();
	}
	
	public void exit() {
		dev.sendExitRequest();
	}
		
	public void switchCellState(int i, int j) {
		dev.sendSwitchCellRequest(i, j);
	}
	
	public void onCellUpdate(int i, int j, CellValue value) {
		if(life.getCellState(i, j)!=value) {
			life.getCell(i, j).setValue(value);
			System.out.println("Updated cell "+i+", "+j+": value "+value);
		}
		
	}

}
