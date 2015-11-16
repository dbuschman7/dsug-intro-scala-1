class Pascal {
  lazy val pascalStream: Stream[Seq[Long]] = Seq(1L) #:: pascalStream.scanLeft(Seq(1L, 1L)) { (prev, _) =>
    (Seq(0L) ++ prev :+ 0L) sliding 2 map (_.sum) toList
  }
}

object pascalTriangle {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val p = new Pascal()                            //> p  : Pascal = Pascal@511baa65
  val f = p.pascalStream                          //> f  : Stream[Seq[Long]] = Stream(List(1), ?)
  val t = f.take(10).toList                       //> t  : List[Seq[Long]] = List(List(1), List(1, 1), List(1, 2, 1), List(1, 3, 3
                                                  //| , 1), List(1, 4, 6, 4, 1), List(1, 5, 10, 10, 5, 1), List(1, 6, 15, 20, 15, 
                                                  //| 6, 1), List(1, 7, 21, 35, 35, 21, 7, 1), List(1, 8, 28, 56, 70, 56, 28, 8, 1
                                                  //| ), List(1, 9, 36, 84, 126, 126, 84, 36, 9, 1))

  val iter = f.iterator                           //> iter  : Iterator[Seq[Long]] = non-empty iterator
  for (i <- 1 until 10) { println(iter.next()) }  //> List(1)
                                                  //| List(1, 1)
                                                  //| List(1, 2, 1)
                                                  //| List(1, 3, 3, 1)
                                                  //| List(1, 4, 6, 4, 1)
                                                  //| List(1, 5, 10, 10, 5, 1)
                                                  //| List(1, 6, 15, 20, 15, 6, 1)
                                                  //| List(1, 7, 21, 35, 35, 21, 7, 1)
                                                  //| List(1, 8, 28, 56, 70, 56, 28, 8, 1)
  for (i <- 1 until 10) { println(iter.next()) }  //> List(1, 9, 36, 84, 126, 126, 84, 36, 9, 1)
                                                  //| List(1, 10, 45, 120, 210, 252, 210, 120, 45, 10, 1)
                                                  //| List(1, 11, 55, 165, 330, 462, 462, 330, 165, 55, 11, 1)
                                                  //| List(1, 12, 66, 220, 495, 792, 924, 792, 495, 220, 66, 12, 1)
                                                  //| List(1, 13, 78, 286, 715, 1287, 1716, 1716, 1287, 715, 286, 78, 13, 1)
                                                  //| List(1, 14, 91, 364, 1001, 2002, 3003, 3432, 3003, 2002, 1001, 364, 91, 14, 
                                                  //| 1)
                                                  //| List(1, 15, 105, 455, 1365, 3003, 5005, 6435, 6435, 5005, 3003, 1365, 455, 1
                                                  //| 05, 15, 1)
                                                  //| List(1, 16, 120, 560, 1820, 4368, 8008, 11440, 12870, 11440, 8008, 4368, 182
                                                  //| 0, 560, 120, 16, 1)
                                                  //| List(1, 17, 136, 680, 2380, 6188, 12376, 19448, 24310, 24310, 19448, 12376, 
                                                  //| 6188, 2380, 680, 136, 17, 1)

}