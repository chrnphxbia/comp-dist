/*
 * NOME: Pedro Henrique Araujo Farias
 * RA: 10265432
*/

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer implements Calculator {

    public double add(double a, double b) throws RemoteException {
        return a + b;
    }

    public double subtract(double a, double b) throws RemoteException {
        return a - b;
    }

    public double multiply(double a, double b) throws RemoteException {
        return a * b;
    }

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

    public ComplexNum addComplexNum(ComplexNum a, ComplexNum b) throws RemoteException {
        return a.addComplexNum(b);
    }
    
    public ComplexNum subComplexNum(ComplexNum a, ComplexNum b) throws RemoteException {
        return a.subComplexNum(b);
    }
    
    public ComplexNum multComplexNum(ComplexNum a, ComplexNum b) throws RemoteException {
        return a.multComplexNum(b);
    }
    
    public ComplexNum divComplexNum(ComplexNum a, ComplexNum b) throws RemoteException {
        return a.divComplexNum(b);
    }

    public ComplexNum minComplexNum(ComplexNum a, ComplexNum b) throws RemoteException {
        return a.minComplexNum(b);
    }
    
    public ComplexNum maxComplexNum(ComplexNum a, ComplexNum b) throws RemoteException {
        return a.maxComplexNum(b);
    }

    public static void main(String[] args) {
        try {
            CalculatorServer server = new CalculatorServer();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Calculator", stub);

            System.out.println("CalculatorServer is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
