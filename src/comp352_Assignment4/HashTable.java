package comp352_Assignment4;

import java.math.BigInteger;
import java.util.Scanner;

public class HashTable {
	
	private Boolean quadratic;
	private String [] hashTable;
	private int rehashNumber = 0;
	private double MAX_SIZE_ARRAY = 101;
	
	
	private double entryCount= 0;
	private double rehashFactor = 2;
	private double DEFAULT_LOAD_FACTOR = 0.75;
	private int collided=0;
	
	Scanner key = new Scanner(System.in);
	
	public HashTable(){
		hashTable = new String[(int) MAX_SIZE_ARRAY];
	
		System.out.println("new HashTable created\n");
		quadratic = true;
		
	}
	
	public double size(){
		return entryCount;
	}
	// add entry to table
	public void put(String key, String value) {
		boolean addedValue = false;
		int hashedKey = hashFunc(key); // replace this with hash function
		
		if(hashTable[hashedKey]== null){
				hashTable[hashedKey]= value;
				addedValue = true;
				System.out.println("Value " + value + " stored in Key " + hashedKey);
				entryCount++;
			}
			
		if(addedValue == false){
			if(quadratic){
				QuadraticSort(Integer.toString(hashedKey), value);
				collided++;
			}
			else{
				DoubleSort(Integer.toString(hashedKey), value);
				collided++;
			}
			entryCount++;
			}
		
		if(currentLoadFactor() > DEFAULT_LOAD_FACTOR){
			
			if(rehashNumber==0){
				String[] temp = hashTable;
				double previousSize = MAX_SIZE_ARRAY;
				MAX_SIZE_ARRAY = MAX_SIZE_ARRAY * rehashFactor;
				
				hashTable = new String[(int) MAX_SIZE_ARRAY];
				for(int i=0; i<previousSize;i++){
					if(temp[i]!=null);
						hashTable[i] = temp[i];
				}
			}
			
			if(rehashFactor==0){
				String[] temp = hashTable;
				double previousSize = MAX_SIZE_ARRAY;
				MAX_SIZE_ARRAY = MAX_SIZE_ARRAY + rehashNumber;
				
				hashTable = new String[(int) MAX_SIZE_ARRAY];
				for(int i=0; i<previousSize;i++){
					if(temp[i]!=null);
						hashTable[i] = temp[i];
				}
				
			}
			
			
		}
	}
	
