package DepthFirstTraversal;

import graph.Graph;
import graph.Vertex;

import java.util.*;

import static graph.GraphLesen.FileToGraph;

/**
 * Created by jonathan on 01.07.17.
 */
public class DepthFirstTraversal {

  private int time = 0;
  private DFTGraph graph = new DFTGraph();
  private boolean acyclic = true;

  public DepthFirstTraversal(Graph inputGraph) {
    for (Vertex vertex : (Collection<Vertex>)inputGraph.getVertices()) {
      DFTVertex newVertex = new DFTVertex(vertex.getId());
      newVertex.setColor("white");
      newVertex.setPredecessor(null);
      this.graph.addVertex(newVertex);
    }

    for (DFTVertex vertex : (Collection<DFTVertex>)this.graph.getVertices()) {
      if (vertex.getColor() == "white") {
        this.depthFirstSearch(vertex, this.graph);
      }
    }
  }

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

  private void depthFirstSearch(DFTVertex vertex, DFTGraph graph) {
    vertex.setColor("grey");
    vertex.setFirstVisit(this.getTime());

    for (DFTVertex neighbour : (Collection<DFTVertex>) graph.getNeighbours(vertex.getId())) {
      if (neighbour.getColor() == "white") {
        neighbour.setPredecessor(vertex);
        this.depthFirstSearch(neighbour, graph);
      } else if (neighbour.getColor() == "grey") {
        this.acyclic = false;
      }
    }

    vertex.setColor("black");
    vertex.setLastVisit(this.getTime());
  }

  public List<DFTVertex> sortTopologically() throws NotAcyclicException {
    if (!this.acyclic) {
      throw new NotAcyclicException("Graph is not acyclic and thus can not be topologically sorted.");
    }
    List<DFTVertex> vertices = new ArrayList<DFTVertex>(this.graph.getVertices());
    vertices.sort(Comparator.comparing(DFTVertex::getLastVisit));
    return vertices;
  }

  private int getTime() {
    return ++this.time;
  }
}
