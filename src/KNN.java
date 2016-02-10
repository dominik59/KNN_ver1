import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.GuardedObject;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.opencsv.CSVReader;



public class KNN {

	static Double[][] learning_set;
	static String[][] classes;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readcsv();
	   	/**
		 * w tej tablicy umieszczamy poszczególne wyniki badań da analizy
		 */
		Double[] query = {63.529,63.529,64.528,64.528,64.528,64.528,64.526,64.526,64.528,64.528};
		/**
		 * w tej tablicy obiektów znajdują się poszczególne dystanse do kolejnych danych ze zbioru uczącego
		 */
		ClosestObject[][] znalezione_obiekty = Find_closest_distance(query, learning_set, classes);
		/**
		 * w zmiennej k następuje określenie w jakiej ilości najbliższych somsiadów będzie przeprowadzone wyszukiwanie
		 */
		Integer k=new Integer(3);
		System.out.println("Wybrane K to: " + k);
		/**
		 * W tej HashMapie następuje określenie ilości znalezionych najbliższych somsiadów należących do poszczególnych klas
		 */
		HashMap<String, Integer> counting = k_neighbours_selection(query,znalezione_obiekty,k);
		/**
		 * w tym stringu znajduje się ostateczna odpowiedź, która została wybrana na postawie większości wystąpień danej klasy w wyszukiwaniu
		 */
		String final_decision=obtain_final_decision(counting);
		/**
		 * Wypisanie wyników na konsolę
		 */
		System.out.println("Ilość powtórzeń w poszczególnych klasach: " + counting);
		System.out.println("Nowo wprowadzona wartość przynależy do klasy: \"" + final_decision + "\"");
	}
	
	
	private static void readcsv() {
		// TODO Auto-generated method stub
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader("perfume_data.csv"));
			List<String[]> myEntries = reader.readAll();
			learning_set=new Double[(myEntries.size()-1)*myEntries.get(0).length][1];
			classes=new String[(myEntries.size()-1)*myEntries.get(0).length][1];
			//System.out.println(myEntries.get(0).length);
			for (int j = 0; j < myEntries.get(0).length; j++) {
				for (int i = 0; i < myEntries.size()-1; i++) {
					learning_set[i+j*28][0] = Double.parseDouble(myEntries.get(i+1)[j]);
				}
			}
			for(int j =0;j<myEntries.get(0).length;j++)
			{
				for(int i=0;i<myEntries.size()-1;i++)
				{
					classes[i+j*28][0]=myEntries.get(0)[j];
				}
			}
//			for(int i=0;i<myEntries.size();i++)
//			{
//				String[] str=myEntries.get(i);
//				System.out.println(str[1]);
//			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		System.out.println("Ilość danych w zbiorze testowym: " + query.length);
		System.out.println("Ilość danych w zbiorze uczącym: " + learning_set.length);
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
		
		/**
		 * fragment kodu pozwalający na wyświetlanie poszczególnych odległości
		 */
		
//		for(int i=0;i<closestobj.length;i++)
//		{
//			for(int j=0;j<closestobj[i].length;j++)
//			{
//				System.out.println(closestobj[i][j].distance + " " + closestobj[i][j].classes);
//			}
//			System.out.println("\n\n\n");
//		}
		
		return closestobj;
		
	}
}
