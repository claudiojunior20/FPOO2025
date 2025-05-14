package semaphore.trafficLight.control;  
  
  
import semaphore.trafficLight.TrafficLight;  
import java.util.Timer;  
import java.util.TimerTask;  
  
  
  
public class SimpleTrafficLightControl implements TrafficLightControl {  
    
 private  final TrafficLight trafficLight;  
 private State state = State.OFF;  
   private Timer alertTimer;  
   
 public SimpleTrafficLightControl(TrafficLight trafficLight) {  
  this.trafficLight=trafficLight;  
 }  
  
 @Override  
 public void turnAlert() {  
 if(state == State.ALERT)  
return;
 this.reset();
 this.configureAlert();
 state = State.ALERT;
   
 

 @Override  
 public void turnGreen() {  
  state = State.GREEN;  
  this.reset();
  this.trafficLight.spotGreen().turnOn();  
  
   
 }  
  
 @Override  
 public void turnYellow() {  
  state =State.YELLOW;  
  this.trafficLight.spotYellow().turnOn();  
this.trafficLight.spotRed().turnOff();  
this.trafficLight.spotGreen().turnOff();  
}  
@Override  
public void turnRed() {  
state= State.RED;  
this.trafficLight.spotRed().turnOn();  
this.trafficLight.spotYellow().turnOff();  
this.trafficLight.spotGreen().turnOff();  
}  
@Override  
public void turnOff() {  
state = State.OFF;  
this.reset();  
}  
public void reset() {
	 
	 if (state == State.ALERT)
		 stopTimer();
	 
	 trafficLight.spotGreen().turnOff();
	 trafficLight.spotRed().turnOff();
	 trafficLight.spotYellow().turnOff();
	 
state=state.OFF;
}}
