// NOME: Pedro Henrique Araujo Farias
// RA: 10265432

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NetworkNode extends UnicastRemoteObject implements NetworkNodeInterface {
    private String nodeName;
    private Set<String> files;
    private Set<String> queriedFiles;
    private List<NetworkNodeInterface> neighbors;

    public NetworkNode(String nodeName) throws RemoteException {
        super();
        this.nodeName = nodeName;
        this.files = new HashSet<>();
        this.neighbors = new ArrayList<>();
        this.queriedFiles = new HashSet<>();
    }

    @Override
    public void addFile(String fileName) throws RemoteException {
        files.add(fileName);
    }

    @Override
    public void addNeighbor(NetworkNodeInterface neighbor) throws RemoteException {
        neighbors.add(neighbor);
    }

    @Override
    public void searchFile(String fileName, int maxHops) throws RemoteException {
        int numOfHops = 0;
        Set<NetworkNodeInterface> visitedNodes = new HashSet<>();
        queryFile(fileName, visitedNodes, numOfHops, maxHops);
    }

    private void queryFile(String fileName, Set<NetworkNodeInterface> visitedNodes, int numOfHops, int maxHops) throws RemoteException {
        if (visitedNodes.contains(this)) {
            return;
        }
        if (numOfHops > maxHops) {
            System.out.println(nodeName + ": TTL expired.");
            return;
        }
        if (queriedFiles.contains(fileName)) {
            System.out.println(nodeName + " has already searched for " + fileName);
            return;
        }

        System.out.println(nodeName + " is searching for '" + fileName + "'...");
        queriedFiles.add(fileName);

        if (files.contains(fileName)) {
            System.out.println(nodeName + " found the file '" + fileName + "'!");
            return;
        }

        visitedNodes.add(this);
        for (NetworkNodeInterface neighbor : neighbors) {
            neighbor.searchFile(fileName, maxHops);
        }
    }

    @Override
    public String getNodeName() throws RemoteException {
        return nodeName;
    }
}
