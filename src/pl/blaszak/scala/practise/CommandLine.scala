package pl.blaszak.scala.practise

object CommandLine {

  def wrapOutput(wrapper: String, output: String): Unit = {
    println(wrapper)
    print(output)
    println(wrapper)
  }

  def optionPrompt(options: Map[String, CommandLineOption]): Option[CommandLineOption] = {
    println("-------------- [Opcje] --------------")
    options.foreach(option => println(option._1 + ") " + option._2.name))
    options.get(prompt("Akcja").toLowerCase)
  }

  def prompt(msg: String): String = {
    print(msg + ": ")
    readLine()
  }

}
