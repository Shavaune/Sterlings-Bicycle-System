import java.util.Comparator;

class CompareByID implements Comparator<Bike>{
	public int compare(Bike b1, Bike b2)
    {
		if (b1.getID()>b2.getID())
		  return 1;
		else{
		  if (b1.getID()<b2.getID())
			return -1;
		  else
			return 0;
		}
	}
}