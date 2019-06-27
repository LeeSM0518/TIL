package chapter11_graph;

public class DirectArrayGraph {

  private int nodeCount;
  private int[][] edges;

  public DirectArrayGraph(int nodeCount) {
    this.nodeCount = nodeCount;
    this.edges = new int[nodeCount][nodeCount];
  }

  public void addEdge(int fromNode, int toNode) {
    if (checkVertexValid(fromNode) && checkVertexValid(toNode)) edges[fromNode][toNode] = 1;
  }

  private boolean checkVertexValid(int node) {
    return node < nodeCount && node >= 0;
  }

  public void removeEdge(int fromNode, int toNode) {
    if (checkVertexValid(fromNode) && checkVertexValid(toNode)) edges[fromNode][toNode] = 0;
  }

  public int getEdge(int fromNode, int toNode) {
    return (checkVertexValid(fromNode) && checkVertexValid(toNode)) ? edges[fromNode][toNode] : 0;
  }

  public void displayGraph() {
    for (int i = 0; i < nodeCount; i++) {
      for (int j = 0; j < nodeCount; j++) {
        System.out.print(edges[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public int getNodeCount() {
    return nodeCount;
  }

  public static void main(String[] args) {
    DirectArrayGraph graph = new DirectArrayGraph(6);

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
