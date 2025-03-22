package unibo.disi.conway_gui_ws.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import unibo.disi.conway_gui_ws.conway.application.io.IInDev;
import unibo.disi.conway_gui_ws.conway.application.io.IOutDev;
import unibo.disi.conway_gui_ws.conway.application.model.Life;
import unibo.disi.conway_gui_ws.conway.device.WsIODev;

@Configuration
@EnableWebSocket
public class ApplicationConfig {

	@Bean
	public Life life() {
		return new Life(20, 20);
	}
	
	@Bean
	@Qualifier("outDev")
	public IOutDev outDev() {
		return WsIODev.getInstance();
	}
	
	@Bean
	@Qualifier("inDev")
	public IInDev inDev() {
		return WsIODev.getInstance();
	}
	
}
