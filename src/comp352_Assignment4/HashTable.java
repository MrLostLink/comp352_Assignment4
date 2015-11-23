package comp352_Assignment4;

public class HashTable {
	
	private int MAX_SIZE_ARRAY = 101;
	private int BUCKET_SIZE = 10;
	int entryCount= 0;
	String [][] hashTable;
	Boolean quadratic;

	
	public HashTable(){
		hashTable = new String[MAX_SIZE_ARRAY][BUCKET_SIZE];
		System.out.println("new HashTable created");
		quadratic = true;
	}
	
	
	// add entry to table
	public void add(String key, String value) {
		boolean addedValue = false;
		int hashedKey = hashFunc(key); // replace this with hash function
		
			for(int j = 0; j< BUCKET_SIZE; j++){
				System.out.println(j + " " + hashedKey);
				if(hashTable[hashedKey][j]== null){
					hashTable[hashedKey][j] = value;
					addedValue = true;
					break;
				}
			}
			
			if(addedValue == false){
				if(quadratic)
					QuadraticSort(Integer.toString(hashedKey), value);
				else
					DoubleSort(Integer.toString(hashedKey), value);
			}
			
	}
	
	public void DoubleSort(String key, String value){
		int hashedKey = hashFunc(key);
		int doubleSort =(( 107 -(hashedKey % 103) % 101));
		
		boolean addedValue = false;
		int newKey;
		int i =1;  
		
		while(addedValue==false){
			
			for(int j = 0; j< BUCKET_SIZE; j++){
				newKey = (doubleSort*i + hashedKey) % MAX_SIZE_ARRAY;
				System.out.println(j + " " + key);
				if(hashTable[newKey][j]== null){
					hashTable[newKey][j] = value;
					addedValue = true;
					break;
				}
			}
			i++;
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
	
	public void getIndex(String value) {

		/*
		 * convert string to key and search the key in the array and find the
		 * index
		 * 
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
		System.out.println(total);
		return total;
}
		
		
	
	public void QuadraticSort(String key, String value){
		boolean addedValue = false;
		int newKey;
		int i = 0;
		
		while(addedValue==false){
			
			for(int j = 0; j< BUCKET_SIZE; j++){
				
				newKey = ((int)(Integer.parseInt(key) + Math.pow(i, 2))) % MAX_SIZE_ARRAY;
				
				System.out.println(j + " " + key);
				if(hashTable[newKey][j]== null){
					hashTable[newKey][j] = value;
					addedValue = true;
					break;
				}
			}
			i++;
		}
	}
	
	
	
	
	public int stringVal(String key) {
		int total = 0;
		int ascii;

		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			int val = (int) c;
			total = total + c;

		}

		return total;

	}
}