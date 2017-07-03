package DepthFirstTraversal;

/**
 * Exception, if cycle is found in directional graph.
 */
public class NotAcyclicException extends Exception {
  public NotAcyclicException(String message) {
    super(message);
  }
}
