// Requirements Checker for password
public class ReqCheck extends UserAccount  {

	private static final long serialVersionUID = -3190498945486059546L;

	public ReqCheck(String email, String password, float chequingsBalance, float savingsBalance) {
		super(email, password, chequingsBalance, savingsBalance);
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

}