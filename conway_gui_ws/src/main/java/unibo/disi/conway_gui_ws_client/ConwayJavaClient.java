package unibo.disi.conway_gui_ws_client;

import unibo.disi.conway_gui_ws_client.controller.ConwayClientController;

public class ConwayJavaClient {
	

    public static void main(String[] args) {

    	ConwayClientController controller = new ConwayClientController();
    	controller.switchCellState(4, 4);
    	controller.switchCellState(5, 4);
    	controller.switchCellState(6, 4);
    	controller.start();
    	try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	controller.stop();

    }
}
