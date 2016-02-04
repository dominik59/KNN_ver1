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
									{64.528},
									{64.528},
									{64.526},
									{64.526},
									{64.528},
									{64.528},
									
									{61.445},
									{61.434},
									{60.423},
									{61.434},
									{60.412},
									{61.421},
									{60.435},
									{61.434},
									{60.447},
									{60.449},
									
									{56.040},
									{56.040},
									{56.040},
									{57.040},
									{57.040},
									{56.040},
									{56.040},
									{56.040},
									{55.040},
									{57.042},
									
									{73.094},
									{72.090},
									{72.087},
									{73.086},
									{72.083},
									{72.083},
									{72.079},
									{72.079},
									{72.079},
									{72.076},
									
									{68.230},
									{68.230},
									{68.230},
									{68.231},
									{68.223},
									{67.224},
									{68.231},
									{68.231},
									{68.230},
									{68.231},
									
									{71.046},
									{71.046},
									{71.046},
									{70.048},
									{70.048},
									{70.048},
									{70.049},
									{70.049},
									{70.049},
									{70.048},
									
									{71.046},
									{70.046},
									{70.046},
									{70.046},
									{70.046},
									{70.048},
									{70.049},
									{70.049},
									{70.049},
									{70.046},
									
									{68.132},
									{68.132},
									{68.132},
									{69.131},
									{69.131},
									{68.127},
									{68.127},
									{68.127},
									{68.132},
									{68.132},
									
									{72.542},
									{72.542},
									{72.541},
									{72.541},
									{71.501},
									{71.501},
									{71.514},
									{71.514},
									{71.514},
									{71.514},
									
									{72.586},
									{72.586},
									{72.586},
									{72.586},
									{72.557},
									{72.557},
									{71.543},
									{72.557},
									{72.557},
									{72.557},
									
									{62.999},
									{62.999},
									{62.999},
									{62.999},
									{62.999},
									{62.999},
									{62.999},
									{62.999},
									{62.999},
									{62.999},
									
									{46.015},
									{46.015},
									{48.016},
									{48.016},
									{48.016},
									{46.015},
									{46.015},
									{46.015},
									{48.016},
									{48.016},
									
									{82.372},
									{82.361},
									{82.351},
									{82.351},
									{82.351},
									{82.331},
									{82.351},
									{82.361},
									{82.372},
									{82.372},
									
									{78.999},
									{78.999},
									{78.999},
									{78.999},
									{78.999},
									{79.999},
									{79.999},
									{79.999},
									{79.999},
									{79.999},
									
									{85.056},
									{85.056},
									{85.054},
									{85.054},
									{85.054},
									{85.054},
									{85.056},
									{85.056},
									{85.056},
									{85.056},
									
									{71.999},
									{71.999},
									{71.999},
									{72.999},
									{72.999},
									{72.999},
									{72.999},
									{71.999},
									{71.999},
									{71.999},
									
									{66.680},
									{66.680},
									{66.680},
									{66.663},
									{67.678},
									{67.643},
									{66.626},
									{67.624},
									{67.622},
									{67.622},
									
									{61.053},
									{61.053},
									{61.056},
									{62.059},
									{61.056},
									{63.052},
									{61.056},
									{62.059},
									{62.050},
									{63.052},
									
									{66.218},
									{66.220},
									{66.220},
									{66.226},
									{66.219},
									{66.220},
									{66.220},
									{66.219},
									{66.220},
									{66.226},
									
									{67.149},
									{66.144},
									{66.145},
									{67.149},
									{66.144},
									{66.145},
									{67.149},
									{66.144},
									{66.145},
									{67.149},};
	
	static String[][] classes={	{"ajayeb"},
								{"ajayeb"},
								{"ajayeb"},
								{"ajayeb"},
								{"ajayeb"},
								{"ajayeb"},
								{"ajayeb"},
								{"ajayeb"},
								{"ajayeb"},
								{"ajayeb"},
								
								{"aday jmal"},
								{"aday jmal"},
								{"aday jmal"},
								{"aday jmal"},
								{"aday jmal"},
								{"aday jmal"},
								{"aday jmal"},
								{"aday jmal"},
								{"aday jmal"},
								{"aday jmal"},
								
								{"amreaj"},
								{"amreaj"},
								{"amreaj"},
								{"amreaj"},
								{"amreaj"},
								{"amreaj"},
								{"amreaj"},
								{"amreaj"},
								{"amreaj"},
								{"amreaj"},
								
								{"aood"},
								{"aood"},
								{"aood"},
								{"aood"},
								{"aood"},
								{"aood"},
								{"aood"},
								{"aood"},
								{"aood"},
								{"aood"},
								
								{"asgar_ali"},
								{"asgar_ali"},
								{"asgar_ali"},
								{"asgar_ali"},
								{"asgar_ali"},
								{"asgar_ali"},
								{"asgar_ali"},
								{"asgar_ali"},
								{"asgar_ali"},
								{"asgar_ali"},
								
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								{"bukhoor"},
								
								{"dehenalaod"},
								{"dehenalaod"},
								{"dehenalaod"},
								{"dehenalaod"},
								{"dehenalaod"},
								{"dehenalaod"},
								{"dehenalaod"},
								{"dehenalaod"},
								{"dehenalaod"},
								{"dehenalaod"},
								
								{"junaid"},
								{"junaid"},
								{"junaid"},
								{"junaid"},
								{"junaid"},
								{"junaid"},
								{"junaid"},
								{"junaid"},
								{"junaid"},
								{"junaid"},
								
								{"kausar"},
								{"kausar"},
								{"kausar"},
								{"kausar"},
								{"kausar"},
								{"kausar"},
								{"kausar"},
								{"kausar"},
								{"kausar"},
								{"kausar"},
								
								{"rose"},
								{"rose"},
								{"rose"},
								{"rose"},
								{"rose"},
								{"rose"},
								{"rose"},
								{"rose"},
								{"rose"},
								{"rose"},
								
								{"solidmusk"},
								{"solidmusk"},
								{"solidmusk"},
								{"solidmusk"},
								{"solidmusk"},
								{"solidmusk"},
								{"solidmusk"},
								{"solidmusk"},
								{"solidmusk"},
								{"solidmusk"},
								
								{"TeaTreeOil"},
								{"TeaTreeOil"},
								{"TeaTreeOil"},
								{"TeaTreeOil"},
								{"TeaTreeOil"},
								{"TeaTreeOil"},
								{"TeaTreeOil"},
								{"TeaTreeOil"},
								{"TeaTreeOil"},
								{"TeaTreeOil"},
								
								{"raspberry"},
								{"raspberry"},
								{"raspberry"},
								{"raspberry"},
								{"raspberry"},
								{"raspberry"},
								{"raspberry"},
								{"raspberry"},
								{"raspberry"},
								{"raspberry"},
								
								{"RoseMusk"},
								{"RoseMusk"},
								{"RoseMusk"},
								{"RoseMusk"},
								{"RoseMusk"},
								{"RoseMusk"},
								{"RoseMusk"},
								{"RoseMusk"},
								{"RoseMusk"},
								{"RoseMusk"},

								{"strawberry"},
								{"strawberry"},
								{"strawberry"},
								{"strawberry"},
								{"strawberry"},
								{"strawberry"},
								{"strawberry"},
								{"strawberry"},
								{"strawberry"},
								{"strawberry"},
								
								{"constrected2"},
								{"constrected2"},
								{"constrected2"},
								{"constrected2"},
								{"constrected2"},
								{"constrected2"},
								{"constrected2"},
								{"constrected2"},
								{"constrected2"},
								{"constrected2"},
								
								{"carolina_herrera"},
								{"carolina_herrera"},
								{"carolina_herrera"},
								{"carolina_herrera"},
								{"carolina_herrera"},
								{"carolina_herrera"},
								{"carolina_herrera"},
								{"carolina_herrera"},
								{"carolina_herrera"},
								{"carolina_herrera"},
								
								{"oudh_ma'alattar"},
								{"oudh_ma'alattar"},
								{"oudh_ma'alattar"},
								{"oudh_ma'alattar"},
								{"oudh_ma'alattar"},
								{"oudh_ma'alattar"},
								{"oudh_ma'alattar"},
								{"oudh_ma'alattar"},
								{"oudh_ma'alattar"},
								{"oudh_ma'alattar"},
								
								{"constrected"},
								{"constrected"},
								{"constrected"},
								{"constrected"},
								{"constrected"},
								{"constrected"},
								{"constrected"},
								{"constrected"},
								{"constrected"},
								{"constrected"},};
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * w tej tablicy umieszczamy poszczególne wyniki badań da analizy
		 */
		Double[] query = {63.529,64.528,64.528,64.528,64.528,64.528,64.528,64.528,64.528,64.528,64.528,61.443,61.443,61.443};
		/**
		 * w tej tablicy obiektów znajdują się poszczególne dystanse do kolejnych danych ze zbioru uczącego
		 */
		ClosestObject[][] znalezione_obiekty = Find_closest_distance(query, learning_set, classes);
		/**
		 * w zmiennej k następuje określenie w jakiej ilości najbliższych somsiadów będzie przeprowadzone wyszukiwanie
		 */
		Integer k=new Integer(10);
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
