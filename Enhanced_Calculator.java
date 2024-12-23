package shadowfox;

import java.util.Scanner;
import java.text.DecimalFormat;

public class EnhancedCalculator {
    private static final DecimalFormat df = new DecimalFormat("#.####");
    private static final Scanner scanner = new Scanner(System.in);

    // Basic Arithmetic Operations
    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }

    // Scientific Calculations
    public static double squareRoot(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(a);
    }

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    // Unit Conversions
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1: performBasicArithmetic(); break;
                    case 2: performScientificCalculation(); break;
                    case 3: performUnitConversion(); break;
                    case 4: System.exit(0);
                    default: System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear input buffer
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Enhanced Calculator ---");
        System.out.println("1. Basic Arithmetic");
        System.out.println("2. Scientific Calculations");
        System.out.println("3. Unit Conversions");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void performBasicArithmetic() {
        System.out.print("Enter first number: ");
        double a = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double b = scanner.nextDouble();
        System.out.println("Addition: " + df.format(add(a, b)));
        System.out.println("Subtraction: " + df.format(subtract(a, b)));
        System.out.println("Multiplication: " + df.format(multiply(a, b)));
        System.out.println("Division: " + df.format(divide(a, b)));
    }

    private static void performScientificCalculation() {
        System.out.print("Enter number: ");
        double a = scanner.nextDouble();
        System.out.println("Square Root: " + df.format(squareRoot(a)));
        System.out.print("Enter exponent: ");
        double exp = scanner.nextDouble();
        System.out.println("Power: " + df.format(power(a, exp)));
    }

    private static void performUnitConversion() {
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.print("Enter choice: ");
        int conversionChoice = scanner.nextInt();
        
        switch (conversionChoice) {
            case 1:
                System.out.print("Enter Celsius: ");
                double celsius = scanner.nextDouble();
                System.out.println(celsius + "°C = " + df.format(celsiusToFahrenheit(celsius)) + "°F");
                break;
            case 2:
                System.out.print("Enter Fahrenheit: ");
                double fahrenheit = scanner.nextDouble();
                System.out.println(fahrenheit + "°F = " + df.format(fahrenheitToCelsius(fahrenheit)) + "°C");
                break;
            default:
                System.out.println("Invalid conversion choice");
        }
    }
}

