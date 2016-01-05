
import java.math.BigInteger;
import java.util.Scanner;

/**************************************************************************
 * Assignment 4 - COMP 352 - FALL 2015 Prof: Dr. Aiman Hanna
 * 
 * Students: Mehal Patel ID - 26789234 Mandeep Tahim ID - 26935478
 * 
 * Notes: [INCLUDE IF ANYTHING]
 * 
 **************************************************************************
 */

public class HashTable {

	// Declaration of Variables
	private Boolean quadratic;
	private HashEntry[] hashTable;
	private int rehashNumber = 0;
	private double MAX_SIZE_ARRAY = 101;

	private double entryCount = 0;
	private double rehashFactor = 2;
	private double DEFAULT_LOAD_FACTOR = 0.75;
	private int collided = 0;

	Scanner key = new Scanner(System.in);

	// Constructor Class; Quadratic as Default and ReHashFactor(2) as Default.
	public HashTable() {
		hashTable = new HashEntry[(int) MAX_SIZE_ARRAY];
		System.out.println("New HashTable initialized\n");
		quadratic = true;

	}

	// Returns the size of the Table, elements that are not null
	public double size() {
		return entryCount;
	}

	/*
	 * Method that adds Key and Value to the table. If there is a given value at
	 * a certain key in the table, a collision happens. Depending on the method
	 * chosen by default or specified by the user, the collision is then handled
	 * either through the quadratic or double method.
	 * 
	 * Finally, if the new element added causes the Load Factor to surpass the
	 * Default or Assigned LF by the User, the Table will then increase in size
	 * to accommodate a lower LF
	 */
	public void put(String key, String value) {
		boolean addedValue = false;
		int hashedKey = hashFunc(key);

		// Verifies that no value stored @Key
		if (hashTable[hashedKey] == null) {
			hashTable[hashedKey] = new HashEntry();
			hashTable[hashedKey].setValue(value);
			addedValue = true;
			System.out.println("Value " + value + " stored in Key " + hashedKey);
			entryCount++;
		}

		// If Value wasn't added, collision has occurred
		if (addedValue == false) {
			hashTable[hashedKey].setNumOfCollisions(hashTable[hashedKey].getNumOfCollisions() + 1);
			if (quadratic) {
				QuadraticSort(Integer.toString(hashedKey), value);

			} else {
				DoubleSort(Integer.toString(hashedKey), value);

			}
			entryCount++;
		}
		// Adjust Table Size to accommodate LF if needed
		if (currentLoadFactor() > DEFAULT_LOAD_FACTOR) {

			if (rehashNumber == 0) {
				HashEntry[] temp = hashTable;
				double previousSize = MAX_SIZE_ARRAY;
				MAX_SIZE_ARRAY = MAX_SIZE_ARRAY * rehashFactor;

				hashTable = new HashEntry[(int) MAX_SIZE_ARRAY];
				for (int i = 0; i < previousSize; i++) {
					if (temp[i] != null)
						;
					hashTable[i] = temp[i];
				}
			}

			if (rehashFactor == 0) {
				HashEntry[] temp = hashTable;
				double previousSize = MAX_SIZE_ARRAY;
				MAX_SIZE_ARRAY = MAX_SIZE_ARRAY + rehashNumber;

				hashTable = new HashEntry[(int) MAX_SIZE_ARRAY];
				for (int i = 0; i < previousSize; i++) {
					if (temp[i] != null)
						;
					hashTable[i] = temp[i];
				}

			}

		}
	}

