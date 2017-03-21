package com.co.devco.database.control.plugin

import sbt._
import sbt.Keys._
import slick.jdbc.meta.MTable
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

object DataBaseControlPlugin extends AutoPlugin {

//

  protected val driver = com.typesafe.slick.driver.oracle.OracleDriver

  object autoImport {
    import driver.api._

    val slickDataBaseConfig = settingKey[String]("slickDataBaseConfig")
    val slickTables = settingKey[List[TableQuery[Table[Any]]]]("slickTables")
    val createDataBase = taskKey[Unit]("createDataBase")

    lazy val dataBaseSettings: Seq[Def.Setting[_]] = Seq(
      slickDataBaseConfig := "cobros",
      slickTables := List(),
      createDataBase := createDataBaseTask
    )
  }

  import autoImport._

  override def trigger = allRequirements
  override lazy val projectSettings = inConfig(Compile)(dataBaseSettings)


  lazy val createDataBaseTask = Def.task{

    import driver.api._

    val tables: List[TableQuery[Table[Any]]] = List()
    val slickConfig: String = ""

    val db = Database.forConfig(slickConfig)
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
