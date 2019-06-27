package chapter11_graph;

import java.util.LinkedList;
import java.util.List;

public class DirectLinkedGraph {

  private int nodeCount;
  private List[] adjEdge;

  public DirectLinkedGraph(int nodeCount) {
    this.nodeCount = nodeCount;
    this.adjEdge = new LinkedList[nodeCount];

    for (int i = 0; i < nodeCount; i++) {
      adjEdge[i] = new LinkedList();
    }
  }

  public void addEdge(int fromNode, int toNode) {
    if (checkVertexValid(fromNode) && checkVertexValid(toNode)) {
      adjEdge[fromNode].add(toNode);
    }
  }

  public boolean checkVertexValid(int node) {
    return node >= 0 && node < nodeCount;
  }

  public void removeEdge(int fromNode, int toNode) {
    if (checkVertexValid(fromNode) && checkVertexValid(toNode)) {
      adjEdge[fromNode].remove((Integer)toNode);
    }
  }

  public boolean getEdge(int fromNode, int toNode) {
    return adjEdge[fromNode].contains(toNode);
  }

  public void displayGraph() {
    for (int i = 0; i < adjEdge.length; i++) {
      for (int j = 0; j < adjEdge.length; j++) {
        if (adjEdge[i].contains(j)) System.out.print("1 ");
        else System.out.print("0 ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    DirectLinkedGraph graph = new DirectLinkedGraph(6);

    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    graph.addEdge(2, 0);
    graph.addEdge(2, 3);
    graph.addEdge(3, 2);
    graph.addEdge(3, 4);
    graph.addEdge(4, 5);
    graph.addEdge(5, 3);

    graph.displayGraph();

    graph.removeEdge(0, 1);

    graph.displayGraph();
  }

}
