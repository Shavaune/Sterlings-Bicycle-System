import java.util.*;

class CompareByBrand implements Comparator<Bike>{

	public int compare(Bike b1, Bike b2)
	{
		String b1n = b1.getBrand().name();
		String b2n = b2.getBrand().name();
		
		for(int i=0;i<b1n.length();i++)
		{
		  if((int)b1n.charAt(i)>(int)b2n.charAt(i))
			return 1;
		  else
			if((int)b1n.charAt(i)<(int)b2n.charAt(i))
			  return -1;
		}
		return 0;
	}
}