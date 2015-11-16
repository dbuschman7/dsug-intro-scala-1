
import scala.io.Codec.string2codec
import scala.io.Source
import scala.reflect.io.File

// Downloaded file from http://www.gutenberg.org/ebooks/100.txt.utf-8

object quizTime {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val Fmt = java.text.NumberFormat.getIntegerInstance
                                                  //> Fmt  : java.text.NumberFormat = java.text.DecimalFormat@674dc
  val Word = "\\b([A-Za-z\\-])+\\b".r             //> Word  : scala.util.matching.Regex = \b([A-Za-z\-])+\b

  //  val SrcDestination: String = "/Users/david/shakespeare_test.txt"
  val SrcDestination: String = "/Users/david/shakespeare.txt"
                                                  //> SrcDestination  : String = /Users/david/shakespeare.txt

  val lines = Source.fromFile(SrcDestination)("UTF-8").getLines
                                                  //> lines  : Iterator[String] = non-empty iterator
  lines.map { l => Word.findAllIn(l.toLowerCase()).toSeq }
    .toStream.flatMap(identity)
    .groupBy(identity)
    .values.map { f => (f.head, f.count(_ => true)) }
    .filter(_._2 != 1)
    .toSeq.sortBy(-_._2)
    .take(100)
    .foreach {
      case (word, count) => println(f"${word}%15s : ${Fmt.format(count)}%7s")
    }                                             //>             the :  27,826
                                                  //|             and :  26,803
                                                  //|               i :  22,502
                                                  //|              to :  19,373
                                                  //|              of :  18,298
                                                  //|               a :  14,675
                                                  //|             you :  13,914
                                                  //|              my :  12,482
                                                  //|            that :  11,537
                                                  //|              in :  11,143
                                                  //|              is :   9,804
                                                  //|               d :   8,935
                                                  //|             not :   8,745
                                                  //|             for :   8,339
                                                  //|            with :   8,060
                                                  //|              me :   7,770
                                                  //|              it :   7,736
                                                  //|               s :   7,715
                                                  //|              be :   7,136
                                                  //|            this :   6,896
                                                  //|            your :   6,888
                                                  //|             his :   6,857
                                                  //|              he :   6,674
                                                  //|             but :   6,275
                                                  //|              as :   5,978
                                                  //|            have :   5,906
                                                  //|            thou :   5,546
                                                  //|              so :   5,271
                                                  //|             him :   5,192
                                                  //|            will :   5,011
                                                  //|            what :   4,805
                                                  //|              by :   4,451
                                                  //|             thy :   4,032
                                                  //|       
                                                  //| Output exceeds cutoff limit.
}