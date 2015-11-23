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
	public void put(String key, String value) {
		boolean addedValue = false;
		int hashedKey = hashFunc(key); // replace this with hash function
		
			for(int j = 0; j< BUCKET_SIZE; j++){
				if(hashTable[hashedKey][j]== null){
					hashTable[hashedKey][j] = value;
					addedValue = true;
					System.out.println("Key: "+ hashedKey + ", index: " + j + ", Value: " + value);
					break;
				}
			}
			
			if(addedValue == false){
				if(quadratic)
					QuadraticSort(Integer.toString(hashedKey), value);
				else
					DoubleSort(Integer.toString(hashedKey), value);
			}
			entryCount++;
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
				if(hashTable[newKey][j]== null){
					hashTable[newKey][j] = value;
					addedValue = true;
					System.out.println("Key: "+ hashedKey + ", index: " + j + ", Value: " + value);
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
		
	public String[][] clone(){
		String[][] cloned = new String[MAX_SIZE_ARRAY][BUCKET_SIZE];
		
		for(int i=0; i<hashTable.length; i++)
			for(int j=0; j<hashTable[i].length; j++)
				cloned[i][j] = hashTable[i][j];
		
		return cloned;
	}

	public void keySet(){
		System.out.print("Keys: {");
		int count=0;;
		for(int i=0; i<MAX_SIZE_ARRAY; i++){
			for(int j=0; j<BUCKET_SIZE; j++){
				if(hashTable[i][j] != null)
					count++;
			}
			if(count!=0){
				System.out.print(i);
					if(i+1!=MAX_SIZE_ARRAY)
						System.out.print(", ");
			
		}
			count=0;
	}
		System.out.println("}");
}

	public void entrySet(){
			System.out.println("Set result:");
			
			for(int i=0; i<MAX_SIZE_ARRAY; i++){	
				System.out.print("(" + i + "= ");
				for(int j=0; j<BUCKET_SIZE; j++){
						System.out.print(hashTable[i][j]);
					if(j+1 == BUCKET_SIZE)
						System.out.println(") ");
					else
						System.out.print(", ");
				}
			}
		}
	
	
	public void QuadraticSort(String key, String value){
		boolean addedValue = false;
		int newKey;
		int i = 0;
		
		while(addedValue==false){
			
			for(int j = 0; j< BUCKET_SIZE; j++){
				
				newKey = ((int)(Integer.parseInt(key) + Math.pow(i, 2))) % MAX_SIZE_ARRAY;
				
				if(hashTable[newKey][j]== null){
					hashTable[newKey][j] = value;
					System.out.println("Key: "+ newKey + ", index: " + j + ", Value: " + value);
					addedValue = true;
					break;
				}
			}
			i++;
		}
	}
	
}