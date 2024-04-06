public class Bike{
	private Brand brand;
	private String color;
	private int id;
	private Status status;
	
	public Bike(int id, Status status, Brand brand, String color){
		this.id=id;
		this.brand=brand;
		this.status=status;
		this.color=color;
	}
	
	
	/** 
	 * @return int
	 */
	public int getID(){
		return id;
	}
	
	
	/** 
	 * @return String
	 */
	public String getColor(){
		return color;
	}
	
	
	/** 
	 * @return Brand
	 */
	public Brand getBrand(){
		return brand;
	}
	
	
	/** 
	 * @return Status
	 */
	public Status getStatus(){
		return status;
	}
	
	public void makeBike(){
	}
	
	
	/** 
	 * @return String
	 */
	public String toString(){
		return ""+getID()+" "+getStatus().name()+" "+getBrand().name()+" "+getColor();
	}
}