package controllers

import javax.inject.Inject

import models.Scores
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import repositories.ScoresRepository
import scala.concurrent.ExecutionContext.Implicits.global

class ScoreStoreController @Inject() (cc: ControllerComponents, scoresRepository: ScoresRepository) extends AbstractController(cc) {

  def addScores() = Action.async(parse.json[Scores]) { request =>
    scoresRepository.addScores(request.body).map { _ =>
      NoContent
    }
  }

  def getScores(id: String) = Action.async { _ =>
    scoresRepository.getScores(id).map {
      case Some(scores) =>
        Ok(Json.toJson(scores))
      case None =>
        NotFound
    }
  }
}

