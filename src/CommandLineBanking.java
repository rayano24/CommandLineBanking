import java.util.Scanner;

public class CommandLineBanking extends RegistrationProcess {

	public CommandLineBanking(String email, String password, float chequingsBalance, float savingsBalance) {
		super(email, password, chequingsBalance, savingsBalance);
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {

		RegistrationPrompt();

		// ========================//
		// MAIN BANKING FUNCTIONS //
		// ======================//

		Scanner scannerInput = new Scanner(System.in);
		System.out.println("Welcome to CommandLineBanking™");
		System.out.print("Please enter your commandLineBanking email: ");
		String email = scannerInput.next();
		System.out.print("Please enter your commandLineBanking password: ");
		String password = scannerInput.next();

		accountsHM.get(email).getEmail();

		if ((accountsHM.get(email).getPassword().equals(password)
				&& email.equalsIgnoreCase((accountsHM.get(email)).getEmail()) == false)) {
			System.out.println("You have entered an invalid email or password.");
			System.exit(0);
		}

		// OPTIONS
		while (true) {

			System.out.println("Please select an option:");
			System.out.println("1. Check Balance");
			System.out.println("2. Move Money");
			System.out.println("3. Sign Out (Weirdly Coming Soon)");

			String choice = scannerInput.next();

			// BALANCE

			if (choice.equalsIgnoreCase("Balance") || choice.equals("1")) {

				System.out.println("Please select an option:");
				System.out.println("1. Chequings account balance");
				System.out.println("2. Savings account balance");

				String accountType = scannerInput.next();

				if (accountType.equalsIgnoreCase("Chequings") || accountType.equals("1")) {
					System.out.println("As of " + timeStamp + ", your chequings account balance (ID: "
							+ accountsHM.get(email).getEmail() + ") is: $" + accountsHM.get(email).getChequingsBalance()
							+ ".");
					System.out.println("Thank you for banking with us.");

				} else if (accountType.equalsIgnoreCase("Savings") || accountType.equals("2")) {
					System.out.println("As of " + timeStamp + ", your savings account balance (ID: "
							+ accountsHM.get(email).getEmail() + ") is: $" + accountsHM.get(email).getSavingsBalance()
							+ ".");
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

				String transferType = scannerInput.next();

				// MOVE WITHIN YOUR OWN ACCOUNT

				if (transferType.equals("1")) {

					System.out.println("What account would you like to move your funds to?");
					System.out.println("1. Chequings Account");
					System.out.println("2. Savings Account");

					String moveTo = scannerInput.next();

					// TRANSFER TO CHEQUINGS ACCOUNT

					if (moveTo.equalsIgnoreCase("Chequings") || moveTo.equals("1")) {

						System.out.print("Please enter the amount you'd like to transfer: $");
						float transferAmount = scannerInput.nextFloat();

						if (transferAmount <= accountsHM.get(email).getSavingsBalance()) {
							accountsHM.get(email).moveTo("chequings", transferAmount);

						} else {
							System.out.println(
									"You do not have sufficient funds. You will be kicked out of the session.");
						}

						// TRANSFER TO SAVINGS ACCOUNT

					} else if (moveTo.equalsIgnoreCase("Savings") || moveTo.equals("2")) {

						System.out.print("Please enter the amount you'd like to transfer:");
						float transferAmount = scannerInput.nextFloat();

						if (transferAmount <= accountsHM.get(email).getChequingsBalance()) {
							accountsHM.get(email).moveTo("chequings", transferAmount);

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

					String moveTo = scannerInput.next();

					// TRANSFER TO USER CHEQUINGS

					if (moveTo.equalsIgnoreCase("Chequings") || moveTo.equals("1")) {

						System.out.print("Please enter the email address of the person you'd like to transfer to: ");
						String transferUser = scannerInput.next();
						if (accountsHM.get(transferUser) == null) {
							createAccount(transferUser, null);
						} else {

						}

						System.out.print("Please enter the amount you'd like to transfer: $");
						float transferAmount = scannerInput.nextFloat();

						// userTransferChequings(transferAmount);
						if (transferAmount <= accountsHM.get(email).getChequingsBalance()) {
							accountsHM.get(email).transfer("Chequings", transferAmount, accountsHM.get(transferUser));

						} else {
							System.out.println("You do not have sufficient funds.");
							continue;
						}

						System.out.println("========TRASNFER RECEIPT========");
						System.out.println("===== " + timeStamp + " =====");
						System.out.println("You transferred $ " + transferAmount + " to "
								+ accountsHM.get(transferUser).getEmail() + ".");
						// System.out.println("Savings balance: $" + getChequingsBalance());

					}

					// TRANSFER TO USER SAVINGS

					if (moveTo.equalsIgnoreCase("Savings") || moveTo.equals("2")) {

						System.out.print("Please enter the email address of the person you'd like to transfer to: ");
						String transferUser = scannerInput.next();
						if (accountsHM.get(transferUser) == null) {
							createAccount(transferUser, null);
						} else {

						}

						System.out.print("Please enter the amount you'd like to transfer: $");
						float transferAmount = scannerInput.nextFloat();

						if (transferAmount <= accountsHM.get(email).getSavingsBalance()) {
							accountsHM.get(email).transfer("Savings", transferAmount, accountsHM.get(transferUser));

						} else {
							System.out.println("You do not have sufficient funds.");
							continue;
						}

						System.out.println("========TRASNFER RECEIPT========");
						System.out.println("===== " + timeStamp + " =====");
						System.out.println("You transferred $ " + transferAmount + " to "
								+ accountsHM.get(transferUser).getEmail() + ".");
					}

				}

			}

			// To be implemented

			else if (choice.equalsIgnoreCase("Sign Out") || choice.equals("3")) {

				// Implementation to add; Sign out option.

			}

			else {
				System.out.println("Invalid input. You will be kicked out of the session.");
				continue;
			}
		}
	}
}
