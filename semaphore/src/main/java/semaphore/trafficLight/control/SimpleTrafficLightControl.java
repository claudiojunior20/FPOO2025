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
	state = State.ALERT;
	if (alertTimer != null) {
        alertTimer.cancel();
    }
    alertTimer = new Timer();
    alertTimer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            
            if (trafficLight.spotYellow().isOn()) {
                trafficLight.spotYellow().turnOff();
            } else {
                trafficLight.spotYellow().turnOn();
            }
            
            trafficLight.spotRed().turnOff();
            trafficLight.spotGreen().turnOff();
        }
    }, 0, 2000); 
}
	
	
	
		 
		 
	 
	
	 
	
	

	@Override
	public void turnGreen() {
		state = State.GREEN;
		this.trafficLight.spotGreen().turnOn();
		this.trafficLight.spotRed().turnOff();
		this.trafficLight.spotYellow().turnOff();
	
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
		this.trafficLight.spotYellow().turnOff();
		this.trafficLight.spotGreen().turnOff();
		this.trafficLight.spotRed().turnOff();
	}
}
