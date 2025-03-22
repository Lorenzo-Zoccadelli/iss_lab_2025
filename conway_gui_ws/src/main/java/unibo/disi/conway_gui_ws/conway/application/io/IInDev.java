package unibo.disi.conway_gui_ws.conway.application.io;

import java.util.function.BiConsumer;

public interface IInDev {
	public void setStartHandler(Runnable callback);
	public void setStopHandler(Runnable callback);
	public void setClearHandler(Runnable callback);
	public void setExitHandler(Runnable callback);
	public void setCellSwitchHandler(BiConsumer<Integer, Integer> callback);
}
