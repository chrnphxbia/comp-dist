// Calculator.java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    // Method to add two numbers
    public double add(double a, double b) throws RemoteException;

    // Method to subtract two numbers
    public double subtract(double a, double b) throws RemoteException;

    // Method to multiply two numbers
    public double multiply(double a, double b) throws RemoteException;

    // Method to divide two numbers
    public double divide(double a, double b) throws RemoteException;

    public Fraction multiply(Fraction a, Fraction b) throws RemoteException;
    public Fraction divide(Fraction a, Fraction b) throws RemoteException;
    public Fraction max(Fraction a, Fraction b) throws RemoteException;
    public Fraction min(Fraction a, Fraction b) throws RemoteException;
}
