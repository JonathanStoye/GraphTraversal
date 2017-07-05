package DepthFirstTraversal;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.*;

public class DepthFirstTraversal {

  class DFTGraph<V extends DFTVertex, E extends Edge> extends graph.Graph {}
  private int time = 0;
  private DFTGraph graph = new DFTGraph();
  private boolean acyclic = true;

  /**
   * Takes the given graph and creates new vertices of type DFTVertex.
   * Then each vertex is added to the graph, stored by the depthFirstTraversal-object.
   * Then initiates the depthFirstSearch.
   * @param inputGraph
   */
  public DepthFirstTraversal(Graph inputGraph) throws NotAcyclicException {
    for (Vertex vertex : (Collection<Vertex>)inputGraph.getVertices()) {
      DFTVertex newVertex = new DFTVertex(vertex.getId());
      newVertex.setColor("white");
      newVertex.setPredecessor(null);
      this.graph.addVertex(newVertex);
    }
    depthFirstSearch();
  }

  /**
   * Travereses each vertex in graph.
   * Every vertex is first set to color white and predecessors set to null.
   * If neighbor is found, thus is being visited via depthVisit() first.
   * @throws NotAcyclicException
   */
  private void depthFirstSearch() throws NotAcyclicException {
    // Start depthFirstSearch for every vertex.
    for (DFTVertex vertex : (Collection<DFTVertex>)this.graph.getVertices()){
      if (vertex.getColor() == "white"){
        depthVisit(vertex);
      }
    }
  }

  /**
   * Takes the given vertex and establishes DepthFirstVisit.
   * Color ist set to grey and firstVisit value is set.
   * If unvisited neighbor is found depthVisit will initiate for this vertex first.
   * If neighbor already
   * @param vertex
   * @throws NotAcyclicException
   */
  private void depthVisit(DFTVertex vertex) throws NotAcyclicException {
    vertex.setColor("grey");
    vertex.setFirstVisit(++time);

    for (DFTVertex neighbor : (Collection<DFTVertex>)graph.getNeighbours(vertex)){
      String color = neighbor.getColor();
      if (color == "white"){
        neighbor.setPredecessor(vertex);
        depthVisit(neighbor);
      } else if ( color == "grey" ){
        this.acyclic = false;
      }
    }

    vertex.setColor("black");
    vertex.setLastVisit(++time);
  }


  /**
   * Tries to sort vertices of graph topologically.
   * Throws exception if not possible.
   * @return Topologically sorted List of vertices
   * @throws NotAcyclicException
   */
  public List<DFTVertex> sortTopologically() throws NotAcyclicException {
    if (!this.acyclic) {
      throw new NotAcyclicException("Graph is not acyclic and thus can not be topologically sorted.");
    }
    List<DFTVertex> vertices = new ArrayList<>(this.graph.getVertices());
    vertices.sort(Comparator.comparing(DFTVertex::getLastVisit));
    return vertices;
  }
}
