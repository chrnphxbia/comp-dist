// CalculatorServer.java
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer implements Calculator {

    // Implement the add method
    public double add(double a, double b) throws RemoteException {
        return a + b;
    }

    // Implement the subtract method
    public double subtract(double a, double b) throws RemoteException {
        return a - b;
    }

    // Implement the multiply method
    public double multiply(double a, double b) throws RemoteException {
        return a * b;
    }

    // Implement the divide method
    public double divide(double a, double b) throws RemoteException {
        if (b == 0) throw new ArithmeticException("Division by zero");
        return a / b;
    }

    public Fraction multiply(Fraction a, Fraction b) throws RemoteException {
        return a.multiply(b);
    }

    public Fraction divide(Fraction a, Fraction b) throws RemoteException {
        return a.divide(b);
    }

    public Fraction max(Fraction a, Fraction b) throws RemoteException {
	return a.max(b);        
    }

    public Fraction min(Fraction a, Fraction b) throws RemoteException {
	return a.min(b);        
    }	

    public static void main(String[] args) {
        try {
            // Create and export the CalculatorServer object
            CalculatorServer server = new CalculatorServer();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(server, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Calculator", stub);

            System.out.println("CalculatorServer is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
