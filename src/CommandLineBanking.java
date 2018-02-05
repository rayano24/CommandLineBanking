import java.util.Scanner;

public class CommandLineBanking extends RegistrationProcess {
	
	public CommandLineBanking(String email, String password, float chequingsBalance, float savingsBalance) {
		super(email, password, chequingsBalance, savingsBalance);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) {
		
		// =========================
		// Registration Process
		// =========================
				
		RegistrationPrompt();
		
		// =========================
		// BANKING FUNCTIONS
		// =========================
		
		
		Scanner scannerinput = new Scanner(System.in);
		System.out.println("Welcome to CommandLineBanking™");
		System.out.print("Please enter your commandLineBanking™ email: ");
		String email = scannerinput.next();
		System.out.print("Please enter your commandLineBanking™ password: ");
		String password = scannerinput.next();

		if ((primary.getPassword().equals(password) && email.equals(primary.getEmail())) == false) {
			System.out.println("You have entered an invalid email or password.");
			System.exit(0);
		}

		// OPTIONS

		while (true) {

			System.out.println("Please select an option:");
			System.out.println("1. Check Balance");
			System.out.println("2. Move Money");
			System.out.println("3. E-Statement");

			String choice = scannerinput.next();

			// BALANCE

			if (choice.equalsIgnoreCase("Balance") || choice.equals("1")) {

				System.out.println("Please select an option:");
				System.out.println("1. Chequings account balance");
				System.out.println("2. Savings account balance");

				String accountType = scannerinput.next();

				if (accountType.equalsIgnoreCase("Chequings") || accountType.equals("1")) {
					System.out.println("As of " + timeStamp + ", your chequings account balance (ID: " + primary.getEmail()
							+ ") is: $" + primary.getChequingsBalance() + ".");
					System.out.println("Thank you for banking with us.");

				} else if (accountType.equalsIgnoreCase("Savings") || accountType.equals("2")) {
					System.out.println("As of " + timeStamp + ", your savings account balance (ID: " + primary.getEmail()
							+ ") is: $" + primary.getSavingsBalance() + ".");
					System.out.println("Thank you for banking with us.");

				} else {
					System.out.println("Invalid input. You will be kicked out of the session");
					continue;
				}
			}

			// MOVE MONEY

			else if (choice.equalsIgnoreCase("move") || choice.equals("2")) {

				System.out.println("Please select an option:");
				System.out.println("1. Move money within your account.");
				System.out.println("2. Transfer to another GBC user.");

				String transferType = scannerinput.next();

				// MOVE WITHIN YOUR OWN ACCOUNT

				if (transferType.equals("1")) {

					System.out.println("What account would you like to move your funds to?");
					System.out.println("1. Chequings Account");
					System.out.println("2. Savings Account");

					String moveTo = scannerinput.next();

					// TRANSFER TO CHEQUINGS ACCOUNT

					if (moveTo.equalsIgnoreCase("Chequings") || moveTo.equals("1")) {

						System.out.println("Please enter the amount you'd like to transfer:");
						float transferAmount = scannerinput.nextFloat();

						if (transferAmount <= primary.getSavingsBalance()) {
							primary.moveTo("chequings", transferAmount);


						} else {
							System.out.println(
									"You do not have sufficient funds. You will be kicked out of the session.");
						}

						// TRANSFER TO SAVINGS ACCOUNT

					} else if (moveTo.equalsIgnoreCase("Savings") || moveTo.equals("2")) {

						System.out.println("Please enter the amount you'd like to transfer:");
						float transferAmount = scannerinput.nextFloat();

						if (transferAmount <= primary.getChequingsBalance()) {
							primary.moveTo("chequings", transferAmount);

						} else {
							System.out.println(
									"You do not have sufficient funds. You will be kicked out of the session.");
						}

					}

				}

				// TRANSFER TO ANOTHER USER

				if (transferType.equals("2")) {

					System.out.println("Select an account to move money from:");
					System.out.println("1. Chequings Account");
					System.out.println("2. Savings Account");

					String moveTo = scannerinput.next();

					// TRANSFER TO USER CHEQUINGS

					if (moveTo.equalsIgnoreCase("Chequings") || moveTo.equals("1")) {

						System.out.println("Please enter the email address of the person you'd like to transfer to: ");
						String secondaryEmail = scannerinput.next();

						UserAccount secondary = new UserAccount(secondaryEmail, null, 0, 0);

						System.out.print("Please enter the amount you'd like to transfer: $");
						float transferAmount = scannerinput.nextFloat();

						// userTransferChequings(transferAmount);
						if (transferAmount <= primary.getChequingsBalance()) {
							primary.transfer("Chequings", transferAmount, secondary);

						} else {
							System.out.println("You do not have sufficient funds.");
							continue;
						}

						System.out.println("========TRASNFER RECEIPT========");
						System.out.println("===== " + timeStamp + " =====");
						System.out.println("You transferred $ " + transferAmount + " to " + secondaryEmail + ".");
						// System.out.println("Savings balance: $" + getChequingsBalance());

					}

					// TRANSFER TO USER SAVINGS

					if (moveTo.equalsIgnoreCase("Savings") || moveTo.equals("2")) {

						System.out.print("Please enter the email address of the person you'd like to transfer to: ");
						String secondaryEmail = scannerinput.next();

						UserAccount secondary = new UserAccount(secondaryEmail, null, 0, 0);

						System.out.print("Please enter the amount you'd like to transfer: $");
						float transferAmount = scannerinput.nextFloat();

						if (transferAmount <= primary.getSavingsBalance()) {
							primary.transfer("Savings", transferAmount, secondary);

						} else {
							System.out.println("You do not have sufficient funds.");
							continue;
						}

						// userTransferSavings(transferAmount);

						System.out.println("========TRASNFER RECEIPT========");
						System.out.println("===== " + timeStamp + " =====");
						System.out.println("You transferred $ " + transferAmount + " to " + secondaryEmail + ".");
						// System.out.println("Savings balance: $" + getSavingsBalance());

					}

				}

			}

			// E-STATEMENT

			else if (choice.equalsIgnoreCase("E-Statement") || choice.equals("3")) {

				// E-STATEMENT POPUP BODY

				// In retrospect, this is not a very logical feature for this specific
				// implementation.
				// To be replaced or removed

				// Implementation to add; Sign out option. When support for multiple
				// simultaneous users is added?

			}

			else {
				System.out.println("Invalid input. You will be kicked out of the session.");
				continue;
			}
		}
	}
}
	
