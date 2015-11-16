object referential_transparency {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  // If an expression can be replaced by its value without changing the behavior of the
  // program, it is said to be referentially transparent.
  var balance = 1000                              //> balance  : Int = 1000

  def withdraw(amount: Int): Int = {
    balance = balance - amount
    balance
  }                                               //> withdraw: (amount: Int)Int

  // side effects
  withdraw(100)                                   //> res0: Int = 900
  withdraw(100)                                   //> res1: Int = 800

  case class Balance(val name: String, val balance: Int) {
    def withdraw(amount: Int) = copy(balance = balance - amount)
  }

  val f1 = Balance("DaVe.", 1000).withdraw(100)   //> f1  : referential_transparency.Balance = Balance(DaVe.,900)
  val f2 = f1.withdraw(100)                       //> f2  : referential_transparency.Balance = Balance(DaVe.,800)
  
}