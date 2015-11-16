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
      def incoming(n: Node) = edges.filter(pred(_) == n)
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

  val g = new MyKidsGraph().newGraph(Set(e, k, j), Set((e, k), (k, j)))
                                                  //> g  : full_mixins.MyKidsGraph#Graph = full_mixins$$anonfun$main$1$AbstractMo
                                                  //| del$1$Graph@5056dfcb

  g.nodes                                         //> res0: scala.collection.immutable.Set[full_mixins.Person] = Set(Person(emily
                                                  //| ,19), Person(katelyn,17), Person(joshua,14))
  g.edges                                         //> res1: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set((Person(emily,19),Person(katelyn,17)), (Person(katelyn,17),Person
                                                  //| (joshua,14)))

  g.sources                                       //> res2: scala.collection.immutable.Set[full_mixins.Person] = Set(Person(emily
                                                  //| ,19))
  g.outgoing(e)                                   //> res3: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set((Person(emily,19),Person(katelyn,17)))
  g.outgoing(k)                                   //> res4: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set((Person(katelyn,17),Person(joshua,14)))
  g.outgoing(j)                                   //> res5: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set()

  g.incoming(e)                                   //> res6: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set()
  g.incoming(k)                                   //> res7: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set((Person(emily,19),Person(katelyn,17)))
  g.incoming(j)                                   //> res8: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set((Person(katelyn,17),Person(joshua,14)))
}

//
//
//
//