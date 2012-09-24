//Dan Crisan - 290684017

/**
 * We want to use the Java collections library to sort 
 * a collection of temperatures. Therefore, the {@code Temperature} 
 * class implements the {@code Comparable} interface by overriding 
 * {@code compareTo} method.
 */

public class Temperature implements Comparable<Temperature> {
	
//the object Temperature has 2 properties: the value and the unit, initialised later on in the constructor. 
	private double valTemp;
	private Units uniTemp;
	
	/** Enumerator for different temperature units */
	public static enum Units { FAHRENHEIT, CELCIUS, KELVIN }
	
  /**
   * Create a new {@code Temperature} with given attributes
   * @param value numerical value of {@code Temperature}
   * @param units {@code Units} of {@code Temperature}
   */
	
//The properties are initialised in the constructor. Each temperature has its own unit and value. 
  public Temperature (double value, Units units) {
	  valTemp = value;
	  uniTemp = units;	  
  }

  /**
    * Get the value of the {@code Temperature}
    * @return numerical value of the {@code Temperature} in the current {@code Units}
    */
  
  public double getValue() { //allows us to access the value of each temperature
	  return valTemp;
  }

  /**
   * Get the current {@code Units} of the {@code Temperature}
   * @return current {@code Units} of {@code Temperature}
   */
  public Units getUnits() { //allows us to access the unit of each temperature
	  return uniTemp;
  }

  /**
   * Change the current {@code Units} of the {@code Temperature}. 
   * Changing the {@code Units} also changes the numerical value 
   * in a consistent manner.
   * @param units the new {@code Units} 
   */
  public void changeUnits(Units units) {
	  //Let's handle the case where the user is mistakenly using the same unit to change the temperature (i.e: changeUnits to Celcius from... Celcius)
	  if (Temperature.this.getUnits().equals(units)) return; //if it is the case, skip this method (don't change anything)
	  
	  //If not, change the temperature in the specific unit using the following formulas:	  
	  double CfromF = (Temperature.this.getValue() - 32) / (9/5); //to Celcius from Fahrenheit
	  double CfromK = Temperature.this.getValue() - 273.15; //to Celcius from Kelvin
	  
	  double FfromC = Temperature.this.getValue() * 9 / 5 + 32; //to Fahrenheit from Celcius
	  double FfromK = CfromK * 9 / 5 + 32; //to Fahrenheit from Kelvin
	  
	  double KfromC = Temperature.this.getValue() + 273.15; //to Kelvin from Celcius
	  double KfromF = CfromF + 273.15; //to Kelvin from Fahrenheit
	  
	  //We will use the following structure for the conversion:
	  
	  //If we want to change the temperature from Fahrenheit to Celcius...
	  if (Temperature.this.getUnits().equals(Units.FAHRENHEIT) && units.equals(Units.CELCIUS)){
		  valTemp = CfromF;	//...the value of the temperature should follow the formula for calculating CfromF
		  uniTemp = Units.CELCIUS; //...and then the actual temperature unit should be changed to Celcius.
	  }
	  else if (Temperature.this.getUnits().equals(Units.KELVIN) && units.equals(Units.CELCIUS)){
		  valTemp = CfromK;	
		  uniTemp = Units.CELCIUS;
	  }
	  else if (Temperature.this.getUnits().equals(Units.CELCIUS) && units.equals(Units.FAHRENHEIT)){
		  valTemp = FfromC;
		  uniTemp = Units.FAHRENHEIT;
	  }
	  else if (Temperature.this.getUnits().equals(Units.KELVIN) && units.equals(Units.FAHRENHEIT)){
		  valTemp = FfromK;
		  uniTemp = Units.FAHRENHEIT;
	  }
	  else if (Temperature.this.getUnits().equals(Units.CELCIUS) && units.equals(Units.KELVIN)){
		  valTemp = KfromC;
		  uniTemp = Units.KELVIN;
	  }
	  else if (Temperature.this.getUnits().equals(Units.FAHRENHEIT) && units.equals(Units.KELVIN)){
		  valTemp = KfromF;
		  uniTemp = Units.KELVIN;
	  }
  
  }

  /** 
   * Convert the {@code Temperature} to {@code String}. The output is
   * as follows
   * <pre><code>
   *    Temperature temperature = new Temperature(0, Units.CELCIUS);
   *    System.out.println(temperature.toString()); // prints "0 °C"
   *    temperature.changeUnits(Units.FAHRENHEIT);
   *    System.out.println(temperature.toString()); // prints "32 °F"
   *    temperature.changeUnits(Units.KELVIN);
   *    System.out.println(temperature.toString()); // prints "273.15 K"
   * </code></pre>
   */
  public String toString() {
	  String string = "0 please try again"; //Initialises our string.
	  //If the unit of our temperature is Celcius, then the string should be composed of the actual value of the temperature and its symbol at the end, corresponding to its unit. 
	  if (Temperature.this.getUnits().equals(Units.CELCIUS)){
	  	 string = Temperature.this.getValue() + " �C";
	  }
	  else if (Temperature.this.getUnits().equals(Units.FAHRENHEIT)){
		 string = Temperature.this.getValue() + " �F";
	  }
	  else if (Temperature.this.getUnits().equals(Units.KELVIN)){
		 string = Temperature.this.getValue() + " K";
	  }
		 return string; 
  }
  
  

