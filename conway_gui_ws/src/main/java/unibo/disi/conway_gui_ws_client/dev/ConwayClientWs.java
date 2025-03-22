package unibo.disi.conway_gui_ws_client.dev;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import unibo.disi.conway_gui_ws.conway.application.model.CellValue;
import unibo.disi.conway_gui_ws_client.controller.ConwayClientController;
import unibo.disi.conway_gui_ws_client.io.ConwayClientDev;

@ClientEndpoint
public class ConwayClientWs extends ConwayClientDev {
	
	private Session session;
	private WebSocketContainer container;
	
	public ConwayClientWs() {   
        try { 
        	container = ContainerProvider.getWebSocketContainer();
        	URI uri = new URI("ws://localhost:7110/wsupdates");
			container.connectToServer(this, uri);
		} catch (URISyntaxException | DeploymentException | IOException e) {
            e.printStackTrace();
        }
	}
	
	
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("Connessione aperta con il server");
    }

    @OnMessage
    public void onMessage(String message) {
    	System.out.println("Messaggio ricevuto: " + message);
    	if(message.startsWith("cell(")) {
    		String body = message.split("(")[1];
    		int i = Integer.parseInt(body.split(",")[0]);
    		int j = Integer.parseInt(body.split(",")[1]);
    		int v = Integer.parseInt(body.split(",")[2].replace(")", ""));
    		
    		if(cellUpdateHandler!=null)
    			cellUpdateHandler.accept(i-1, j-1, v==0 ? CellValue.DEAD : CellValue.ALIVE);
    		
    	}
        
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
    	this.session = null;
        System.out.println("Connessione chiusa: " + closeReason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("Errore nella connessione: " + throwable);
    }
    
    @Override
    protected void sendMessage(String message) {
    	if (session != null && session.isOpen()) {
            session.getAsyncRemote().sendText(message);
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    	System.out.println("inviato");
    }
    

    
    
}
