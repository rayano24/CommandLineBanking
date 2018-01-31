// Rayan Osseiran
// Works as the pseudo-registration system. Alternatively, store hash tables in text file as specific user keys.
// TO BE CHANGED; 

import java.util.Hashtable;

public class HolderAccount {
	
	public static Hashtable<String, Integer> hm = new Hashtable<String, Integer>();
	
	public static void AccountGenerator(String emailAddress, int pinStore) {				
		hm.put(emailAddress,pinStore);  	
	}
}
