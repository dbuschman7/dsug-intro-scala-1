
import scala.io.Codec.string2codec
import scala.io.Source
import scala.reflect.io.File

object quizTime {
  println("Welcome to the Scala worksheet")
  val Fmt = java.text.NumberFormat.getIntegerInstance
  val Word = "\\b([A-Za-z\\-])+\\b".r

  val SrcDestination: String = "/Users/david/shakespeare_test.txt"
  val SrcDestination: String = "/Users/david/shakespeare.txt"

  val lines = Source.fromFile(SrcDestination)("UTF-8").getLines

 // lines.map { l => Word.findAllIn(l.toLowerCase()).toSeq }
 //   .toStream.flatMap(identity)
 //   .groupBy(identity).values.map { f => (f.head, f.count(_ => true)) }
 //   .filter(_._2 != 1)
 //   .toSeq.sortBy(-_._2)
 //   .take(30)
 //   .foreach {
 //     case (word, count) => println(f"${word}%15s : ${Fmt.format(count)}%7s")
 //   }
}