  /**
   * In order to implement {@code Comparable}, we need to override
   * the {@code compareTo} method. 
   * @param temperature The {@code Temperature} to compare against
   * @return -1 if current object is less than {@code temperature}
   *          0 if both are equal
   *          1 if current object is greater than {@code temperature}
   */
  @Override
  public int compareTo (Temperature temperature) {
    //In order to simplify our comparisons, we decide to convert any temperature asked in Celcius, and then compare it (easier because both are in the same units) 
    Units newTemp = temperature.getUnits();
	temperature.changeUnits(Units.CELCIUS); //converts the new element to compare in Celcius
    Temperature.this.changeUnits(Units.CELCIUS); //converts the initial old temperature in Celcius. 
    if (temperature.getValue() < Temperature.this.getValue()){
    	temperature.changeUnits(newTemp);
    	return -1;
    }
    else if (temperature.getValue() > Temperature.this.getValue()){
    	temperature.changeUnits(newTemp);
    	return 1;
    }
    else{
    	temperature.changeUnits(newTemp);    	
    	return 0; //if the temperature to compare is neither greater or smaller than the initial temperature, than it means it is equal. Hence, we return 0. 
    }
  }

  /**
   * Indicates whether some object is "equal" to this one.
   * To maintain consistency, whenever a class overrdes 
   * {@code compareTo}, it must override {@code equals} so 
   * that
   * <pre>
   *   <code>o1.compareTo(o2) == 0</code> implies <code>o1.equals(o2) == true</code>
   * </pre>
   * See the API documentation of {@code Object} class for more details.
   */
  @Override
  public boolean equals (Object o) {
	 //Check if our object has the same address as the argument; this automatically means that both are equal.
	 if (this == o) return true;
	 //Verify if the object from the argument is null or of a different type that our object. In that case, it's an inequality.  
	 if (o == null || o.getClass() != this.getClass()) return false;
	 //We cast the argument in our type of object. We call it "other".
	 Temperature other = (Temperature)o;
	 //We use the method compareTo in order to verify if our object is the same as the "other" object, which is now of type Temperature. (we are basically comparing apples with apples now)
	 //If our objects aren't the same, we expect an answer different than 0 (as seen in the previous method).
	 if (this.compareTo(other) != 0) return false;
	 return true;   
  }

  /**
   * Return a hash code of the object. To maintain consistency,
   * whenever a class overrides {@code equals} it mush also override
   * {@code hashCode} in such a manner that 
   * <pre>
   *   <code>o1.equals(o2) == true</code> implies <code>o1.hashCode() == o2.hashCode()</code>
   * </pre>
   * See the API documentation of {@code Object} class for more details.
   */
  @Override
  public int hashCode() {
	//Initialise hash as an arbitrary constant.  
    int hash = 10;
    //Let's now consider all the variables used in the definition of our Temperature object: the unit and the value. 
    //We handle the first variable, "valTemp" (the value), which is a double.
    long bits = Double.doubleToLongBits(Temperature.this.valTemp);
    int var1 = (int)(bits ^ (bits >>> 32));
    //We represent the individual hash code of each variable combined with the initial one. 
    hash = 31 * hash + var1;
    //And then we handle the second variable, "uniTemp" (the unit), which is an object. 
    //In this case, we simply check if the object is null (if yes, var_code = 0), and if not, we recursively call the hashCode method to get the hash code.     
    int var2 = (null == Temperature.this.uniTemp ? 0 : Temperature.this.uniTemp.hashCode());
    //Again, we represent the individual hash code of each variable combined with the previous one.
    hash = 31 * hash + var2;
    //After computing the hash for each variable, we can return its final value.
    return hash;    
  }

  
  //TEST
  
