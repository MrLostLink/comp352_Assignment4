package comp352_Assignment4;

import java.util.Scanner;

public class HashTable {
	
	private Boolean quadratic;
	private String [] hashTable;
	
	private int rehashNumber = 0;
	private int MAX_SIZE_ARRAY = 101;
	
	private double entryCount= 0;
	private double rehashFactor = 2;
	private double DEFAULT_LOAD_FACTOR = 0.75;
	
	Scanner key = new Scanner(System.in);
	
	public HashTable(){
		hashTable = new String[MAX_SIZE_ARRAY];
		System.out.println("new HashTable created\n");
		quadratic = false;
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
				if(quadratic)
					QuadraticSort(Integer.toString(hashedKey), value);
				else
					DoubleSort(Integer.toString(hashedKey), value);
			entryCount++;
			}
			
	}
	
	public void search(String value){
		
		/*Enter a string and check if its in the array 
		 */
	}
	
	public void remove(String value){
		
		/* convert value to key then find the key in the array
		 * then remove that value  
		 */
		
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
		total = (((4307*total)+997) % 103) % 101 ;
		return total;
}
	
	public void QuadraticSort(String key, String value){
		boolean addedValue = false;
		int newKey;
		int i = 0;
		
		while(addedValue==false){
				
			newKey = ((int)(Integer.parseInt(key) + Math.pow(i, 2))) % MAX_SIZE_ARRAY;
				
				if(hashTable[newKey]== null){
					hashTable[newKey]= value;
					System.out.println("Value " + value + " stored in Key " + newKey);
					addedValue = true;
					break;
				}
			
			i++;
		}
	}
	
	public void DoubleSort(String key, String value){
		int hashedKey = hashFunc(key);
		int doubleSort =(( 107 -(hashedKey % 103) % 101));
		
		boolean addedValue = false;
		int newKey;
		int i =1;  
		
		while(addedValue==false){
			
				newKey = (doubleSort*i + hashedKey) % MAX_SIZE_ARRAY;
				if(hashTable[newKey]== null){
					hashTable[newKey] = value;
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
		System.out.println("Default is: "+DEFAULT_LOAD_FACTOR);
		System.out.println("Enter new between 0 and 1");
		
		loadFactor= key.nextDouble();
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

		}
		else if(factorOrNumber instanceof Double){
			
			double i = ((Double) factorOrNumber).doubleValue();
			
			if((entryCount/(MAX_SIZE_ARRAY*i)) < currentLoadFactor()){
				rehashFactor = i;
				rehashNumber = 0;
				System.out.println("New Rehash Factor: " + rehashFactor);
			}
		}
	}

	
}