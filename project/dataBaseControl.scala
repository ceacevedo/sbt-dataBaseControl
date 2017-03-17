import sbt._
import Keys._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object DataBseControlPlugin extends AutoPlugin {

  override lazy val projectSettings = Seq(commands += createDataBase)

  protected val driver = com.typesafe.slick.driver.oracle.OracleDriver // scalastyle:ignore

  object autoImport {
    val createDataBase = taskKey[Unit]("createDataBase")
  }

  import autoImport._
  def createDataBase() ={

    val tables = List(
//      TablasGruposCoberturas.gruposCoberturas,
//      TablasReaseguroFacturaProcesado.reaseguroFacturaProcesado
    )

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
