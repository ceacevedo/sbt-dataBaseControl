package com.co.devco.database.control.plugin

import sbt._
import sbt.Keys._
import slick.jdbc.meta.MTable
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

object DataBaseControlPlugin extends AutoPlugin {

//  override lazy val projectSettings = Seq(commands += createDataBase)

  protected val driver = com.typesafe.slick.driver.oracle.OracleDriver

  object autoImport {
//    val createDataBase = taskKey[Unit]("createDataBase")
  }
  def createDataBase() ={

    import driver.api._

    val tables: List[TableQuery[Table[Any]]] = List()

    val db = Database.forConfig("cobros")
    try {
      val existing = db.run(MTable.getTables)
      val f = existing.flatMap( v => {
        val names = v.map(mt => mt.name.name)
        val createIfNotExist = tables.filter( table =>
          !names.contains(table.baseTableRow.tableName)).map(_.schema.create)
        db.run(DBIO.sequence(createIfNotExist))
      })
      Await.result(f, Duration.Inf)
    } finally db.close
  }

}
