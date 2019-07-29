package section1

class UnDirectedGraph[V](adjList: Map[V, List[V]]) extends DirectedGraph[V](adjList) {
  override def addEdge(a: V, b: V): UnDirectedGraph[V] = {
    val aNeighbour = b +: neighbours(a)
    val bNeighbour = a +: neighbours(b)
    new UnDirectedGraph(adjList + (a -> aNeighbour, b -> bNeighbour))
  }
}
