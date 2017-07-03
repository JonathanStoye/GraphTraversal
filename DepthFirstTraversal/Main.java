package DepthFirstTraversal;

import graph.Graph;

import java.util.List;

import static graph.GraphLesen.FileToGraph;

public class Main {
  public static void main(String[] args) {
    Graph graph8 = FileToGraph("./DepthFirstTraversal/TestData/graph8.txt", true);
    Graph graph9 = FileToGraph("./DepthFirstTraversal/TestData/graph9.txt", true);
    Graph graph20 = FileToGraph("./DepthFirstTraversal/TestData/graph20.txt", true);

    /*
      Graphs are being put into DepthFirstTraversal-objects (DFT) in order to establish the search.
      A new graph is stored in each DFT and traversed using the DepthFirstTraversal-Algorithm.
     */

    try {
      DepthFirstTraversal dft1 = new DepthFirstTraversal(graph8);
      DepthFirstTraversal dft2 = new DepthFirstTraversal(graph9);
      DepthFirstTraversal dft3 = new DepthFirstTraversal(graph20);
      /*
        Tries to sort vertices in each DFT topologically.
        If cycle is found, a NotAcyclicException is thrown.
      */
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
