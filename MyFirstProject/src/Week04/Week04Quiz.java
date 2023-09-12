package Week04;

public class Week04Quiz {
	public static void main(String[] args) {
		System.out.println(power(3,4));
		
	}
	
	public static int power(int base, int exponent) {
		int result = 1;
		for(int index = 1; index <= exponent; index++) {
			result = result * base;
		}
		
		return result;
	}
}
