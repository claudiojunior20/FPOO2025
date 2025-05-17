package semaphore.control.simple;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import semaphore.trafficLight.control.TrafficLightControl;

public class OneWaySemaphoreControl implements SemaphoreControl {
	
	
  private List<TrafficLightControl> trafficLights = new ArrayList<>();
  private int greenMillis = 10_000;
  private int yellowMillis = 2_000;
  private int redMillis = 5_000;
  
    private LocalTime alertStart = LocalTime.of(0, 0);
    private LocalTime alertEnd = LocalTime.of(3, 30);
  
  private OnOff state = OnOff.OFF;
  
  
  
  public OneWaySemaphoreControl(List<TrafficLightControl> trafficLights) {
	  
	  this.trafficLights = trafficLights;
  }
  
  public OneWaySemaphoreControl(TrafficLightControl... trafficLights) {
	  
	  this(Arrays.asList(trafficLights));
  }
  
  
  private boolean isAlertPeriod() {
	  
	  boolean START_MIDNIGHT_END = alertStart.isAfter(alertEnd);
	  
	  LocalTime now = LocalTime.now();
	  
	  if(START_MIDNIGHT_END)
		  return (now.isAfter(alertStart) || now.isBefore(alertEnd));
	  
	  return (now.isAfter(alertStart) && now.isBefore(alertEnd));
  }
  
  
  private void doAlert() throws InterruptedException{
	  while(isAlertPeriod()) {
		  trafficLights.forEach(e->e.turnAlert());
		  Thread.sleep(1_000);
	  }
  }
  
  
  private void doYellowRedGreen() throws InterruptedException {
	 
	  trafficLights.forEach(e->e.turnYellow());
	  Thread.sleep(yellowMillis);
	  
	  trafficLights.forEach(e->e.turnRed());
	  Thread.sleep(redMillis);
	  
	  trafficLights.forEach(e->e.turnGreen());
	  Thread.sleep(greenMillis);
  }
 private void run() {
	 Runnable runnable = ()->{
		 
		 while (state == OnOff.ON) {
			 try {
				 doAlert();
				 doYellowRedGreen();
				 
			 }
			 catch(InterruptedException exception) {
				 
				 trafficLights.forEach(e->e.turnAlert());
			 }
		 }
	 };
	 Thread thread = new Thread(runnable);
	 thread.start();
 }
  
  
  
  
@Override
public void turnOn() {
	// 
	
}
@Override
public void turnOff() {
	 if (state == OnOff.OFF) {
		 
	 }
	
}
@Override
public boolean isOn() {
	// pra fazer depois
	return false;
}
@Override
public boolean isOff() {
	 while (state == OnOff.OFF) {
		 
	 }
	return false;
}
@Override
public void setGreenSeconds(int seconds) {
	
	
}
@Override
public void setRedSeconds(int seconds) {
	// pra fazer depois
	
}
@Override
public void setYellowSeconds(int seconds) {
	// pra fazer depois
	
}
@Override
public void setAlertPeriod(LocalTime start, LocalTime end) {
	this.alertStart ;
		
	}
	
}
}
