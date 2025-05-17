package semaphore.trafficLight.control;


import semaphore.trafficLight.TrafficLight;

import semaphore.util.TurnOnOff;

import java.util.Timer;
import java.util.TimerTask;



public class SimpleTrafficLightControl implements TrafficLightControl {

private final TrafficLight trafficLight;
private final TurnOnOff green, yellow, red;
private State state = State.OFF;
private Timer alertTimer;

public SimpleTrafficLightControl(TrafficLight trafficLight) {
this.trafficLight=trafficLight;
this.green = trafficLight.spotGreen();
this.yellow = trafficLight.spotYellow();
this.red = trafficLight.spotRed();
}

@Override
public void turnAlert() {

if(state == State.ALERT)
return ;
this.reset();
this.configureAlert();
state = State.ALERT;

}


@Override
public void turnGreen() {

this.reset();
this.trafficLight.spotGreen().turnOn();
state = State.GREEN;

}

@Override
public void turnYellow() {

this.reset();
this.trafficLight.spotYellow().turnOn();
state =State.YELLOW;
}


@Override

public void turnRed() {

this.reset();
this.trafficLight.spotRed().turnOn();
state= State.RED;
}


@Override


public void turnOff() {

state = State.OFF;
this.reset();
}

public void reset() {
if (state == State.ALERT)
stopTimer();
green.turnOff();
red.turnOff();
yellow.turnOff();
state=state.OFF;
}}