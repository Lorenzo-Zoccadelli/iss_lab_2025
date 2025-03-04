package unibo.disi.conwaygui.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class ConwayGuiController {

	@Value("spring.application.name")
	String appName;
	
	@Value("server.port")
	String port;

	
	

}
