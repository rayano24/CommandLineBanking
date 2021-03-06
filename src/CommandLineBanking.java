
// The following class will be cleaned up
// It is a little messy/difficult to read in its current state
import java.util.Scanner;

public class CommandLineBanking extends RegistrationProcess {

	private static final long serialVersionUID = 6539416002176179079L;

	public CommandLineBanking(String email, String password, float chequingsBalance, float savingsBalance) {
		super(email, password, chequingsBalance, savingsBalance);
	}

	public static void main(String args[]) {

		while (true) {

			RegistrationPrompt(); // Sign in + Sign Out Option
			deserialize();

			@SuppressWarnings("resource")
			Scanner scannerInput = new Scanner(System.in);
			System.out.println("Welcome to CommandLineBanking");
			System.out.print("Email: ");
			String email = scannerInput.next().toLowerCase();
			System.out.print("Password: ");
			String password = scannerInput.next();

			// Processing user input
			try {
				if (((accountsHM.get(email).getPassword().equals(password)
						&& accountsHM.get(email).getEmail().equalsIgnoreCase(email)) == false)) {
					System.out.println("You have entered an invalid password. Please try again.");
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

					while (true) {

						System.out.println("Please select an option:");
						System.out.println("1. Chequings account balance");
						System.out.println("2. Savings account balance");
						System.out.println("3. Back");

						String accountType = scannerInput.next();

						if (accountType.equalsIgnoreCase("Chequings") || accountType.equals("1")) {
							accountsHM.get(email).getBalance("chequings");
							break;

						} else if (accountType.equalsIgnoreCase("Savings") || accountType.equals("2")) {
							accountsHM.get(email).getBalance("savings");
							break;

						} else if (accountType.equals("3")) {
							break;
						} else {
							System.out.println("The option you selected is not valid. Please try again");
							continue;
						}
					}
				}

				// Move Money

				else if (choice.equalsIgnoreCase("move") || choice.equals("2")) {

					System.out.println("Please select an option:");
					System.out.println("1. Your own GBC account");
					System.out.println("2. Another GBC Client");
					System.out.println("3. E-Mail Money Transfer");
					System.out.println("4. Back");

					String transferType = scannerInput.next();

					// Move within your account

					if (transferType.equals("1")) {

						accountChoice(); // Text prompt to select account Type

						String moveFrom = scannerInput.next();

						// Transfer to Chequings

						if (moveFrom.equalsIgnoreCase("Chequings") || moveFrom.equals("1")) {

							while (true) {

								System.out.print("Please enter the amount you'd like to transfer: $");
								float transferAmount = scannerInput.nextFloat();

								if (transferAmount <= accountsHM.get(email).getChequingsBalance()) {
									accountsHM.get(email).moveFrom("Chequings", transferAmount);
									break;

								} else {
									System.out.println("You do not have sufficient funds.");
									continue;
								}
							}

							// Transfer to Savings

						} else if (moveFrom.equalsIgnoreCase("Savings") || moveFrom.equals("2")) {

							while (true) {

								System.out.print("Please enter the amount you'd like to transfer:");
								float transferAmount = scannerInput.nextFloat();

								if (transferAmount <= accountsHM.get(email).getSavingsBalance()) {
									accountsHM.get(email).moveFrom("Savings", transferAmount);
									break;

								} else {
									System.out.println("You do not have sufficient funds.");
									continue;
								}
							}

						}

					}
					// Transfer to GBC Client (****clean up****)

					if (transferType.equals("2")) {

						accountChoice(); // Text prompt to select account Type
						String moveTo = scannerInput.next();

						if (moveTo.equalsIgnoreCase("Chequings") || moveTo.equals("1")) {

							while (true) {

								System.out.print(
										"Please enter the email address of the person you'd like to transfer to: ");
								String transferUser = scannerInput.next().toLowerCase();
								if (accountsHM.get(transferUser) == null) {
									System.out.println("The email you provided is not a GBC client.");
									continue;
								} else {

								}
								System.out.print("Please enter the amount you'd like to transfer: $");
								float transferAmount = scannerInput.nextFloat();

								if (transferAmount <= accountsHM.get(email).getChequingsBalance()) {
									accountsHM.get(email).transfer("Chequings", transferAmount, accountsHM.get(transferUser));
									System.out.println(accountsHM.get(transferUser).getEmail() + ".");
									break;

								} else {
									System.out.println("You do not have sufficient funds.");
									continue;
								}

							}

						}

						if (moveTo.equalsIgnoreCase("Savings") || moveTo.equals("2")) {

							while (true) {

								System.out.print(
										"Please enter the email address of the person you'd like to transfer to: ");
								String transferUser = scannerInput.next().toLowerCase();
								if (accountsHM.get(transferUser) == null) {
									System.out.println("The email you provided is not a GBC client.");
									continue;
								} else {

								}

								System.out.print("Please enter the amount you'd like to transfer: $");
								float transferAmount = scannerInput.nextFloat();

								if (transferAmount <= accountsHM.get(email).getSavingsBalance()) {
									accountsHM.get(email).transfer("Savings", transferAmount,
											accountsHM.get(transferUser));
									System.out.println(accountsHM.get(transferUser).getEmail() + ".");
									break;

								} else {
									System.out.println("You do not have sufficient funds.");
									continue;
								}

							}

						}
					}

					// Transfer to a user, bank is irrelevant. If the email does not exist, it is
					// the senders error (no callback).

					if (transferType.equals("3")) {

						accountChoice(); // Text prompt to select account Type
						String moveFrom = scannerInput.next();

						// Transfer from Chequings

						if (moveFrom.equalsIgnoreCase("Chequings") || moveFrom.equals("1")) {

							while (true) {

								System.out.print(
										"Please enter the email address of the person you'd like to transfer to: ");
								String transferUser = scannerInput.next();
								System.out.print("Please enter the amount you'd like to transfer: $");
								float transferAmount = scannerInput.nextFloat();

								// userTransferChequings(transferAmount);
								if (transferAmount <= accountsHM.get(email).getChequingsBalance()) {
									accountsHM.get(email).transfer("Chequings", transferAmount, null);
									System.out.println(transferUser + ".");
									break;

								} else {
									System.out.println("You do not have sufficient funds.");
									continue;
								}

							}
						}

						// Transfer from Savings

						if (moveFrom.equalsIgnoreCase("Savings") || moveFrom.equals("2")) {

							while (true) {

								System.out.print(
										"Please enter the email address of the person you'd like to transfer to: ");
								String transferUser = scannerInput.next();
								System.out.print("Please enter the amount you'd like to transfer: $");
								float transferAmount = scannerInput.nextFloat();

								if (transferAmount <= accountsHM.get(email).getSavingsBalance()) {
									accountsHM.get(email).transfer("Savings", transferAmount, null);
									System.out.println(transferUser + ".");
									break;

								} else {
									System.out.println("You do not have sufficient funds.");
									continue;
								}

							}
						}
					} else if (transferType.equals("3")) {
						break;
					}
				}

				else if (choice.equalsIgnoreCase("Sign Out") || choice.equals("3")) {
					break;
				}

				else {
					System.out.println("The option you selected is not valid.");
					continue;
				}
				serialize();
			}
		}
	}
}
