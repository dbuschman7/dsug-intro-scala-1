// mixins  - from Martin Odersky - The Simple Parts - San Fransisco Scala  Users Group 2014
object full_mixins {
  println("Welcome to the Scala Full Mixins")     //> Welcome to the Scala Full Mixins

  trait Graphs {
    type Node
    type Edge
    type Graph <: GraphSig

    def pred(e: Edge): Node
    def succ(e: Edge): Node
    def newGraph(nodes: Set[Node], edges: Set[Edge]): Graph

    trait GraphSig {
      def nodes: Set[Node]
      def edges: Set[Edge]

      def outgoing(n: Node): Set[Edge]
      def incoming(n: Node): Set[Edge]

      def sources: Set[Node]
    }
  }

  trait AbstractModel extends Graphs {
    class Graph(val nodes: Set[Node], val edges: Set[Edge]) extends GraphSig {
      def outgoing(n: Node) = edges filter { succ(_) == n }
      def incoming(n: Node) = edges filter (pred(_) == n)
      lazy val sources = nodes filter (incoming(_).isEmpty)
    }

    def newGraph(nodes: Set[Node], edges: Set[Edge]) = new Graph(nodes, edges)
  }

  case class Person(name: String, age: Int)

  trait ConcreteModel extends Graphs {
    type Node = Person
    type Edge = (Person, Person)
    def succ(e: Edge) = e._1
    def pred(e: Edge) = e._2
  }

  //
  // Emily -> Katelyn -> Joshua
  // ////////////////////////////////////
  class MyKidsGraph extends AbstractModel with ConcreteModel

  val e = Person("emily", 19)                     //> e  : full_mixins.Person = Person(emily,19)
  val k = Person("katelyn", 17)                   //> k  : full_mixins.Person = Person(katelyn,17)
  val j = Person("joshua", 14)                    //> j  : full_mixins.Person = Person(joshua,14)

 // val g = new MyKidsGraph().newGraph(Set(e, k, j), Set((e, k), (k, j)))

//  g.nodes
//  g.edges

//  g.sources
//  g.outgoing(e)
//  g.outgoing(k)
//  g.outgoing(j)

//  g.incoming(e)
//  g.incoming(k)
//  g.incoming(j)
}

//
//
//
//