package section4

import section1.Graph

object ScalaDetectionLauncher {
  def main(args: Array[String]): Unit = {
    val g1 = Graph(Map("A" -> List("C"),
      "B" -> List("A", "C")
      ))
    val res = g1.vertices.exists(v => CycleDetection.containsCycle(v,g1).isCyclic)
    println(res)
  }

}
