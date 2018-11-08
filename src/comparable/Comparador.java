package comparable;
import java.util.Comparator;

public class Comparador  implements Comparator{

	public int compare(Object arg0, Object arg1) {
		return arg0.toString().toLowerCase().compareTo(arg1.toString().toLowerCase());
	}
	
}
