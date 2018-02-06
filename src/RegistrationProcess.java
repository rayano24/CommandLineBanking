import java.util.Scanner;

public class RegistrationProcess extends ReqCheck {

	static UserAccount primary = new UserAccount(null, null, 100, 0);

	protected RegistrationProcess(String email, String password, float chequingsBalance, float savingsBalance) {
		super(email, password, chequingsBalance, savingsBalance);
		// TODO Auto-generated constructor stub
	}

	public static void RegistrationPrompt() {

		System.out.println("Welcome to the Generic Bank of Canada CommandLineBanking™ portal");
		System.out.println("1. Sign In");
		System.out.println("2. Sign Up");

		@SuppressWarnings("resource")
		Scanner scannerInput = new Scanner(System.in);
		String initialInput = scannerInput.next();

		// SIGN UP PROCESS INITITATES
		if (initialInput.equals("2")) {

			// WELCOME PROMPT

			System.out.println(
					"Welcome to CommandLineBanking™, we're glad that you're here to test drive the future of banking. ");
			System.out.println("For guidelines on selecting a password, please type help onto the console.\n"
					+ "Alternatively, press any other key continue to registration.");

			String passwordInfo = scannerInput.next();

			if (passwordInfo.equalsIgnoreCase("help")) {
				generateHelp();
			} else {
			}

			// REGISTRATION

			System.out.println("Please enter the email associated with your account and a desired password.");
			System.out.print("Email Address: ");
			String emailAddress = scannerInput.next();
			String userPassword = null;
			System.out.print("Password: ");

			try {
				userPassword = scannerInput.next(); // RENAME !, TRY CATCH MIGHT NOT BE NECESSARY ANYMORE
			} catch (Exception e) {
				System.out.println("Your pin is not valid. You will be kicked out of the session.");
			}

			if (passwordAuthenticator(userPassword) == false) {
				System.out.println("The password you select is invalid");
				System.exit(0);

			}

			// STARTING BALANCE + USER ACCOUNTS
			primary.setPassword(userPassword);
			primary.setEmail(emailAddress);

			// AccountGenerator(emailAddress, pinNum);
			// int pinNumber = hm.get(emailAddress);

			System.out.println(
					"Thank you for signing up for CommandLineBanking. Your chequings account has been credited with $100. Be sure to keep your new credentials in a safe place.");

		} else {

		}
	}

}
