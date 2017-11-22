package repositories

import javax.inject.Singleton

import models.Scores
import reactivemongo.api.{DefaultDB, MongoConnection, MongoDriver}
import reactivemongo.bson.document

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class ScoresRepository {

  val mongoUri = "mongodb://localhost:27017/scoresdb"

  val driver = MongoDriver()
  val parsedUri = MongoConnection.parseURI(mongoUri)
  val connection = parsedUri.map(driver.connection)

  val futureConnection = Future.fromTry(connection)
  def db1: Future[DefaultDB] = futureConnection.flatMap(_.database("scoresdb"))
  def scoresCollection = db1.map(_.collection("scores"))

  def addScores(scores: Scores) = {
    val selector = document(
      "id" -> scores.id
    )

    scoresCollection.flatMap(_.update(selector, scores, upsert = true).map(_ => {}))
  }

  def getScores(id: String): Future[Option[Scores]] = {
    val selector = document(
      "id" -> id
    )

    scoresCollection.flatMap(_.find(selector).one[Scores])
  }

}
