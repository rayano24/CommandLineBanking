import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserAccount {

	static String timeStamp = new SimpleDateFormat("dd/MM/yyyy - H:mm").format(Calendar.getInstance().getTime());
	private String email;
	private String password;
	private float chequingsBalance;
	private float savingsBalance;

	public UserAccount(String email, String password, float chequingsBalance, float savingsBalance) {
		super();
		this.email = email;
		this.password = password;
		this.chequingsBalance = chequingsBalance;
		this.savingsBalance = savingsBalance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getChequingsBalance() {
		return chequingsBalance;
	}

	public void setChequingsBalance(float chequingsBalance) {
		this.chequingsBalance = chequingsBalance;
	}

	public float getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(float savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	public void moveTo(String accountType, float amount) {
		if (accountType.equalsIgnoreCase("Savings")) {
			this.setSavingsBalance(this.getChequingsBalance() + amount);
			this.setChequingsBalance(this.getSavingsBalance() - amount);
		}
		else if(accountType.equalsIgnoreCase("Chequings")) {
			this.setChequingsBalance(this.getSavingsBalance() + amount);
			this.setSavingsBalance(this.getChequingsBalance() - amount);
		}
		System.out.print(timeStamp + "\nTransaction Details: \nChequings Balance: $"
				+ this.getChequingsBalance() + "\nSavings Balance: $" + this.getSavingsBalance()
				+ "\nThank you for banking with us.\n");
	}

	
	public void transfer(String accountType, float amount, UserAccount receiver) {
		if (accountType.equals("Chequings")) {
			this.setChequingsBalance(this.getChequingsBalance() - amount);
			receiver.setChequingsBalance((receiver.getChequingsBalance() + amount));

		} else if (accountType.equals("Savings")) {
			this.setChequingsBalance(this.getSavingsBalance() - amount);
			receiver.setChequingsBalance((receiver.getChequingsBalance() + amount));

		}
		

	}

}
