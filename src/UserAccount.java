import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UserAccount implements Serializable {

	private static final long serialVersionUID = 2233294977370068968L;
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

	protected static void serialize() {
		try {
			FileOutputStream fos = new FileOutputStream("userData.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(accountsHM);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	protected static void deserialize() {
		try {
			FileInputStream fis = new FileInputStream("userData.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			accountsHM = (HashMap<String, UserAccount>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
	}

	protected static void createAccount(String email, String password) {
		accountsHM.put(email.toLowerCase(), new UserAccount(email.toLowerCase(), password, 100, 0));
		serialize();
	}

	protected void getBalance(String accountType) {
		if (accountType.equalsIgnoreCase("Chequings")) {
			System.out.println("As of " + timeStamp + ", your chequings account balance (ID: " + this.getEmail()
					+ ") is: $" + this.getChequingsBalance() + ".");
		} else if (accountType.equalsIgnoreCase("Savings")) {
			System.out.println("As of " + timeStamp + ", your savings account balance (ID: " + this.getEmail()
					+ ") is: $" + this.getSavingsBalance() + ".");
		}
		System.out.println("Thank you for banking with us.");
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
		System.out.print(
				timeStamp + "\n=======Transaction Details======= \nChequings Balance: $" + this.getChequingsBalance()
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
		System.out.println("========TRASNFER RECEIPT========");
		System.out.println("======= " + timeStamp + " =======");
		System.out.print("You transferred $ " + amount + " to ");

	}

}
