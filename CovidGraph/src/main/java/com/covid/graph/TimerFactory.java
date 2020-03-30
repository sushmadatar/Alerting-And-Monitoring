package com.covid.graph;

import java.util.Timer;
import java.util.TimerTask;

public class TimerFactory {

	public static void createTimerAndExecute(long delay, long period, TimerTask timerTask) {
		try {
			
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, delay, period);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void cancelTimer(Timer timer) {
		try {
			timer.cancel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
