package vn.vanlanguni.oopdrawing;

public class Calculator {

	public static double average(int n1, int n2) {
		return 1.0 * (n1 + n2) / 2;
	}

	public static double average(double n1, double n2) {
		return (n1 + n2) / 2;
	}
	
	public static double average(int n1, int n2, int n3) {
		return 1.0 * (n1 + n2 + n3) / 3;
	}
	
	public static void main(String[] args) {
		System.out.println(average(10, 15));
		System.out.println(average(10.5, 15.7));
		System.out.println(average(10, 15, 20));
	}

}