	// Searches for element and then removes element.
	// If "R" scheme is chosen, then the value will have a hyphen at the
	// beginning of it
	public void remove(String value) {

		for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
			if (hashTable[i] != null) {

				if (hashTable[i].getValue().equals(value)) {
					hashTable[i] = null;
					System.out.println("Value " + value + " removed.");
					entryCount--;
				}
			}
		}
	}
	
	public void remove10k() {
		for (int i = 0; i <= 10000; i++) {
			if (hashTable[i] != null) {
				if (hashTable[i] != null)
					hashTable[i] = null;

			}
		}
	}
	// Prints the Value and it's corresponding Key on the HashTable.
	public void getString(String value) {

		for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
			if (hashTable[i] != null) {
				if (hashTable[i].getValue().equals(value)) {

					System.out.println("Value " + value + " found at: " + i);
				}
			}
		}
	}

	// Prints out various statistics of HashTable
	public void printHashtableStatistics() {
		boolean run = true;

		// Prompts User of Selection
		System.out.println("Please select what you would like to print");
		while (run) {

			System.out.println("" + "1- Set Load Factor \n" + "2- Increase table by factor or number \n"
					+ "3- Choose collision handling \n" + "4- Set empty marker scheme \n" + "5- Size of table \n"
					+ "6- The load percentage \n" + "7- Number of collisons \n" + "8- Maximum collision for one cell \n"
					+ "9-Average number of collisions over all cells \n" + "10-Reset the table \n" + "0- Exit");

			switch (key.nextInt()) {

			case 1: // Sets LoadFactor
				System.out.println("Default is: " + DEFAULT_LOAD_FACTOR);
				System.out.println("Enter new between 0 and 1");
				double loadFactor = key.nextDouble();
				this.setRehashTreshold(loadFactor);
				break;

			case 2: // Sets Rehash Number or Factor, calling on
					// setRehashFactor() method
				System.out
						.println("Press \"N\" to adjust the rehash Number or Press \"F\" to adjust the rehash Factor");
				String ans = key.next();
				while (true) {
					if (ans.equalsIgnoreCase("n")) {
						System.out.print("Enter rehash Number: ");
						int a = key.nextInt();
						setRehashFactor(a);
						break;
					}
					if (ans.equalsIgnoreCase("f")) {
						System.out.print("Enter rehash Factor: ");
						double b = key.nextDouble();
						setRehashFactor(b);
						break;
					} else
						System.out.println("No...");
				}

				break;

			case 3: // Sets Collision type

				boolean input = true;
				while (input) {
					System.out.println("Choose collision type handling, press Q for quadratic or D for double");
					String b = key.next();
					if (b.equals("D")) {
						quadratic = false;
						break;
					} else if (b.equals("Q")) {
						quadratic = true;
						break;
					} else
						System.out.println(" No...");
				}
				break;

			case 4:

				break;

			case 5: // Prints out the Size of the Table, and not the entry
					// count.
				System.out.println("Size of table is: " + (MAX_SIZE_ARRAY - 1));
				break;

			case 6: // Determines current load factor in percentage
				System.out.println("Load Percentage is: " + (currentLoadFactor() * 100));
				break;

			case 7: // Determines the maximum quantity of collisions throughout
					// the runtime
				System.out.println("Number of collisions: " + collided);
				break;

			case 8:// Determines which element has the highest number of
					// Collisions, based on the variable for HashEntry objects
				int highColl = 0;
				int highKey = 0;
				for (int i = 0; i < MAX_SIZE_ARRAY - 1; i++) {
					if (hashTable[i] != null && hashTable[i].getNumOfCollisions() > highColl) {
						highColl = hashTable[i].getNumOfCollisions();
						highKey = i;
					}
				}
				System.out.println("Highest Collission is at Key: " + highKey + " with " + highColl + " collisions.");
				break;

			case 9: // Determines average amount of Collisions per cells with
					// Collisions
				double numOfCells = 0;
				for (int i = 0; i < MAX_SIZE_ARRAY - 1; i++) {
					if (hashTable[i] != null && hashTable[i].getNumOfCollisions() > 0) {
						numOfCells++;
					}
				}
				System.out.printf("With " + numOfCells + " cells that have a total of " + collided
						+ ". \nAverage Collisions per cell with Collision: %.2f \n", collided / numOfCells);
				break;

			case 10: // Resets the HashTable, as if new Table was created.
				this.resetHashtableStatistics();
				System.out.println("Table has been reset.");
				break;
			case 0: // Exit loop
				System.out.println("Now exiting");
				run = false;
				break;

			}

		}

	}

	// Method rendering Table empty
	public void resetHashtableStatistics() {
		for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
			if (hashTable[i] != null) {
				hashTable[i] = (null);
			}
		}
	}

	// Hashes Value into Key based on algorithm supplied in course
	public int hashFunc(String value) {

		int total = 0;
		int length = value.length();
		int code = 0;

		for (int i = 0; i < length; i++) {
			code = code + ((value.charAt(i)) * (length - i));

			total += code;
		}

		BigInteger b = new BigInteger(String.valueOf((int) MAX_SIZE_ARRAY));
		BigInteger c = b.nextProbablePrime();
		total = (((437 * total) + 997) % c.intValue()) % (int) MAX_SIZE_ARRAY;
		return total;
	}

	// Sorts through the HashTable, if there was a collision, in order to find
	// next appropriate spot for element to be stored in
	// nextKey = Key+i^2 mod N
	public void QuadraticSort(String key, String value) {
		boolean addedValue = false;
		double newKey;
		int i = 0;

		while (addedValue == false) {

			newKey = ((int) (Integer.parseInt(key) + Math.pow(i, 2))) % MAX_SIZE_ARRAY;

			if (hashTable[(int) newKey] == null) {
				hashTable[(int) newKey] = new HashEntry();
				hashTable[(int) newKey].setValue(value);
				System.out.println("Value " + value + " stored in Key " + newKey);
				addedValue = true;
				break;
			}
			hashTable[(int) newKey].setNumOfCollisions(hashTable[(int) newKey].getNumOfCollisions() + 1);
			collided++;
			i++;
		}
	}

	// Sorts through the HashTable, if there was a collision, in order to find
	// next appropriate spot for element to be stored in
	// nextKey = hashedTwiceValue * i + hashedKey mod N
	public void DoubleSort(String key, String value) {

		BigInteger b = new BigInteger(String.valueOf((int) MAX_SIZE_ARRAY));
		// BigInteger c = b.nextProbablePrime();

		int hashedKey = hashFunc(key);
		// int doubleSort = ((17 - (hashedKey % c.intValue()) % (int)
		// MAX_SIZE_ARRAY));

		int doubleSort = ((17 - (hashedKey % 13) % (int) MAX_SIZE_ARRAY));

		boolean addedValue = false;
		double newKey;
		int i = 1;

		while (addedValue == false) {

			newKey = (doubleSort * i + hashedKey) % MAX_SIZE_ARRAY;
			if (hashTable[(int) newKey] == null) {
				hashTable[(int) newKey] = new HashEntry();
				hashTable[(int) newKey].setValue(value);
				addedValue = true;
				System.out.println("Value " + value + " stored in Key " + newKey);
			}

			hashTable[(int) newKey].setNumOfCollisions(hashTable[(int) newKey].getNumOfCollisions() + 1);
			collided++;
			i++;
		}

	}

	// Clone method
	public void clone(HashTable cloning) {

		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = cloning.hashTable[i];
		}
		quadratic = cloning.quadratic;
		entryCount = cloning.entryCount;
		DEFAULT_LOAD_FACTOR = cloning.DEFAULT_LOAD_FACTOR;
		rehashNumber = cloning.rehashNumber;
		rehashFactor = cloning.rehashFactor;
		MAX_SIZE_ARRAY = cloning.MAX_SIZE_ARRAY;
	}

	// Prints non-empty Keys that contain a value.
	public void keySet() {
		System.out.print("\nKeys: {");
		int count = 0;
		;

		for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
			if (hashTable[i] != (null)) {
				System.out.print(i);
				count++;
			}
			if (count != entryCount && hashTable[i] != null)
				System.out.print(",");

		}
		System.out.println("}\n");
	}

	// Prints Keys and their respective Values
	public void entrySet() {
		System.out.println("Set result:");

		for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
			if (hashTable[i] == null)
				System.out.println(i + "= null");
			else
				System.out.println(i + "= " + hashTable[i].getValue());
		}
	}

	// Prints current Load Factor which corresponds to n/N where n=entries and
	// N= size of Table
	public double currentLoadFactor() {
		return entryCount / MAX_SIZE_ARRAY;
	}

	// Defines the rehash Treshold, ergo when to increase the size of the Table
	public void setRehashTreshold(double loadFactor) {

		if ((loadFactor >= 0 && loadFactor <= 1) && (loadFactor >= currentLoadFactor())) {
			System.out.println("ThreshFactor has change to " + loadFactor);
		} else
			System.out.println("Thresh is not changed");

	}

	// Defines Factor or Number to increase the Table Size when Load Factor is
	// surpassed.
	public <T> void setRehashFactor(T factorOrNumber) {

		if (factorOrNumber instanceof Integer) {

			int i = ((Integer) factorOrNumber).intValue();

			if (entryCount / (MAX_SIZE_ARRAY + i) < currentLoadFactor()) {
				rehashNumber = i;
				rehashFactor = 0;
				System.out.println("New Rehash Number: " + rehashNumber);
			} else
				System.out.println("Invalid rehash Number! \nBack to Menu!");

		} else if (factorOrNumber instanceof Double) {

			double i = ((Double) factorOrNumber).doubleValue();

			if ((entryCount / (MAX_SIZE_ARRAY * i)) < currentLoadFactor()) {
				rehashFactor = i;
				rehashNumber = 0;
				System.out.println("New Rehash Factor: " + rehashFactor);
			} else
				System.out.println("Invalid rehash Factor! \nBack to Menu!");
		}
	}
}