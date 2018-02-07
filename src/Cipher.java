// Unrealistic implementation of encryption
// Currently basic caesar cipher, to be updated. this only exists because I already have the code on hand.
// Will be implemented when the project makes use of file I/O

public class Cipher extends TextPrompts {

	public Cipher(String email, String password, float chequingsBalance, float savingsBalance) {
		super(email, password, chequingsBalance, savingsBalance);
		// TODO Auto-generated constructor stub
	}

	protected void encrypt(String email, int key) {
		key = 12;
		char messageLetter = 0;
		char[] caesarArr = new char[accountsHM.get(email).getPassword().length()];
		for (int i = 0; i < accountsHM.get(email).getPassword().length(); i++) {
			if (key > 0) {
				messageLetter = (char) (((int) (accountsHM.get(email).getPassword().charAt(i)) + key - 97) % 26 + 97);
				caesarArr[i] = messageLetter;
			} else if (key < 0) { // for decipher
				key += 26;
				messageLetter = (char) (((int) (accountsHM.get(email).getPassword().charAt(i)) + key - 97) % 26 + 97);
				caesarArr[i] = messageLetter;
			}
		}
		String temp = new String(caesarArr);
		accountsHM.get(email).setPassword(temp);
	}
	
	protected void decrypt(String email, int key) {
		key = -12;
		encrypt(email, key);
	}

}
