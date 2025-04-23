%====================================================================================
% sonar_qak description   
%====================================================================================
mqttBroker("192.168.1.193", "1883", "sonar_qak/events").
dispatch( sonarstart, sonarstart(X) ).
dispatch( sonarstop, sonarstop(X) ).
event( sonardata, distance(D) ).
%====================================================================================
context(ctx_sonar, "localhost",  "TCP", "8080").
 qactor( datacleaner, ctx_sonar, "it.unibo.datacleaner.Datacleaner").
 static(datacleaner).
