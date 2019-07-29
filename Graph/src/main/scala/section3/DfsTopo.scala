package section3

import section3.GameGraph.g

object DfsTopo {


  case class DfsStep(visited: Set[String] = Set(), sort: List[String] = Nil)

  def topoDfsSort(node: String, dfsStep: DfsStep): DfsStep = {
    if (dfsStep.visited.contains(node)) dfsStep
    else {
      val preDfsStep = dfsStep.copy(visited = dfsStep.visited + node)
      val postDfsStep = g.neighbours(node)
        .foldLeft(preDfsStep)((step, n) => topoDfsSort(n, step))
      postDfsStep.copy(sort = node +: postDfsStep.sort)
    }
  }

  def main(args: Array[String]): Unit = {
    val result = g.vertices
      .foldLeft(DfsStep())((step, n) => topoDfsSort(n, step)).sort
    println(result)
  }


}
