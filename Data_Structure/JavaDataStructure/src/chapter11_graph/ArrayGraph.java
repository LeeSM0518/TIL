package chapter11_graph;

import java.util.LinkedList;
import java.util.Queue;

public class ArrayGraph extends DirectArrayGraph {

  private static final int DIRECT = 0;
  private static final int UNDIRECT = 1;

  private int graphType;

  public ArrayGraph(int graphType, int nodeCount) {
    super(nodeCount);
    this.graphType = graphType;
  }

  public void addEdgeAG(int fromNode, int toNode) {
    addEdge(fromNode, toNode);
    if (graphType == UNDIRECT) addEdge(toNode, fromNode);
  }

  public void removeEdgeAG(int fromNode, int toNode) {
    removeEdge(fromNode, toNode);
    if (graphType == UNDIRECT) removeEdge(fromNode, toNode);
  }

  public void traversalDFS(int startNodeIndex) {
    recursiveTraversalDFS(startNodeIndex, new int[getNodeCount()]);
  }

  private void recursiveTraversalDFS(int startNodeIndex, int[] visit) {
    visit[startNodeIndex] = 1;
    System.out.println("노드-[" + startNodeIndex + "] 방문");
    for (int i = 0; i < getNodeCount(); i++) {
      if (i != startNodeIndex) {
        if (getEdge(startNodeIndex, i) != 0 && visit[i] == 0) {
          recursiveTraversalDFS(i, visit);
        }
      }
    }
  }

  public void traversalBFS(int startNodeIndex) {
    Queue<Integer> queue = new LinkedList<>();
    int[] visit = new int[getNodeCount()];

    visit[startNodeIndex] = 1;
    queue.add(startNodeIndex);

    while (!queue.isEmpty()) {
      int node = queue.poll();
      System.out.println("노드-[" + node + "] 방문");
      for (int i = 0; i < getNodeCount(); i++) {
        if (i != node && getEdge(node, i) != 0 && visit[i] == 0) {
          visit[i] = 1;
          queue.add(i);
        }
      }
    }
  }

  public static void main(String[] args) {
    ArrayGraph arrayGraph = new ArrayGraph(UNDIRECT, 4);

    arrayGraph.addEdgeAG(0, 1);
    arrayGraph.addEdgeAG(0, 2);
    arrayGraph.addEdgeAG(1, 3);

    arrayGraph.traversalDFS(0);
    arrayGraph.traversalBFS(0);
  }
}