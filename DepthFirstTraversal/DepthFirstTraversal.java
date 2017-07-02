package DepthFirstTraversal;

import graph.Graph;
import graph.Vertex;

import java.util.Collection;

import static graph.GraphLesen.FileToGraph;

/**
 * Created by jonathan on 01.07.17.
 */
public class DepthFirstTraversal {

  private int time = 0;
  private DFTGraph graph = new DFTGraph();

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
    Graph graph = FileToGraph("./DepthFirstTraversal/TestData/graph8.txt", true);
    DepthFirstTraversal dft = new DepthFirstTraversal(graph);
  }

  private void depthFirstSearch(DFTVertex vertex, DFTGraph graph) {
    vertex.setColor("grey");
    vertex.setFirstVisit(this.getTime());

    for (DFTVertex neighbour : (Collection<DFTVertex>) graph.getNeighbours(vertex.getId())) {
      if (neighbour.getColor() == "white") {
        neighbour.setPredecessor(vertex);
        this.depthFirstSearch(neighbour, graph);
      }
    }

    vertex.setColor("black");
    vertex.setLastVisit(this.getTime());
  }

  private int getTime() {
    return ++this.time;
  }
}
