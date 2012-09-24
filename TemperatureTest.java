//260482017 - Crisan Dan

import org.junit.* ;
import static org.junit.Assert.* ;


public class TemperatureTest {
	
	//Creates the first temperature object in Celcius.
	Temperature temperature1 = new Temperature(0.0, Temperature.Units.CELCIUS);
	double temp1Value = 0;
	Temperature.Units temp1Unit = Temperature.Units.CELCIUS;
    
	//Creates the second temperature object in Fahrenheit.
	Temperature temperature2 = new Temperature(32, Temperature.Units.FAHRENHEIT);
    double temp2Value = 32;
	Temperature.Units temp2Unit = Temperature.Units.FAHRENHEIT;
	
	//Creates the third temperature object in Kelvin.
	Temperature temperature3 = new Temperature(273.15, Temperature.Units.KELVIN);
	double temp3Value = 273.15;
	Temperature.Units temp3Unit = Temperature.Units.KELVIN;
	
	//Test if the getters are working for each temperature.
	
	//Test the value getters.
	
	//For the next 2 methods we are using data-flowing tests since we are testing defined data, which has been
	//Initialised but not used yet in the Temperature class. 
	@Test
	public void testGetValue() {
		assertTrue(temperature1.getValue() == temp1Value);
		assertTrue(temperature2.getValue() == temp2Value);
		assertTrue(temperature3.getValue() == temp3Value);
	}
	
	//Test the unit getters. 
	@Test
	public void testGetUnits() {
		assertTrue(temperature1.getUnits().equals(temp1Unit));
		assertTrue(temperature2.getUnits().equals(temp2Unit));
		assertTrue(temperature3.getUnits().equals(temp3Unit));
	}
	
	//Test if we can correctly change units for each temperature possible.
	
	//For the next 2 methods, we are using structured testing since we check each statement of the method (each case)
	@Test
	public void testChangeUnits() {
	 
		//We check if temperature2 passes from its value in Fahrenheit to its value in Celcius and also if its units are changed. 
		temperature2.changeUnits(Temperature.Units.CELCIUS); assertTrue(temperature2.getValue() == temp1Value); assertTrue(temperature2.getUnits().equals(temp1Unit));
		//We check if temperature3 passes from its value in Kelvin to its value in Celcius and also if its units are changed. 
		temperature3.changeUnits(Temperature.Units.CELCIUS); assertTrue(temperature3.getValue() == temp1Value); assertTrue(temperature3.getUnits().equals(temp1Unit));
		//We check if temperature1 passes from its value in Celcius to its value in Fahrenheit and also if its units are changed. 
		temperature1.changeUnits(Temperature.Units.FAHRENHEIT); assertTrue(temperature1.getValue() == temp2Value); assertTrue(temperature1.getUnits().equals(temp2Unit));
		//We check if temperature3 passes from its value in Kelvin to its value in Fahrenheit and also if its units are changed. 
		temperature3.changeUnits(Temperature.Units.FAHRENHEIT); assertTrue(temperature3.getValue() == temp2Value); assertTrue(temperature3.getUnits().equals(temp2Unit));
		//We check if temperature1 passes from its value in Celcius to its value in Kelvin and also if its units are changed. 
		temperature1.changeUnits(Temperature.Units.KELVIN); assertTrue(temperature1.getValue() == temp3Value); assertTrue(temperature1.getUnits().equals(temp3Unit));
		//We check if temperature1 passes from its value in Fahrenheit to its value in Kelvin and also if its units are changed. 
		temperature2.changeUnits(Temperature.Units.KELVIN); assertTrue(temperature2.getValue() == temp3Value); assertTrue(temperature2.getUnits().equals(temp3Unit));
	}

	@Test
	public void testEqualsObject() {
		//Assigns if 2 objects have the same address to a.
		boolean a = temperature1.equals(temperature1);
		//Assigns if a temperature is null to b.
		boolean b = temperature1.equals(null);
		//Assigns if a temperature is not of the same type as the other temperature. 
		boolean c = !temperature1.getClass().equals(temperature2.getClass());
		//Assigns if 2 temperatures of different units are equal. 
		boolean d = temperature1.equals(temperature2);
		//Verify the conditions. 
		assertTrue(a && b == false && c == false && d);
		//Verify if calling the method "equals" is modifying the value of the compared temperatures. 
		assertTrue(temperature1.getValue() == temp1Value);
		assertTrue(temperature2.getValue() == temp2Value);
	}

}
