package unibo.disi.conway_gui_ws_client.io;

public interface ConwayClientOutDev {
    public void sendStartRequest();
    public void sendStopRequest();
    public void sendExitRequest();
    public void sendClearRequest();
    public void sendSwitchCellRequest(int i, int j);
}