	public void remove(String key) {

		for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
			if (hashTable[i] == key) {
				hashTable[i] = null;
				System.out.println("Key " + key + " removed.");
				entryCount--;
			}
		}

	}

	public void getString(String key) {

		for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
			if (hashTable[i] == key) {

				System.out.println("Key " + key + " found at: " + i);
			}
		}

	}

	public void printHashtableStatistics() {

		boolean run = true;

		System.out.println("Please select what you would like to print");
		while (run) {

			System.out.println("" + "1- Set Load Factor \n" + "2- Increase table by factor or number \n"
					+ "3- Choose collision handling \n" + "4- Set empty marker scheme \n" + "5- Um ye...\n"
					+ "6- Size of table \n" + "7- The load percentage \n" + "8- Number of collisons \n"
					+ "9- Maximum collision for one cell \n" + "10-Average number of collisions over all cells \n"
					+ "0- Exit");

			switch (key.nextInt()) {

			case 1:

				System.out.println("Default is: " + DEFAULT_LOAD_FACTOR);
				System.out.println("Enter new between 0 and 1");
				double loadFactor = key.nextDouble();
				this.setRehashTreshold(loadFactor);
				break;

			case 2:

				System.out.println("Press \"N\" to adjust the rehash Number or Press \"F\" to adjust the rehash Factor");
				String ans = key.next(); 
				while(true){
					if(ans.equalsIgnoreCase("n")){
						System.out.print("Enter rehash Number: ");
						int a = key.nextInt();
						setRehashFactor(a);
						break;
					}
					if(ans.equalsIgnoreCase("f")){
						System.out.print("Enter rehash Factor: ");
						double b = key.nextDouble();
						setRehashFactor(b);
						break;
					}
					else
						System.out.println("No...");
				}
				
				break;

			case 3:
				
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
				
			case 5:
				System.out.println("Ye");
				break;
			
			case 6:
				System.out.println("Size of table is: " + (MAX_SIZE_ARRAY - 1));
				break;

			case 7:
				System.out.println("Load Percentage is: " + (currentLoadFactor() * 100));
				break;

			case 8:
				System.out.println("Number of collisions: " + collided);

			case 0:
				System.out.println("Now exiting");
				run = false;
				break;

			}

		}

	}
	
	public void resetHashtableStatistics(){
		for (int i = 0; i < MAX_SIZE_ARRAY; i++) {
			if(hashTable[i] != null){
				hashTable[i]= null;
			}
		}		
	}
	
	public int hashFunc(String value){

		
		int total = 0;
		int length = value.length();
		int code=0;
		
		for(int i = 0; i< length; i++){
			code = code + ((value.charAt(i)) * (length-i));
			//code = (((4307*code)+997) % 43977) % 43979 ;
			//System.out.println(code);
				total += code;
		}
		//System.out.println(total);
		
		BigInteger b = new BigInteger(String.valueOf((int)MAX_SIZE_ARRAY));
		BigInteger c = b.nextProbablePrime();
		total = (((437*total)+997) % c.intValue()) % (int)MAX_SIZE_ARRAY ;
		return total;
}
	
	public void QuadraticSort(String key, String value){
		boolean addedValue = false;
		double newKey;
		int i = 0;
		
		while(addedValue==false){
				
			newKey = ((int)(Integer.parseInt(key) + Math.pow(i, 2))) % MAX_SIZE_ARRAY;
				
				if(hashTable[(int) newKey]== null){
					hashTable[(int) newKey]= value;
					System.out.println("Value " + value + " stored in Key " + newKey);
					addedValue = true;
					break;
				}
			
			i++;
		}
	}
	
	public void DoubleSort(String key, String value){
		
		BigInteger b = new BigInteger(String.valueOf((int)MAX_SIZE_ARRAY));
		BigInteger c = b.nextProbablePrime();

		int hashedKey = hashFunc(key);
		int doubleSort =(( 107 -(hashedKey % c.intValue()) % (int) MAX_SIZE_ARRAY));
		
		boolean addedValue = false;
		double newKey;
		int i =1;  
		
		while(addedValue==false){
			
				newKey = (doubleSort*i + hashedKey) % MAX_SIZE_ARRAY;
				if(hashTable[(int) newKey]== null){
					hashTable[(int) newKey] = value;
					addedValue = true;
					System.out.println("Value " + value + " stored in Key " + newKey);
			}
				i++;
		}
		
		
	}
	
	public void clone(HashTable cloning){
	
		for(int i = 0; i< hashTable.length; i++){
			hashTable[i] = cloning.hashTable[i];
		}
		quadratic = cloning.quadratic;
		entryCount = cloning.entryCount;
		DEFAULT_LOAD_FACTOR = cloning.DEFAULT_LOAD_FACTOR;
		rehashNumber = cloning.rehashNumber;
		rehashFactor = cloning.rehashFactor;
		MAX_SIZE_ARRAY = cloning.MAX_SIZE_ARRAY;
	}
	
	public void keySet(){
		System.out.print("\nKeys: {");
		int count=0;;
		
		for(int i=0; i<MAX_SIZE_ARRAY; i++){
			if(hashTable[i] != null){
				System.out.print(i);
				count++;
			}
			if(count != entryCount && hashTable[i] != null)
				System.out.print(",");
			
		}
		System.out.println("}\n");
}

	public void entrySet(){
			System.out.println("Set result:");
			
			for(int i=0; i<MAX_SIZE_ARRAY; i++){	
				System.out.println(i + "= " + hashTable[i]);
			}
		}
	
	public double currentLoadFactor(){
		return entryCount/MAX_SIZE_ARRAY;
	}
	
	public void setRehashTreshold(double loadFactor){
		
		if((loadFactor >= 0 && loadFactor <= 1) && (loadFactor >= currentLoadFactor())){
			System.out.println("ThreshFactor has change to "+loadFactor);
			}else
				System.out.println("Thresh is not changed");
	
	}
	
	public <T> void setRehashFactor(T factorOrNumber){
		
		if(factorOrNumber instanceof Integer){
			
			int i = ((Integer) factorOrNumber).intValue();
			
			if(entryCount/(MAX_SIZE_ARRAY+i) < currentLoadFactor()){
				rehashNumber = i;
				rehashFactor = 0;
				System.out.println("New Rehash Number: " + rehashNumber);
			}
			else
				System.out.println("Invalid rehash Number! \nBack to Menu!");

		}
		else if(factorOrNumber instanceof Double){
			
			double i = ((Double) factorOrNumber).doubleValue();
			
			if((entryCount/(MAX_SIZE_ARRAY*i)) < currentLoadFactor()){
				rehashFactor = i;
				rehashNumber = 0;
				System.out.println("New Rehash Factor: " + rehashFactor);
			}
			else
				System.out.println("Invalid rehash Factor! \nBack to Menu!");
		}
	}
}