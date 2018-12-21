package pl.blaszak.scala.practise.domain

import pl.blaszak.scala.practise.CommandLine

object Table {

  def createFields(count: Int, fields: List[String]): List[String] = if (count <= 0) fields else
    createFields(count - 1, fields ::: List(CommandLine.prompt("Pole")))

  def performFilter(table: Table, fieldName: String, fieldValue: String): Map[Long, Record] = {
    if(fieldName == "id") {
      table.records.get(fieldValue.toLong) match {
        case Some(record) => Map(fieldValue.toLong -> record)
        case _ => Map()
      }
    } else {
      table.records.filter(record => record._2.fieldValues.get(fieldName) match {
        case Some(value) => value == fieldValue
        case _ => false
      })
    }

  }

  def create(): Table = new Table(createFields(CommandLine.prompt("Liczba pól").toInt, List()), Map(), 1)

  def insert(table: Table): Table = new Table(table.fields, table.records + (table.id -> Record.create(table.fields, Map())), table.id + 1)

  def describe(table: Table): Table = {
    println("(implikowane) id")
    table.fields.foreach(field => println(field))
    table
  }

  def selectWithFilter(table: Table): Table = {
    performFilter(table, CommandLine.prompt("Pole filtrowania"), CommandLine.prompt("Wartość pola")).
    foreach(record => record._2.print(table.fields, record._1))
    table
  }

  def selectAll(table: Table): Table = {
    table.records.foreach(record => record._2.print(table.fields, record._1))
    table
  }

  def select(table: Table): Table = {
    CommandLine.prompt("Filtrować według pola? (t/n)").toLowerCase match {
      case "t" => selectWithFilter(table)
      case "n" => selectAll(table)
      case _ => println("Niewłaściwy wybór"); select(table);
    }
  }

  def delete(table: Table): Table = new Table(table.fields, table.records - CommandLine.prompt("ID").toLong, table.id)

}

case class Table(fields: List[String], records: Map[Long, Record], id: Long) {

  def delete(): Table = {
    Table.delete(this)
  }

  def select(): Table = {
    Table.select(this)
  }

  def insert(): Table = {
    Table.insert(this)
  }

  def describe(): Table = {
    Table.describe(this)
  }
}
