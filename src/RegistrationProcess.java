import java.util.Scanner;

public class RegistrationProcess extends TextPrompt {

	public RegistrationProcess(String email, String password, float chequingsBalance, float savingsBalance) {
		super(email, password, chequingsBalance, savingsBalance);
	}

	private static final long serialVersionUID = -7876473206806408458L;


	protected static void RegistrationPrompt() {

		System.out.println("Welcome to the Generic Bank of Canadia CommandLineBanking™ portal");
		System.out.println("1. Sign In");
		System.out.println("2. Sign Up");

		@SuppressWarnings("resource")
		Scanner scannerInput = new Scanner(System.in);
		String initialInput = scannerInput.next();

		// Sign up Process
		if (initialInput.equals("2")) {

			System.out.println(
					"Welcome to CommandLineBanking™, we're glad that you're here to test drive the future of banking. ");
			System.out.println("For guidelines on selecting a password, please type help onto the console.\n"
					+ "Alternatively, press any other key continue to registration.");

			String passwordInfo = scannerInput.next();

			if (passwordInfo.equalsIgnoreCase("help")) {
				generateHelp();
			}

			System.out.println("Please enter the email associated with your account and a desired password.");
			System.out.print("Email Address: ");
			String emailAddress = scannerInput.next();
			while (true) {
				System.out.print("Password: ");
				String userPassword = scannerInput.next();

				// STARTING BALANCE + USER ACCOUNTS
				if (passwordAuthenticator(userPassword) == true) {
					createAccount(emailAddress, userPassword);
					System.out.println("Thank you for signing up for CommandLineBanking. Your chequings account has been credited with $100. Be sure to keep your new credentials in a safe place.");
					break;
				} else {
					System.out.println("You have entered an invalid password. Please try again.");
					continue;
				}
			}
		}

	}
}
