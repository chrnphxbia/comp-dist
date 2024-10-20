package domain;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

public class Node {
    private String nodeName;
    private Set<String> files;
    private List<Node> neighbors;

    public Node(String nodeName) {
        this.nodeName = nodeName;
        this.files = new HashSet<>();
        this.neighbors = new ArrayList<>();
    }
    
    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Set<String> getFiles() {
        return this.files;
    }

    public void setFiles(Set<String> files) {
        this.files = files;
    }

    public List<Node> getNeighbors() {
        return this.neighbors;
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public void addFile(String fileName) {
        files.add(fileName);
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public void queryFile(String fileName, Set<Node> visitedNodes) {
        if (visitedNodes.contains(this)) {
            return; 
        }

        System.out.println(nodeName + " está procurando por '" + fileName + "'...");

        if (files.contains(fileName)) {
            System.out.println(nodeName + " encontrou o arquivo '" + fileName + "'!");
            return;
        }

        visitedNodes.add(this);

        for (Node neighbor : neighbors) {
            neighbor.queryFile(fileName, visitedNodes);
        }
    }

    public void searchFile(String fileName) {
        Set<Node> visitedNodes = new HashSet<>();
        queryFile(fileName, visitedNodes);
    }
}

