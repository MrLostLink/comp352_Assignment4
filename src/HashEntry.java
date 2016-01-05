
public class HashEntry {

	private String value;
	private String collisionValue;
	private int numOfCollisions = 0;
	
	
	
	public HashEntry(){
	}
	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getCollisionValue() {
		return collisionValue;
	}


	public void setCollisionValue(String collisionValue) {
		this.collisionValue = collisionValue;
	}


	public int getNumOfCollisions() {
		return numOfCollisions;
	}


	public void setNumOfCollisions(int numOfCollisions) {
		this.numOfCollisions = numOfCollisions;
	}


	
	
	
	
	
}
