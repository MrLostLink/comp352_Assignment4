package comp352_Assignment4;

public class HashTable {


	
	int tableSize= 100;
	Hash [] hTable;
	Hash [] vTable;
	

	public void HashTable(){
		hTable = new Hash[100];
		for(int i=0; i< 100; i++){
			hTable[i] = null;
		}
	}
	
	// add entry to table
	public void add(String key, String value) {

		int i = hashFunc(key); // replace this with hash function

		while (hTable[i] != null) {
			hTable[i] = new Hash(key, value);
		}

		System.out.println(" Key is: " + i + " and value is: " + value);

		// add methods here for collision
		// double
		// qaudratic

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

		int j = hashFunc(value);

		for (int i = 0; i < tableSize; i++) {
			if (hTable[i].equals(j)) {
				System.out.println("Found " + value + " at: " + i);
			} else
				System.out.println("Non existence value");
		}

	}
	
	public int hashFunc(String value){

		
		int total = 0;
		int length = value.length();
		int code=0;
		
		for(int i = 0; i< length; i++){
			code = code + ((value.charAt(i)) * (length-i));
			//code = (((4307*code)+997) % 43977) % 43979 ;
			System.out.println(code);
				total += code;
		}
		System.out.println(total);
		total = (((4307*total)+997) % 43977) % 43979 ;
		System.out.println(total);
		return code;
}
		
		
	
	public void QuadraticSort( String key, String value){
		
	}
	
	public void DoubleSort( String key, String value){
		
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
	

	
	






public class Hash{
	
	public String value;
	public String key;
	
	public Hash (String value, String key){
		value=this.value;
		key= this.key;
		
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	
	
	
}

}

