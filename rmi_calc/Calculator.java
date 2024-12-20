/*
 * NOME: Pedro Henrique Araujo Farias
 * RA: 10265432
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    
    public double add(double a, double b) throws RemoteException;
    public double subtract(double a, double b) throws RemoteException;
    public double multiply(double a, double b) throws RemoteException;
    public double divide(double a, double b) throws RemoteException;

    public Fraction multiply(Fraction a, Fraction b) throws RemoteException;
    public Fraction divide(Fraction a, Fraction b) throws RemoteException;
    public Fraction max(Fraction a, Fraction b) throws RemoteException;
    public Fraction min(Fraction a, Fraction b) throws RemoteException;

    public ComplexNum addComplexNum(ComplexNum a, ComplexNum b) throws RemoteException;
    public ComplexNum subComplexNum(ComplexNum a, ComplexNum b) throws RemoteException;
    public ComplexNum multComplexNum(ComplexNum a, ComplexNum b) throws RemoteException;
    public ComplexNum divComplexNum(ComplexNum a, ComplexNum b) throws RemoteException;
    public ComplexNum maxComplexNum(ComplexNum a, ComplexNum b) throws RemoteException;
    public ComplexNum minComplexNum(ComplexNum a, ComplexNum b) throws RemoteException;
}
