package pl.blaszak.scala.practise

import pl.blaszak.scala.practise.domain.Database

import scala.annotation.tailrec

object fDB {

  private val options = Map[String, CommandLineOption](
    "create" -> new CommandLineOption("Utwórz tabelę", Database.createTable),
    "describe" -> new CommandLineOption("Opisz tabelę", Database.describeTable),
    "insert" -> new CommandLineOption("Wstaw rekord", Database.insert),
    "delete" -> new CommandLineOption("Usuń rekord", Database.delete),
    "select" -> new CommandLineOption("Wybierz rekord", Database.select),
    "exit" -> new CommandLineOption("Zakończ", db => sys.exit))

  @tailrec
  def mainLoop(database: Database): Unit = mainLoop(
    CommandLine.optionPrompt(options) match {
      case Some(opt) => opt.exec(database)
      case _ => println("Nieprawidłowa operacja"); database
    }
  )

  def main(args: Array[String]): Unit = {
    mainLoop(new Database(Map()))
  }
}
