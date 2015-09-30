//
object full_mixins {
  println("Welcome to the Scala Full Mixins")     //> Welcome to the Scala Full Mixins

  trait Graphs {
    type Node <: Product
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
      def outgoing(n: Node) = edges filter { p =>
        val inter = pred(p)
        println(s"Node ${n} - inter = ${inter}")
        inter == n
      }
      def incoming(n: Node) = edges filter (succ(_) == n)
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

  class MyGraph extends AbstractModel with ConcreteModel

  val e = Person("emily", 19)                     //> e  : full_mixins.Person = Person(emily,19)
  val k = Person("katelyn", 17)                   //> k  : full_mixins.Person = Person(katelyn,17)
  val j = Person("joshua", 14)                    //> j  : full_mixins.Person = Person(joshua,14)

  val g = new MyGraph().newGraph(Set(e, k, j), Set((e, k), (k, j)))
                                                  //> g  : full_mixins.MyGraph#Graph = full_mixins$$anonfun$main$1$AbstractModel$
                                                  //| 1$Graph@5056dfcb

  g.nodes                                         //> res0: scala.collection.immutable.Set[full_mixins.Person] = Set(Person(emily
                                                  //| ,19), Person(katelyn,17), Person(joshua,14))
  g.edges                                         //> res1: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set((Person(emily,19),Person(katelyn,17)), (Person(katelyn,17),Person
                                                  //| (joshua,14)))

  g.sources                                       //> res2: scala.collection.immutable.Set[full_mixins.Person] = Set(Person(joshu
                                                  //| a,14))
  g.outgoing(e)                                   //> Node Person(emily,19) - inter = Person(katelyn,17)
                                                  //| Node Person(emily,19) - inter = Person(joshua,14)
                                                  //| res3: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set()
  g.outgoing(k)                                   //> Node Person(katelyn,17) - inter = Person(katelyn,17)
                                                  //| Node Person(katelyn,17) - inter = Person(joshua,14)
                                                  //| res4: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set((Person(emily,19),Person(katelyn,17)))
  g.outgoing(j)                                   //> Node Person(joshua,14) - inter = Person(katelyn,17)
                                                  //| Node Person(joshua,14) - inter = Person(joshua,14)
                                                  //| res5: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set((Person(katelyn,17),Person(joshua,14)))

  g.incoming(e)                                   //> res6: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set((Person(emily,19),Person(katelyn,17)))
  g.incoming(k)                                   //> res7: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set((Person(katelyn,17),Person(joshua,14)))
  g.incoming(j)                                   //> res8: scala.collection.immutable.Set[(full_mixins.Person, full_mixins.Perso
                                                  //| n)] = Set()
}