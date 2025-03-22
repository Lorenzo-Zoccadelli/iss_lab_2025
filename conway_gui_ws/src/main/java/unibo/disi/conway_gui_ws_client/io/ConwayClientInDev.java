package unibo.disi.conway_gui_ws_client.io;

import org.apache.logging.log4j.util.TriConsumer;

import unibo.disi.conway_gui_ws.conway.application.model.CellValue;

public interface ConwayClientInDev {
	public void setCellUpdateHandler(TriConsumer<Integer, Integer, CellValue> callback);
}