  /*public static void main(String args[]){
  
  Temperature temperature1 = new Temperature(0, Units.CELCIUS);
  Temperature temperature2 = new Temperature(32, Units.FAHRENHEIT);
  Temperature temperature3 = new Temperature(273.15, Units.KELVIN);
  
  //Test getters
  System.out.println(temperature1.getValue());
  System.out.println(temperature1.getUnits());
  
  //Test toString method
  System.out.println(temperature1.toString());
  System.out.println(temperature2.toString());
  System.out.println(temperature3.toString());
  
  //Test changeUnits into Celcius:
  temperature1.changeUnits(Units.CELCIUS); System.out.println(temperature1.toString());
  temperature2.changeUnits(Units.CELCIUS); System.out.println(temperature2.toString());
  temperature3.changeUnits(Units.CELCIUS); System.out.println(temperature3.toString());
  
  //Test changeUnits into FAHRENHEIT:
  temperature1.changeUnits(Units.FAHRENHEIT); System.out.println(temperature1.toString());
  temperature2.changeUnits(Units.FAHRENHEIT); System.out.println(temperature2.toString());
  temperature3.changeUnits(Units.FAHRENHEIT); System.out.println(temperature3.toString());
  
  //Test changeUnits into KELVIN:
  temperature1.changeUnits(Units.KELVIN); System.out.println(temperature1.toString());
  temperature2.changeUnits(Units.KELVIN); System.out.println(temperature2.toString());
  temperature3.changeUnits(Units.KELVIN); System.out.println(temperature3.toString());
  
  //Test compareTo 
  
  //Verify if temperatures are equal:
  //System.out.println(temperature1.compareTo(temperature2));  System.out.println(temperature1.compareTo(temperature1));
  //System.out.println(temperature1.compareTo(temperature3));  System.out.println(temperature2.compareTo(temperature2));
  //System.out.println(temperature2.compareTo(temperature3));  System.out.println(temperature3.compareTo(temperature3));
  
  System.out.println(temperature1.compareTo(temperature2));
  System.out.println(temperature2.getUnits());
  
  //FfromC = Temperature.this.getValue() * 9 / 5 + 32;
  //KfromC = Temperature.this.getValue() + 273.15;
  //By looking at the equations we know that if we add 300 to the variable in Celcius (temperature1), this value will be greater than 32F (temperature2) and also than 273.15K (temperature3).
  //the same with the variable in Fahrenheit (temperature2), where the value will be greater than 273.15K (temperature3). Let's check this: 
  Temperature temperature4 = new Temperature(0 + 300, Units.CELCIUS);
  Temperature temperature5 = new Temperature(32 + 300, Units.FAHRENHEIT);
  
  System.out.println(temperature4.getValue() > temperature2.getValue());
  System.out.println(temperature4.getValue() > temperature3.getValue());
  System.out.println(temperature5.getValue() > temperature3.getValue());
  //Now we can check if our method compareTo works to verify if any temperature A is greater than any temperature B. We verify all cases possible, with the assumption that the 3 previous statements are true, as observed. 
  //We consider that A > B, it is clear that B < A, for the same A and B. Hence, we don't check those cases.
  System.out.println((temperature4.getValue() > temperature2.getValue()) && temperature2.compareTo(temperature4) == 1);
  System.out.println((temperature4.getValue() > temperature3.getValue()) && temperature3.compareTo(temperature4) == 1);
  System.out.println((temperature5.getValue() > temperature3.getValue()) && temperature3.compareTo(temperature5) == 1);
  //If we subtract 300, the value will be less than 32F and also than 273.15K.
  
  Temperature temperature6 = new Temperature(0 - 300, Units.CELCIUS);
  Temperature temperature7 = new Temperature(32 - 300, Units.FAHRENHEIT);
  
  System.out.println(temperature6.getValue() < temperature2.getValue());
  System.out.println(temperature6.getValue() < temperature3.getValue());
  System.out.println(temperature7.getValue() < temperature3.getValue());
  
  System.out.println((temperature6.getValue() < temperature2.getValue()) && temperature2.compareTo(temperature6) == -1);
  System.out.println((temperature6.getValue() < temperature3.getValue()) && temperature3.compareTo(temperature6) == -1);
  System.out.println((temperature7.getValue() < temperature3.getValue()) && temperature3.compareTo(temperature7) == -1);
  
  //Test equals method:
  
  //check if object has same address than argument:
  System.out.println(temperature1.equals(temperature1));
  
  //check if argument is null:
  System.out.println(temperature1.equals(null)); //if it is, return false
  
  //check if argument is of the correct type:
  System.out.println(temperature1.equals("hello!")); //if it is not, return false
  
  //To verify the equality between 2 values of the same type, please refer to the compareTo check. 
  
  //Test hashCode method
  
  //Check if we have equal hashes for equal objects: 
  System.out.println(temperature1.hashCode());
  System.out.println(temperature2.hashCode());
  
  //Check if we have the same hashes for multiple invocations during same executions:
  System.out.println(temperature6.hashCode());
  System.out.println(temperature7.hashCode());
  
  //Check if we have different hashes for different objects during different executions:
  System.out.println(temperature5.hashCode());
  
*/
}


