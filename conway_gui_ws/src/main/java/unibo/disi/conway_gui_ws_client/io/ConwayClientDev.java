package unibo.disi.conway_gui_ws_client.io;

import org.apache.logging.log4j.util.TriConsumer;

import unibo.disi.conway_gui_ws.conway.application.model.CellValue;
import unibo.disi.conway_gui_ws_client.dev.ConwayClientWs;

public abstract class ConwayClientDev implements ConwayClientInDev, ConwayClientOutDev {

	private static ConwayClientDev instance=null;
	protected TriConsumer<Integer, Integer, CellValue> cellUpdateHandler = null;
	
	public static ConwayClientDev getInstance() {
		if(instance==null) {
			instance = new ConwayClientWs();
		}
		return instance;
	}
	
	@Override
	public void setCellUpdateHandler(TriConsumer<Integer, Integer, CellValue> callback) {
		this.cellUpdateHandler = callback;
	}
	
	protected abstract void sendMessage(String msg);
	
    public void sendStartRequest() {
    	sendMessage("start");
    }
    public void sendStopRequest() {
    	sendMessage("stop");
    }
    public void sendExitRequest() {
    	sendMessage("exit");
    }
    public void sendClearRequest() {
    	sendMessage("exit");
    }
    public void sendSwitchCellRequest(int i, int j) {
    	sendMessage("cell-"+i+"-"+j);
    }
    
}
