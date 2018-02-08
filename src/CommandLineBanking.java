// The following class will be cleaned up
// It is a little messy/difficult to read in its current state

import java.util.Scanner;

public class CommandLineBanking extends RegistrationProcess {

	public CommandLineBanking(String email, String password, float chequingsBalance, float savingsBalance) {
		super(email, password, chequingsBalance, savingsBalance);
	}

	public static void main(String args[]) {

		while (true) {

			RegistrationPrompt(); // Sign in + Sign Out Option

			@SuppressWarnings("resource")
			Scanner scannerInput = new Scanner(System.in);
			System.out.println("Welcome to CommandLineBanking");
			System.out.print("Email: ");
			String email = scannerInput.next();
			System.out.print("Password: ");
			String password = scannerInput.next();

			// Processing user input
			try {
				if (((accountsHM.get(email).getPassword().equals(password)
						&& email.equalsIgnoreCase((accountsHM.get(email)).getEmail())) == false)) {
					System.out.println("You have entered an invalid password. Please try again.\n");
					continue;
				}
			} catch (Exception e) {
				System.out.println("The account does not exist.");
				continue;
			}

			// Banking Options
			while (true) {

				System.out.println("Please select an option:");
				System.out.println("1. Check Balance");
				System.out.println("2. Move Money");
				System.out.println("3. Sign Out");

				String choice = scannerInput.next();

				// Balance

				if (choice.equalsIgnoreCase("Balance") || choice.equals("1")) {

					System.out.println("Please select an option:");
					System.out.println("1. Chequings account balance");
					System.out.println("2. Savings account balance");

					String accountType = scannerInput.next();

					if (accountType.equalsIgnoreCase("Chequings") || accountType.equals("1")) {
						System.out.println("As of " + timeStamp + ", your chequings account balance (ID: "
								+ accountsHM.get(email).getEmail() + ") is: $"
								+ accountsHM.get(email).getChequingsBalance() + ".");
						System.out.println("Thank you for banking with us.");

					} else if (accountType.equalsIgnoreCase("Savings") || accountType.equals("2")) {
						System.out.println("As of " + timeStamp + ", your savings account balance (ID: "
								+ accountsHM.get(email).getEmail() + ") is: $"
								+ accountsHM.get(email).getSavingsBalance() + ".");
						System.out.println("Thank you for banking with us.");

					} else {
						System.out.println("The option you selected is not valid.");
						continue;
					}
				}

				// Move Money

				else if (choice.equalsIgnoreCase("move") || choice.equals("2")) {

					System.out.println("Please select an option:");
					System.out.println("1. Your own GBC account.");
					System.out.println("2. Another GBC Client.");
					System.out.println("3. E-Mail Money Transfer .");

					String transferType = scannerInput.next();

					// Move within your account

					if (transferType.equals("1")) {

						accountChoice(); // Text prompt to select account Type

						String moveFrom = scannerInput.next();

						// Transfer to Chequings

						if (moveFrom.equalsIgnoreCase("Chequings") || moveFrom.equals("1")) {

							System.out.print("Please enter the amount you'd like to transfer: $");
							float transferAmount = scannerInput.nextFloat();

							if (transferAmount <= accountsHM.get(email).getChequingsBalance()) {
								accountsHM.get(email).moveFrom("Chequings", transferAmount);

							} else {
								System.out.println("You do not have sufficient funds.");
							}

							// Transfer to Savings

						} else if (moveFrom.equalsIgnoreCase("Savings") || moveFrom.equals("2")) {

							System.out.print("Please enter the amount you'd like to transfer:");
							float transferAmount = scannerInput.nextFloat();

							if (transferAmount <= accountsHM.get(email).getSavingsBalance()) {
								accountsHM.get(email).moveFrom("Savings", transferAmount);

							} else {
								System.out.println("You do not have sufficient funds.");
							}

						}

					}
					// Transfer to GBC Client (****clean up****)

					if (transferType.equals("2")) {

						accountChoice(); // Text prompt to select account Type
						String moveTo = scannerInput.next();

						if (moveTo.equalsIgnoreCase("Chequings") || moveTo.equals("1")) {

							System.out
									.print("Please enter the email address of the person you'd like to transfer to: ");
							String transferUser = scannerInput.next();
							if (accountsHM.get(transferUser) == null) {
								System.out.println("The email you provided is not a GBC client.");
								continue;
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

						if (moveTo.equalsIgnoreCase("Savings") || moveTo.equals("2")) {

							System.out
									.print("Please enter the email address of the person you'd like to transfer to: ");
							String transferUser = scannerInput.next();
							if (accountsHM.get(transferUser) == null) {
								System.out.println("The email you provided is not a GBC client.");
								continue;
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

					// Transfer to a user, bank is irrelevant. If the email does not exist, it is
					// the senders error (no callback).

					if (transferType.equals("3")) {

						accountChoice(); // Text prompt to select account Type
						String moveFrom = scannerInput.next();

						// Transfer from Chequings

						if (moveFrom.equalsIgnoreCase("Chequings") || moveFrom.equals("1")) {

							System.out
									.print("Please enter the email address of the person you'd like to transfer to: ");
							String transferUser = scannerInput.next();
							System.out.print("Please enter the amount you'd like to transfer: $");
							float transferAmount = scannerInput.nextFloat();

							// userTransferChequings(transferAmount);
							if (transferAmount <= accountsHM.get(email).getChequingsBalance()) {
								accountsHM.get(email).transfer("Chequings", transferAmount, null);

							} else {
								System.out.println("You do not have sufficient funds.");
								continue;
							}

							System.out.println("========TRASNFER RECEIPT========");
							System.out.println("===== " + timeStamp + " =====");
							System.out.println("You transferred $ " + transferAmount + " to " + transferUser + ".");

						}

						// Transfer from Savings

						if (moveFrom.equalsIgnoreCase("Savings") || moveFrom.equals("2")) {

							System.out.print("Please enter the email address of the person you'd like to transfer to: ");
							String transferUser = scannerInput.next();
							System.out.print("Please enter the amount you'd like to transfer: $");
							float transferAmount = scannerInput.nextFloat();

							if (transferAmount <= accountsHM.get(email).getSavingsBalance()) {
								accountsHM.get(email).transfer("Savings", transferAmount, null);

							} else {
								System.out.println("You do not have sufficient funds.");
								continue;
							}

							System.out.println("========TRASNFER RECEIPT========");
							System.out.println("===== " + timeStamp + " =====");
							System.out.println("You transferred $ " + transferAmount + " to " + transferUser + ".");
						}
					}
				}

				else if (choice.equalsIgnoreCase("Sign Out") || choice.equals("3")) {
					break;
				}

				else {
					System.out.println("The option you selected is not valid.");
					continue;
				}
			}
		}
	}
}
