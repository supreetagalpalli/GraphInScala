package section5
import section1.WeightedEdge
import section5.TravelGraph.g

import scala.util.Try

case class ShortStep(parent: Map[String, String],
                     unProccessed: Set[String],
                     dist: Map[String, Int]){
  def extractMin(): Option[(String, Int)] = Try(unProccessed.minBy(n => dist(n))).toOption.map(n => (n, dist(n)))
}

class DijkstraShortestPath {
  val sDistance = g.vertices.map(_ -> Int.MaxValue).toMap + ("Mumbai" -> 0)

  def shortestPath(step: ShortStep): ShortStep = {
    step.extractMin().map { case (n, currentD) =>
      val newDists = g.neighboursWtWeights(n).collect {
        case WeightedEdge(m, w) if step.dist.get(m).exists(_ > currentD + w) =>
          m -> (currentD + w)
      }
      val newParents = newDists.map { case (m, _) => m -> n }
      shortestPath(ShortStep(step.parent ++ newParents,
        step.unProccessed - n,
        step.dist ++ newDists))
    }.getOrElse(step)
  }

  def extractSPaths(node: String, parents: Map[String, String]): List[String] =
    parents.get(node).map(p => node +: extractSPaths(p, parents))
      .getOrElse(List(node))

  val result = shortestPath(ShortStep(Map(), g.vertices.toSet, sDistance))
  result.dist

  g.vertices.foreach(n => println(extractSPaths(n, result.parent).reverse))

}


