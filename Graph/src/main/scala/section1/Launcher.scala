package section1

import section2.Traversal

object Launcher {
  def main(args: Array[String]): Unit = {
    val g = Graph[String]()
      .addEdge("London", "Lisbon")
      .addEdge("Lisbon", "Madrid")
      .addEdge("Madrid", "London")
      .addEdge("Madrid", "Rome")
      .addEdge("Rome", "London")
      .addEdge("Paris", "Rome")
    println(g.vertices)
    println(g.edges)
    println(g.neighbours("Madrid"))

    val g1 = Graph[String]()
      .addEdge("A", "B")
      .addEdge("B", "C")
      .addEdge("C", "E")
      .addEdge("C", "D")
      .addEdge("A", "G")
      .addEdge("G", "H")
      .addEdge("H", "F")
      .addEdge("F", "A")
      .addEdge("D", "E")


    Traversal.traversalDFSIterative("A",g1, println)
    println("//////")
    Traversal.traversalBFS("A", g1, println)
  }

}
