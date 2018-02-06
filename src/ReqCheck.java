// Requirements Checker for password
public class ReqCheck extends UserAccount  {

	public ReqCheck(String email, String password, float chequingsBalance, float savingsBalance) {
		super(email, password, chequingsBalance, savingsBalance);
		// TODO Auto-generated constructor stub
	}

	protected static boolean passwordAuthenticator(String password) {
		if (password.length() < 6) {
			return false;
		} else if (password.equals(password.toLowerCase())) {
			return false;
		} else if (password.equals(password.toUpperCase())) {
			return false;
		} else if (password.matches("[A-Za-z0-9 ]*")) { 
			return false;
		}
		return true;
	}

	public static void generateHelp() {
		System.out.println("========Generic Bank of Canada password guidelines========");
		System.out.println("At GBC, your security is our priority./n" + "It is important to select a password that"
				+ " cannot easily be guessed by people who may attempt to infiltrate your account./n");
		System.out.println("1. Your password must have a minimum of six characters.");
		System.out.println("2. Your password must contain at least one upper case and lower case letter.");
		System.out.println("3. Your password must contain a special character such as @!# etc../n");

	}
}