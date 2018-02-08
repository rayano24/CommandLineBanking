import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UserAccount {

	// Multiple Users to be stored in hashmap
	static HashMap<String, UserAccount> accountsHM = new HashMap<String, UserAccount>();
	static String timeStamp = new SimpleDateFormat("dd/MM/yyyy - H:mm").format(Calendar.getInstance().getTime());
	private String email;
	private String password;
	private float chequingsBalance;
	private float savingsBalance;

	protected UserAccount(String email, String password, float chequingsBalance, float savingsBalance) {
		super();
		this.email = email;
		this.password = password;
		this.chequingsBalance = chequingsBalance;
		this.savingsBalance = savingsBalance;
	}

	protected static void createAccount(String email, String password) {
		accountsHM.put(email, new UserAccount(email, password, 0, 100));
	}

	protected String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	protected float getChequingsBalance() {
		return chequingsBalance;
	}

	protected void setChequingsBalance(float chequingsBalance) {
		this.chequingsBalance = chequingsBalance;
	}

	protected float getSavingsBalance() {
		return savingsBalance;
	}

	protected void setSavingsBalance(float savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	// Facilitates transfers within account

	protected void moveFrom(String accountType, float amount) {
		if (accountType.equalsIgnoreCase("Savings")) {
			this.setSavingsBalance(this.getSavingsBalance() - amount);
			this.setChequingsBalance(this.getChequingsBalance() + amount);
		} else if (accountType.equalsIgnoreCase("Chequings")) {
			this.setChequingsBalance(this.getChequingsBalance() - amount);
			this.setSavingsBalance(this.getSavingsBalance() + amount);
		}
		System.out.print(timeStamp + "\nTransaction Details: \nChequings Balance: $" + this.getChequingsBalance()
				+ "\nSavings Balance: $" + this.getSavingsBalance() + "\nThank you for banking with us.\n");
	}

	// Facilitates transfers to other users

	protected void transfer(String accountType, float amount, UserAccount receiver) {
		if (accountType.equals("Chequings")) {
			this.setChequingsBalance(this.getChequingsBalance() - amount);
			if (receiver == null) { // For email transfers since the user is not a GBC client
			} else {
				receiver.setChequingsBalance((receiver.getChequingsBalance() + amount));
			}

		} else if (accountType.equals("Savings")) {
			this.setSavingsBalance(this.getSavingsBalance() - amount);
			if (receiver == null) { // For email transfers since the user is not a GBC client
			} else {
				receiver.setChequingsBalance((receiver.getChequingsBalance() + amount));
			}

		}

	}

}
