object multiplication_tables {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  // List helper functions
  List.range(1, 10)                               //> res0: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
  List.range(1, 10, 2)                            //> res1: List[Int] = List(1, 3, 5, 7, 9)
  List.range(2, 11, 2)                            //> res2: List[Int] = List(2, 4, 6, 8, 10)

  List.fill(5)("foo")                             //> res3: List[String] = List(foo, foo, foo, foo, foo)
  List.fill(100)(1)                               //> res4: List[Int] = List(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                                                  //|  1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
                                                  //| 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                                                  //| , 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                                                  //|  1, 1, 1, 1, 1, 1)
  val tSize = 10                                  //> tSize  : Int = 10
  val nums = List.tabulate(tSize)(x => x + 1)     //> nums  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  val tables = nums.map { num => List.tabulate(tSize)(x => x * num) }
                                                  //> tables  : List[List[Int]] = List(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), List(0,
                                                  //|  2, 4, 6, 8, 10, 12, 14, 16, 18), List(0, 3, 6, 9, 12, 15, 18, 21, 24, 27), 
                                                  //| List(0, 4, 8, 12, 16, 20, 24, 28, 32, 36), List(0, 5, 10, 15, 20, 25, 30, 35
                                                  //| , 40, 45), List(0, 6, 12, 18, 24, 30, 36, 42, 48, 54), List(0, 7, 14, 21, 28
                                                  //| , 35, 42, 49, 56, 63), List(0, 8, 16, 24, 32, 40, 48, 56, 64, 72), List(0, 9
                                                  //| , 18, 27, 36, 45, 54, 63, 72, 81), List(0, 10, 20, 30, 40, 50, 60, 70, 80, 9
                                                  //| 0))
  //
  //
  // Patterns --Pimps
  // //////////////////////
  tables.map { row => row.davesFunction(3) }.map(println)
                                                  //> 0  , 1  , 2  , 3  , 4  , 5  , 6  , 7  , 8  , 9  
                                                  //| 0  , 2  , 4  , 6  , 8  , 10 , 12 , 14 , 16 , 18 
                                                  //| 0  , 3  , 6  , 9  , 12 , 15 , 18 , 21 , 24 , 27 
                                                  //| 0  , 4  , 8  , 12 , 16 , 20 , 24 , 28 , 32 , 36 
                                                  //| 0  , 5  , 10 , 15 , 20 , 25 , 30 , 35 , 40 , 45 
                                                  //| 0  , 6  , 12 , 18 , 24 , 30 , 36 , 42 , 48 , 54 
                                                  //| 0  , 7  , 14 , 21 , 28 , 35 , 42 , 49 , 56 , 63 
                                                  //| 0  , 8  , 16 , 24 , 32 , 40 , 48 , 56 , 64 , 72 
                                                  //| 0  , 9  , 18 , 27 , 36 , 45 , 54 , 63 , 72 , 81 
                                                  //| 0  , 10 , 20 , 30 , 40 , 50 , 60 , 70 , 80 , 90 
                                                  //| res5: List[Unit] = List((), (), (), (), (), (), (), (), (), ())

  //
  //
  //
  //
  implicit class IntPimp(n: Int) {
    def padded(length: Int): String = n.toString().padTo(length, ' ')
  }

  implicit class RowPimp(row: List[Int]) {
    def davesFunction(width: Int) = row.map(elem => elem.padded(width)).mkString(", ")
  }

}