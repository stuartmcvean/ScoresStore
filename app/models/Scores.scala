package models

import play.api.libs.json.Json
import reactivemongo.bson.{BSONDocumentReader, BSONDocumentWriter, Macros}

case class Score(user: String, score: Int)

case class Scores(id: String, scores: List[Score])

object Score {
  implicit val format = Json.format[Score]
  implicit def scoreReader: BSONDocumentReader[Score] = Macros.reader[Score]
  implicit def scoreWriter: BSONDocumentWriter[Score] = Macros.writer[Score]
}

object Scores {
  implicit val format = Json.format[Scores]
  implicit def scoresReader: BSONDocumentReader[Scores] = Macros.reader[Scores]
  implicit def scoresWriter: BSONDocumentWriter[Scores] = Macros.writer[Scores]
}
