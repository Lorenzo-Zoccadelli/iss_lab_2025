# Laboratorio Ingegneria dei sistemi software 2025
Repository personale dei progetti fatti durante il corso Ingegneria dei Sistemi Software a.a. 2024/2025

<h2 id="Fase1">Fase 1</h2>

Relazione Fase 1: [Fase1ISS25-ZoccadelliLorenzo.pdf](Fase1ISS25-ZoccadelliLorenzo.pdf)

### Dagli oggetti ai microservizi (in Java)
* [conway_cli](conway_cli): progetto java che realizza la parte core del gioco Conway Life con un'interfaccia di output da linea di comando
* [conway_gui_ws](conway_gui_ws): progetto java basato su Spring Boot che partendo dal core realizzato in [conway_cli](conway_cli) realizza la stessa applicazione integrandola con una gui in JavaScript che si basa sull'utilizzo di websocket. Il progetto include anche un esempio di client che pemrette di eseguire l'applicazione mediante un approccio machine-to-machine
* [conway_mqtt](conway_mqtt): progetto con il core dell'applicazione riprogettato come agente indipendente che comunica attraverso protocollo mqtt
* [conwayguialone](conwayguialone): Servizio SpringBoot che offre la GUI per intergasice via MQTT con [conway_mqtt](conway_mqtt)

* [conwayqak](conway_qak_micro/userDocs/ZoccadelliLorenzo_conway_qak_micro.html): Analisi del problema di conway qak con attori e microservizi

<h2 id="Fase2">Fase 1</h2>

Relazione Fase 2: [Fase2ISS25-ZoccadelliLorenzo.pdf](Fase2ISS25-ZoccadelliLorenzo.pdf)
