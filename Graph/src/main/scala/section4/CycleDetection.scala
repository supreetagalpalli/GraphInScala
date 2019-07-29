package section4
import section1.Graph

case class DfsCycleResult[V](visited: Set[V] = Set[V](), isCyclic: Boolean = false)
object CycleDetection {

  def containsCycle[V](vertex: V, graph: Graph[V],
                       visited: Set[V] = Set[V](),
                       ancester: Set[V] = Set[V]()): DfsCycleResult[V] = {
    if(ancester.contains(vertex)) DfsCycleResult(visited, isCyclic = true)
    else if(visited.contains(vertex)) DfsCycleResult(visited)
    else {
      graph.neighbours(vertex).foldLeft(DfsCycleResult(visited + vertex)){
        case (DfsCycleResult(v,true),_) => DfsCycleResult(v,isCyclic = true)
        case (currentRes, n) => containsCycle(n, graph, currentRes.visited, ancester + vertex)
      }
    }
  }
}
