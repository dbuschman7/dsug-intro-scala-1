object functional4 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  // call by name - not evalulated unitl needed
  //  def myIf(cond: Boolean, thenPart: Int, elsePart: Int) =
  def myIf(cond: Boolean, thenPart: => Int, elsePart: => Int) =
    if (cond) thenPart else elsePart              //> myIf: (cond: Boolean, thenPart: => Int, elsePart: => Int)Int

  myIf(1 < 2, 10, 20)                             //> res0: Int = 10

  def fun1() = { println("fun1"); 10 }            //> fun1: ()Int
  def fun2() = { println("fun2"); 20 }            //> fun2: ()Int

  myIf(1 < 2, fun1(), fun2())                     //> fun1
                                                  //| res1: Int = 10

}