package unibo.disi.conway_gui_ws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import unibo.basicomm23.utils.CommUtils;
import unibo.disi.conway_gui_ws.conway.device.WsIODev;

@Configuration
@EnableWebSocket
public class WsConfig implements WebSocketConfigurer {

	public final String wsPath  = "wsupdates";

    
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		CommUtils.outblue( "WebSocketConfiguration | registerWebSocketHandlers" );		
			WsIODev wsgui = WsIODev.getInstance();
			registry.addHandler(wsgui, wsPath).setAllowedOrigins("*");
 	}
}
