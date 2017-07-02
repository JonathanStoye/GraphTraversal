package DepthFirstTraversal;

import graph.Graph;

import java.util.List;

import static graph.GraphLesen.FileToGraph;

/**
 * Created by jonathan on 02.07.17.
 */
public class Main {
  public static void main(String[] args) {
    Graph graph8 = FileToGraph("./DepthFirstTraversal/TestData/graph8.txt", true);
    Graph graph9 = FileToGraph("./DepthFirstTraversal/TestData/graph9.txt", true);
    Graph graph20 = FileToGraph("./DepthFirstTraversal/TestData/graph20.txt", true);

    DepthFirstTraversal dft1 = new DepthFirstTraversal(graph8);
    DepthFirstTraversal dft2 = new DepthFirstTraversal(graph9);
    DepthFirstTraversal dft3 = new DepthFirstTraversal(graph20);

    try {
      List<DFTVertex> sortedGraph8 = dft1.sortTopologically();
      List<DFTVertex> sortedGraph9 = dft2.sortTopologically();
      List<DFTVertex> sortedGraph20 = dft3.sortTopologically();
      for (DFTVertex vertex : sortedGraph8) {
        System.out.println(vertex.toString());
      }
      for (DFTVertex vertex : sortedGraph9) {
        System.out.println(vertex.toString());
      }
      for (DFTVertex vertex : sortedGraph20) {
        System.out.println(vertex.toString());
      }
    } catch (NotAcyclicException e) {
      System.out.println(e.getMessage());
    }
  }
}
