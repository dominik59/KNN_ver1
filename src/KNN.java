import java.lang.reflect.Array;
import java.security.GuardedObject;
import java.util.Arrays;
import java.util.Comparator;

public class KNN {
	static Double[][] learning_set={{63.529},
									{63.529},
									{64.528},
									{64.528},
									{61.445},
									{61.434},
									{60.423},
									{61.434},
									{56.040},
									{56.040},
									{56.040},
									{57.040},};
	
	static String[][] classes={	{"ajayeb"},
								{"ajayeb"},
								{"ajayeb"},
								{"ajayeb"},
								{"aday jmal"},
								{"aday jmal"},
								{"aday jmal"},
								{"aday jmal"},
								{"amreaj"},
								{"amreaj"},
								{"amreaj"},
								{"amreaj"},};
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double[] query = {63.529,63.529,56.040,63.529};
		//System.out.println(learning_set.length);
		//System.out.println(classes[0][0]);
		ClosestObject[][] znalezione_obiekty = Find_closest_distance(query, learning_set, classes);
	}

	private static ClosestObject[][] Find_closest_distance(Double[] query,Double[][] learning_set,String[][] classes)
	{		
		System.out.println(query.length);
		System.out.println(learning_set.length);
		ClosestObject[][] closestobj=new ClosestObject[query.length][learning_set.length];
		
		for(int i=0; i<query.length;i++)
		{
			for(int j=0;j<learning_set.length;j++)
			{	
				closestobj[i][j]=new ClosestObject();
				closestobj[i][j].distance=Math.abs(query[i]-learning_set[j][0]);
				closestobj[i][j].classes=classes[j][0];
			}	
			
		}
		for(int k=0;k<query.length;k++)
		{
			Arrays.sort(closestobj[k], new Comparator<ClosestObject>() {
				@Override
				public int compare(ClosestObject entry1, ClosestObject entry2) {
					ClosestObject time1 = entry1;
					// System.out.println(time1);
					ClosestObject time2 = entry2;
					// System.out.println(time2.distance);
					return time1.distance.compareTo(time2.distance);
				}
			});
		}
		
		for(int i=0;i<closestobj.length;i++)
		{
			for(int j=0;j<closestobj[i].length;j++)
			{
				System.out.println(closestobj[i][j].distance + " " + closestobj[i][j].classes);
			}
			System.out.println("\n\n\n");
		}
		
		return null;
		
	}
}
