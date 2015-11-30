package io.lightspeed7.dsug.akka

import org.scalatest._
import akka.actor._
import scala.concurrent.duration._
import scala.concurrent.Await
import scala.collection.concurrent.TrieMap
import scala.util.Random

// Domain 
// ////////////
case class Serve(game: Int, hits: Int) // number of hits
case class Ball(game: Int, remaining: Int) { // remaining hits
  def decrement = copy(remaining = remaining - 1)
}
case class Point(game: Int, player: String)

case class PlayGames(count: Int, maxGameLength: Int)
case class PlayGame(game: Int, starter: ActorPath, other: ActorPath, length: Int)
case class WinnerIs()

// Actors 
// ////////////////
class Player(name: String, scorer: ActorRef) extends Actor {
  def receive = {
    case b: Ball if b.remaining > 0 => sender ! b.decrement
    case b: Ball                    => scorer ! Point(b.game, name)
    case Serve(game, hits)         => sender ! Ball(game, hits)
    case unk                        => println(s"Unknown message type - ${unk.toString}")
  }
}

class Scorer extends Actor {

  var scores: List[Point] = List()
  var games: Int = 0 // total games played

  def receive = {
    case p: Point     => { scores = scores :+ p; if (scores.size == games) self ! WinnerIs }
    case g: PlayGames => { println(s"Games registered - ${g.count}"); games = g.count }
    case WinnerIs =>
      scores.sortBy(_.game).map { pt => println(s"Game ${pt.game} winner is ${pt.player}") }
      val points = scores.groupBy(_.player).toSeq.map { case (player, pts) => (player, pts.size) }
      val ordered = points.sortBy { case (player, cnt) => 0 - cnt }
      val score = ordered.map(_._2).mkString(" - ", " games to ", ".")
      println(s"The winner is ${ordered.head._1}${score}")
  }
}

class Umpire(playerList: List[ActorRef], scorer: ActorRef) extends Actor {
  def receive = {
    case pg: PlayGames => {
      scorer ! pg // register game count
      (1 to pg.count).map { c =>
        val starter = playerList(Random.nextInt(playerList.size)).path
        val other = playerList.filter { p => p.path != starter }.head.path
        val game = PlayGame(c, starter, other, Random.nextInt(pg.maxGameLength))
        println(game)
        self ! game
      }
    }
    case PlayGame(game, stPath, otPath, count) => {
      val starter = playerList.filter(p => p.path == stPath).head
      val other = playerList.filter(p => p.path == otPath).head
      starter.tell(Serve(game, count), other)
    }
  }
}

class AkkaTest extends FunSuite {

  test("run an akka system") {
    val akka = ActorSystem("ping-pong") //> akka  : akka.actor.ActorSystem = akka://ping-pong

    val scorer = akka.actorOf(Props(classOf[Scorer]))
    val player1 = akka.actorOf(Props(classOf[Player], "player1", scorer))
    val player2 = akka.actorOf(Props(classOf[Player], "player2", scorer))
    val umpire = akka.actorOf(Props(classOf[Umpire], List(player1, player2), scorer))

    umpire ! PlayGames(Random.nextInt(100), 30)

    Thread.sleep(5 * 1000)
    Await.result(akka.terminate(), 10 seconds)
  }
}