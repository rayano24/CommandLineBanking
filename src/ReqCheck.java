// Requirements Checker for PIN
public class ReqCheck extends HolderAccount {
	
	//Provided by xpa1492, stack overflow
	 public static boolean hasDistinctDigits(int number) {
	     int numMask = 0;
	     int numDigits = (int) Math.ceil(Math.log10(number+1));
	     for (int digitIdx = 0; digitIdx < numDigits; digitIdx++) {
	         int curDigit = (int)(number / Math.pow(10,digitIdx)) % 10;
	         int digitMask = (int)Math.pow(2, curDigit);             
	         if ((numMask & digitMask) > 0) return false;
	         numMask = numMask | digitMask;
	     }
	     return true;
	 }
	 
	 public static void generateHelp() { 
		 System.out.println("Generic Bank of Canada PIN Guidelines");
		 System.out.println("1. Please use only unique digits in your PIN. Do not repeat digits.");
		 System.out.println("2. Do not use more or less than four digits in your pin.");
		 
	 }
}