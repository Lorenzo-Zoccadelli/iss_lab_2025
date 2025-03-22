package unibo.disi.conway_gui_ws.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

public class NetworkUtil {
	public static String getServerLocalIp() {
		
        try {
            Enumeration<NetworkInterface> interfacce = NetworkInterface.getNetworkInterfaces();
            while (interfacce.hasMoreElements()) {
                NetworkInterface interfaccia = interfacce.nextElement();
                Enumeration<InetAddress> indirizzi = interfaccia.getInetAddresses();
                while (indirizzi.hasMoreElements()) {
                    InetAddress indirizzo = indirizzi.nextElement();
                    if (!indirizzo.isLoopbackAddress()) { // Esclude l'indirizzo loopback (127.0.0.1)
                        //System.out.println(interfaccia.getDisplayName() + ": " + indirizzo.getHostAddress());                        
                        if( indirizzo.getHostAddress().startsWith("192.168")) {
                        	System.out.println("ConwayGuiControllerLifeLocal ==== " + indirizzo.getHostAddress());
                        	return indirizzo.getHostAddress();
                        }
                    }
                }
            }
            return null;
        } catch (SocketException e) {
            System.err.println("Errore durante la ricerca degli indirizzi IP: " + e.getMessage());
            return null;
        }			
 	
	}
	
	public static  String getMyPublicip(){
		try {
			// URL di un servizio che restituisce l'indirizzo IP pubblico
			String serviceUrl = "https://checkip.amazonaws.com";

			// Creazione della connessione HTTP
			URL url = new URL(serviceUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// Lettura della risposta
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();
			
			//String localip = getServerLocalIp();
			
			// Stampa dell'indirizzo IP pubblico
			String myip = response.toString().trim();
			//System.out.println("Il tuo indirizzo IP pubblico è: " +  myip);
		    return  myip;
		    //return localip;
		} catch (Exception e) {
			System.out.println("Errore nell'ottenere l'indirizzo IP: " + e.getMessage());
			return null;
		}
	}
}
