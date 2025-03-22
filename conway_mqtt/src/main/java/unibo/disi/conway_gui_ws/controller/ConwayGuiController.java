package unibo.disi.conway_gui_ws.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import unibo.basicomm23.utils.CommUtils;
import unibo.disi.conway_gui_ws.conway.application.controller.LifeController;
import unibo.disi.conway_gui_ws.conway.application.model.Life;
import unibo.disi.conway_gui_ws.util.NetworkUtil;


@Controller
public class ConwayGuiController {

	@Value("${spring.application.name}")
	private String appName;
	
	@Value("${server.port}")
	private String serverport;

	private LifeController lifeController;
	private Life life;
	private Gson gson;
	private boolean started;
	
	public ConwayGuiController(LifeController lifeController, Life life, Gson gson) {
		this.life = life;
		this.lifeController = lifeController;
		this.gson = gson;
		started=false;
	}
	
	//WSIoDev.getInstance().setLifeCotrol(lifeController); //injections

	@GetMapping("/")
	public String homePage(Model model) {
		if( ! started ) {
			life.resetGrids();
			model.addAttribute("arg", appName );
			started = true;
		}
	    return "guipage";  
	}
	
	@RequestMapping("/testHTTPCallResponseBody")
	@ResponseBody
	public String testHTTPCallResponseBody( ) {
		JsonObject res = new JsonObject();
		res.addProperty("message", "ConwayGuiControllerLifeLocal Hello from testHTTPCallResponseBody");
		return gson.toJson(res);
	}
	
	@RequestMapping("/getserverip")
	@ResponseBody
	public String getserverip() {
		System.out.println("ConwayGuiControllerLifeLocal doing getserverip"  );
		
		String ip = CommUtils.getEnvvarValue("HOST_IP"); // in docker-compose
		if( ip == null ) {
			ip = NetworkUtil.getServerLocalIp();
			System.out.println("ConwayGuiControllerLifeLocal getserverip: myip=" + ip);		
			if(ip==null) {//non ho la rete ...
				ip="localhost";
				System.out.println("ConwayGuiControllerLifeLocal senza myip=" + ip);	
			}
			else {
				ip = NetworkUtil.getMyPublicip();
	 			System.out.println("ConwayGuiControllerLifeLocal getserverip: mypubip=" + ip);
				System.out.println("ConwayGuiControllerLifeLocal con myip=" + ip);		      
			}
		}
		//imposto localhost in automatico
		ip="localhost";

		JsonObject res = new JsonObject();
		res.addProperty("host", ip);
		return gson.toJson(res);
		
	}
 
	
	

	 
}
