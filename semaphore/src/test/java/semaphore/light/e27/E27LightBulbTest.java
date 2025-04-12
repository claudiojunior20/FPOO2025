package semaphore.light.e27;




import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import semaphore.light.Light;

public class E27LightBulbTest {

	@Test
	public void shouldTurnOn() {
		//given
		Light light = new E27LightBulb();
		
		//do action
		light.turnOff();
		light.turnOn();
		
		//check
		assertTrue(light.isOn());
	}

}
