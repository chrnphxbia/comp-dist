// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface NetworkNodeInterface extends Remote {
    void addFile(String fileName) throws RemoteException;
    void addNeighbor(NetworkNodeInterface neighbor) throws RemoteException;
    void searchFile(String fileName, int maxHops) throws RemoteException;
    String getNodeName() throws RemoteException;
}
