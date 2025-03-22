package unibo.disi.conway_gui_ws.util;

public class Timer extends Thread{
	
	private long delay;
	private Runnable action;
	
	
	public Timer(long delay, Runnable action) {
		super();
		this.delay = delay;
		this.action = action;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(delay);
			action.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
