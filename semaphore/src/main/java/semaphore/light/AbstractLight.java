package semaphore.light;

public class AbstractLight implements Light{
	
	private OnOff state = OnOff.OFF;

	public void turnOn() {
		this.state = OnOff.ON;
		
	}

	public void turnOff() {
		this.state = OnOff.OFF;
		
	}

	public boolean isOn() {
		return this.state == OnOff.ON;
	}

	public boolean isOff() {
		return this.state == OnOff.OFF;
	}

}
