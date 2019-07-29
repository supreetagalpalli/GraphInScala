package section2

import section1.Graph

import scala.collection.immutable.Queue

object Traversal {
  def traversalDFS[V](start: V, graph: Graph[V],f: V => Unit, visited: Set[V]): Set[V] = {
    if(visited.contains(start)) visited
    else {
      graph.neighbours(start).foldLeft(visited + start)((allVisited, n) => traversalDFS(n,graph,f,allVisited))
    }
  }

  def traversalDFSIterative[V](start: V, graph: Graph[V],f: V => Unit): Unit = {
    LazyList.iterate((List(start), Set[V](start))){case (stk, visted) =>
        var vertex = stk.head
        var newStack = graph.neighbours(vertex).filterNot(visted.contains) ++ stk.tail
        var newVisited = graph.neighbours(vertex).toSet ++ visted
      (newStack,newVisited)
    }.takeWhile(t => t._1.nonEmpty).foreach(t => f(t._1.head))
  }

  def traversalBFS[V](start: V, graph: Graph[V], f: V => Unit): Unit = {
    LazyList.iterate((Queue(start), Set[V](start))){ case (q, visted) =>
      val (vertex, rest) = q.dequeue
      val newQueue = rest.enqueue(graph.neighbours(vertex).filterNot(visted.contains))
      val newVisited = graph.neighbours(vertex).toSet ++ visted
      (newQueue, newVisited)
    }.takeWhile(t => t._1.nonEmpty).foreach(t => f(t._1.head))
  }
}
