package unibo.disi.conway_gui_ws.conway.device;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import jakarta.websocket.server.ServerEndpoint;
import unibo.disi.conway_gui_ws.conway.application.io.IInDev;
import unibo.disi.conway_gui_ws.conway.application.io.IOutDev;
import unibo.disi.conway_gui_ws.conway.application.model.CellValue;
import unibo.disi.conway_gui_ws.conway.application.model.Life;

@Component
@ServerEndpoint("/wsupdates")
public class WsIODev extends AbstractWebSocketHandler implements IOutDev, IInDev{	

	private static WsIODev instance = null;
	
	private List<WebSocketSession> activeSessions;
	private WebSocketSession owner;
	private boolean ownerOff;
	
	
	private Runnable startHandler;
	private Runnable stopHandler;
	private Runnable clearHandler;
	private Runnable exitHandler;
	private BiConsumer<Integer, Integer> cellSwitchHandler;
	
	
	public static WsIODev getInstance() {
		if(instance==null) {
			instance = new WsIODev(); 
		}
		
		return instance;
	}
	
	private WsIODev(){
		activeSessions = new LinkedList<>();
		ownerOff = false;
		owner = null;
	}
	
	
	
	
	@Override
	public void displayCells(Life life) {
		for(int i=0; i<life.getRowsNum(); i++) {
			for(int j=0; j<life.getColsNum(); j++) {
				sendBroadcast("cell(" + (j+1) + "," + (i+1) + ","+ (life.getCellState(i, j) == CellValue.DEAD ? 0 : 1) + ")");
			}
		}
		
	}
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);	
		
		activeSessions.add(session);
		if(owner == null) {
			this.owner = session;
			System.out.println("New user connected as owner");
		}
		else {
			System.out.println("New user connected");
		}
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		
		System.out.println("Received message: "+message.getPayload());
		
		if(ownerOff || session.equals(owner)) {
			if(message.getPayload().equalsIgnoreCase("start")) {
				startHandler.run();
			}
			else if(message.getPayload().equalsIgnoreCase("stop")) {
				stopHandler.run();
			}
			else if(message.getPayload().equalsIgnoreCase("exit")) {
				exitHandler.run();
			}
			else if(message.getPayload().equalsIgnoreCase("clear")) {
				clearHandler.run();
			}
			else if(message.getPayload().startsWith("cell")) {
				String[] parts = message.getPayload().split("-");
				int x = Integer.parseInt(parts[1]);
				int y = Integer.parseInt(parts[2]);
				cellSwitchHandler.accept(x-1, y-1);
			}
		}
		
		if(message.getPayload().equalsIgnoreCase("owneroff")) 
			ownerOff = true;
		else if(message.getPayload().equalsIgnoreCase("owneron"))
			ownerOff = false;
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		activeSessions.remove(session);
		if(owner.equals(session)) {
			System.out.println("Owner disconnected");
		}
		else {
			System.out.println("User disconnected");
		}
	}
	
	
	
	private void sendBroadcast(String msg) {
		for(var session : activeSessions) {
			if(session.isOpen()) {
				try {
					session.sendMessage(new TextMessage(msg));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				activeSessions.remove(session);
			}
		}
	}

	

	
	@Override
	public void setStartHandler(Runnable callback) {
		startHandler = callback;
	}

	@Override
	public void setStopHandler(Runnable callback) {
		stopHandler = callback;
	}

	@Override
	public void setClearHandler(Runnable callback) {
		clearHandler = callback;
	}

	@Override
	public void setExitHandler(Runnable callback) {
		exitHandler = callback;
	}

	@Override
	public void setCellSwitchHandler(BiConsumer<Integer, Integer> callback) {
		cellSwitchHandler = callback;
	}
	
}
