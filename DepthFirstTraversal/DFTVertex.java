package DepthFirstTraversal;

/**
 * Created by jonathan on 02.07.17.
 */
public class DFTVertex extends graph.Vertex {
  private String color;
  private int firstVisit;
  private int lastVisit;
  private DFTVertex predecessor;

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getFirstVisit() {
    return firstVisit;
  }

  public void setFirstVisit(int firstVisit) {
    this.firstVisit = firstVisit;
  }

  public int getLastVisit() {
    return lastVisit;
  }

  public void setLastVisit(int lastVisit) {
    this.lastVisit = lastVisit;
  }

  public DFTVertex getPredecessor() {
    return predecessor;
  }

  public void setPredecessor(DFTVertex predecessor) {
    this.predecessor = predecessor;
  }

  public DFTVertex(int id) {
    super(id);
  }
}
