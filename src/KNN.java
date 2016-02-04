import java.lang.reflect.Array;
import java.security.GuardedObject;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

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
		Integer k=new Integer(2);
		HashMap<String, Integer> counting = k_neighbours_selection(query,znalezione_obiekty,k);
		String final_decision=obtain_final_decision(counting);
//		System.out.println(counting);
		System.out.println("Nowo wprowadzona wartość przynależy do klasy: \"" + final_decision + "\"");
	}

	private static String obtain_final_decision(HashMap<String, Integer> counting) {
		String temp_final_decision=new String();
		Integer temp_max_value=0;
		for(Map.Entry<String, Integer> entry : counting.entrySet())
		{
			String temp_s=entry.getKey();
			Integer temp_v=entry.getValue();
//			System.out.println(temp_s);
//			System.out.println(temp_v);
			if(temp_max_value<temp_v)
			{
				temp_max_value=temp_v;
				temp_final_decision=temp_s;
			}
		}
		if(temp_max_value==0)
		{
			return "Nic nie znaleziono";
		}
		else
		{
			return temp_final_decision;
		}
		
	}

	private static HashMap<String, Integer> k_neighbours_selection(Double[] query, ClosestObject[][] znalezione_obiekty, Integer k) {
		//dla każdego wiersza wybierz po k kolum i sprawdź czy większość należy do jednej klasy jeżeli nie to wybierz losowo
		HashMap<String, Integer> mapa= new HashMap<String, Integer>();
		//mapa.put(znalezione_obiekty[0][0].classes,0);
		for (int i=0;i<query.length;i++)
		{
			for(int j=0;j<k;j++)
			{
				
				if(!mapa.containsKey(znalezione_obiekty[i][j].classes))
				{
					mapa.put(znalezione_obiekty[i][j].classes, 1);
				}
				else
				{
					mapa.put(znalezione_obiekty[i][j].classes, mapa.get(znalezione_obiekty[i][j].classes)+1);
				}
				
			}
		}
		return mapa;
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
		
		return closestobj;
		
	}
}
