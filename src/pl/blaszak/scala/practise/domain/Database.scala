package pl.blaszak.scala.practise.domain

import pl.blaszak.scala.practise.CommandLine

object Database {

  def createTable(database: Database): Database = {
    new Database(database.tables + (CommandLine.prompt("Nazwa tabeli") -> Table.create()))
  }

  def describeTable(database: Database): Database = {
    database.tables.get(CommandLine.prompt("Nazwa tabeli")) match {
      case Some(table) => table.describe()
      case _ => println("Tabela nie istnieje")
    }
    database
  }

  def insert(database: Database): Database = {
    val tableName = CommandLine.prompt("Nazwa tabeli")
    database.tables.get(tableName) match {
      case Some(table) => new Database(database.tables + (tableName -> table.insert()))
      case _ => println("Tabela nie istnieje"); database;
    }
  }

  def select(database: Database): Database = {
    database.tables.get(CommandLine.prompt("Nazwa tabeli")) match {
      case Some(table) => table.select()
      case _ => println("Tabela nie istnieje")
    }
    database
  }

  def delete(database: Database): Database = {
    val tableName = CommandLine.prompt("Nazwa tabeli")
    database.tables.get(tableName) match {
      case Some(table) => new Database(database.tables + (tableName -> table.delete()))
    }
  }
}

case class Database(tables: Map[String, Table]) {}