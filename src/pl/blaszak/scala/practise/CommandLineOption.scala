package pl.blaszak.scala.practise

import pl.blaszak.scala.practise.domain.Database

class CommandLineOption(val name: String, val exec: Database => Database) { }
