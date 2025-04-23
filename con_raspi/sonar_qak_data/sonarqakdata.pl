%====================================================================================
% sonarqakdata description   
%====================================================================================
mqttBroker("192.168.1.193", "1883", "sonar_qak/events").
dispatch( sonarstart, sonarstart(X) ).
dispatch( sonarstop, sonarstop(X) ).
event( sonardata, distance(D) ).
event( cleaneddata, distance(D) ).
%====================================================================================
context(ctxsonar, "localhost",  "TCP", "8080").
 qactor( datacleaner, ctxsonar, "it.unibo.datacleaner.Datacleaner").
 static(datacleaner).
  qactor( sonarsender, ctxsonar, "it.unibo.sonarsender.Sonarsender").
 static(sonarsender).
  qactor( sonarreciever, ctxsonar, "it.unibo.sonarreciever.Sonarreciever").
 static(sonarreciever).
