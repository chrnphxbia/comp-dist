// CalculatorClient.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {

    public static void main(String[] args) {
        try {
            // Get the registry
            Registry registry = LocateRegistry.getRegistry("localhost");

            // Lookup the Calculator object
            Calculator stub = (Calculator) registry.lookup("Calculator");

            // Invoke remote methods
            double a = 10.0;
            double b = 5.0;
	    Fraction f1 = new Fraction(1,2);
            Fraction f2 = new Fraction(2,3);

            System.out.println("Addition: " + stub.add(a, b));
            System.out.println("Subtraction: " + stub.subtract(a, b));
            System.out.println("Multiplication: " + stub.multiply(a, b));
            System.out.println("Division: " + stub.divide(a, b));

	    
            System.out.println("Multiplication: " + stub.multiply(f1, f2).print());
            System.out.println("Division: " + stub.divide(f1, f2).print());
            System.out.println("Max: " + stub.max(f1, f2).print());
            System.out.println("Min: " + stub.min(f1, f2).print());

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
