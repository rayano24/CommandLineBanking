/* Rayan Osseiran
 * Command line banking platform
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;


/*=
*===============================
*         UPDATES 
*===============================
* 1. The following is a conversion of an existing ATM program found on my GitHub.
* 2. The user account system has not been worked/implemented yet. To that effect, the current implementation is unchanged from the ATM program.
* 4. Transfers to other users tba
* 5. Major issue; looping after incorrect or improper input. Currently either exits or restarts program.
* 6. GUI to potentially be added in the future but focus on working in console for now.
*/

public class Main extends ReqCheck {

	private static float chequingsBalance = 100; // starting balance 
	private static float savingsBalance = 5000;

	public static void main(String args[]) {

		// Implementing date and time for post action receipt
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy - H:mm").format(Calendar.getInstance().getTime());

		// Setting a pin, simulates an unrealistic registration process
		
		// UNCHANGED FROM ATM

		System.out.print("Welcome to the Generic Bank of Canada, " + "would you like to sign up for command line banking? ");

		@SuppressWarnings("resource")
		Scanner scannerinput = new Scanner(System.in);
		String bankingQuery = scannerinput.next();

		if ((bankingQuery.equalsIgnoreCase("Yes") || bankingQuery.equalsIgnoreCase("Sure")) == false) {
			System.out.println("We're sorry to hear that. We look forward to serving you in the future.");
			System.exit(0);
		}
		
		// WELCOME PROMPT 

		System.out.println("Welcome, you will be asked to select a pin number. ");
		System.out.println("For guidelines on selecting a pin, please type help onto the console. Alternatively, press enter to continue to registration.");

		String pinInfo = scannerinput.next();

		if (pinInfo.equalsIgnoreCase("help")) {
			generateHelp();
		} 
		else {
		}
		
		// REGISTRATION 

		System.out.println("Please enter the email associated with your account and a desired pin number.");
		System.out.print("Email Address: ");
		String emailAddress = scannerinput.next();
		String pinStore = null;
		System.out.print("Pin Number: ");

		try {
			pinStore = scannerinput.next();
		} catch (Exception e) {
			System.out.println("Your pin is not valid. You will be kicked out of the session.");
		}

		int pinNum = Integer.parseInt(pinStore);


		// PIN CHECK

		if (pinStore.length() != 4 && hasDistinctDigits(pinNum) == false) {
			System.out.println("The pin you selected is not valid.");
			System.exit(0);
		}

		AccountGenerator(emailAddress, pinNum);
		int pinNumber = hm.get(emailAddress);


		System.out.println("Thank you for banking with us. Be sure to keep your new credentials in a safe place.");
		
		
		// MAIN BODY 
		// ENTER PIN 

		
		while (true) {
			
			System.out.print("Please enter your commandLineBanking™ PIN: ");
			String pin = scannerinput.next();

			if (pinNumber != Integer.parseInt(pin)) {
				System.out.println("You have entered an invalid pin");
				continue;
			}
			
			// OPTIONS 

			else {
				System.out.println("Please select an option:");
				System.out.println("1. Check Balance");
				System.out.println("2. Move Money");
				System.out.println("3. E-Statement");
			}
			
			String choice = scannerinput.next();
			
			// BALANCE 

			if (choice.equalsIgnoreCase("Balance") || choice.equals("1")) {
				
				System.out.println("Please select an option:");
				System.out.println("1. Chequings account balance");
				System.out.println("2. Savings account balance");
				
				String accountType = scannerinput.next();
				
				
				if(accountType.equalsIgnoreCase("Chequings") || accountType.equals("1")) {
					System.out.println("As of " + timeStamp + ", your savings account balance (ID: " + emailAddress + ") is: $"
							+ chequingsBalance + ".");
					System.out.println("Thank you for banking with us.");	
				}
				else if (accountType.equalsIgnoreCase("Savings") || accountType.equals("2")) { 
					System.out.println("As of " + timeStamp + ", your chequings account balance (ID: " + emailAddress + ") is: $"
							+ savingsBalance + ".");
					System.out.println("Thank you for banking with us.");
				}
				else {
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
				
				if(transferType.equals("1")) {
					
					System.out.println("What account would you like to move your funds to?");
					System.out.println("1. Chequings Account");
					System.out.println("2. Savings Account");
					
					String moveTo = scannerinput.next();					
					
					// TRANSFER TO CHEQUINGS ACCOUNT 

					if(moveTo.equalsIgnoreCase("Chequings") || moveTo.equals("1")) {
						
						System.out.println("Please enter the amount you'd like to transfer:");
						float transferAmount = scannerinput.nextFloat();
						
						if (transferAmount <= savingsBalance) {
							chequingsBalance += transferAmount;
							savingsBalance -= transferAmount;
							
							System.out.print(timeStamp + "\nTransaction Details: \nChequings Balance: $"
							+ chequingsBalance + "\nSavings Balance: $" + savingsBalance + 
							"\nThank you for banking with us.\n");
								
							
						}
						else {
							System.out.println("You do not have sufficient funds. You will be kicked out of the session.");
						}
						
					// TRANSFER TO SAVINGS ACCOUNT
						
					}
					else if (moveTo.equalsIgnoreCase("Savings") || moveTo.equals("2")) { 
						
						
						System.out.println("Please enter the amount you'd like to transfer:");
						float transferAmount = scannerinput.nextFloat();
						
						if (transferAmount <= chequingsBalance) {
							savingsBalance += transferAmount;	
							chequingsBalance -= transferAmount;
							
							System.out.print(timeStamp + "\nTransaction Details: \nChequings Balance: $"
									+ chequingsBalance + "\nSavings Balance: $" + savingsBalance + 
									"\nThank you for banking with us.\n");
									
						}
						else {
							System.out.println("You do not have sufficient funds. You will be kicked out of the session.");
						}
						
					}	
					
					
				}
				
				
				// TRANSFER  TO ANOTHER USER 
				
				if(transferType.equals("2")) {
					
					System.out.println("What account would you like to move your funds to?");
					System.out.println("1. Chequings Account");
					System.out.println("2. Savings Account");
					
					String moveTo = scannerinput.next();	
					
								
					
					// TRANSFER TO USER CHEQUINGS
					
					if(moveTo.equalsIgnoreCase("Chequings") || moveTo.equals("1")) {
						
					}
					
					// TRANSFER TO USER SAVINGS
					
					if(moveTo.equalsIgnoreCase("Savings") || moveTo.equals("2")) {
						
					}
					
					
					
					

					
				}

				
				
			}
			
			//E-STATEMENT
			
			else if (choice.equalsIgnoreCase("E-Statement") || choice.equals("3")) {
				
				//E-STATEMENT POPUP BODY
				
				// In retrospect, this is not a very logical feature for this specific implementation.
				// To be replaced or removed 
				
				
			}
			
			
				
			
			else {
				System.out.println("Invalid input. You will be kicked out of the session.");
				continue;
			}
		}
	}
}
			
			


	