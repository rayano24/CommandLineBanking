
public class TextPrompts extends Cipher {

	public TextPrompts(String email, String password, float chequingsBalance, float savingsBalance) {
		super(email, password, chequingsBalance, savingsBalance);
		// TODO Auto-generated constructor stub
	}

	public static void accountChoice() { 	
		System.out.println("Select an account to move money from:");
		System.out.println("1. Chequings Account");
		System.out.println("2. Savings Account");
		
	}
	
	public static void generateHelp() {
		System.out.println("========Generic Bank of Canadia password guidelines========");
		System.out.println("At GBC, your security is our priority.\n" + "It is important to select a password that"
				+ " cannot easily be guessed by people who may attempt to infiltrate your account.\n");
		System.out.println("1. Your password must have a minimum of six characters.");
		System.out.println("2. Your password must contain at least one upper case and lower case letter.");
		System.out.println("3. Your password must contain a special character such as @!# etc..\n");

	}
}
