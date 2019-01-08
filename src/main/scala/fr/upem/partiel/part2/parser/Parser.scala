package fr.upem.partiel.part2.parser

import fr.upem.partiel.part2.model.Movie
import fr.upem.partiel.part2.model.Movie.Country._
import fr.upem.partiel.part2.model.Movie.{Country, _}
import play.api.libs.functional.syntax._
import play.api.libs.json._

object Parser {

  // TODO
  def toDirector: String => Option[Director] =  {
    case x if x.length == 0 => None
    case x if x.split(" ").length == 2 => {
      val Array(a,b) = x.split(" ") take 2
      Some(DirectorIm(a,b))
    }
    case _ => None
  }


  // TODO
  def toName: String => Title = {
    case x if x.length > 0 => TitleIm(x)
    case _ => TitleIm("")
  }

  // TODO
  def toCountry: String => Option[Country] = ???

  /*{
    case x if  match {
      case France => Some(France)
      case England => Some(England)
      case Italy => Some(Italy)
      case Germany => Some(Germany)
      case UnitedStates => Some(UnitedStates)
    }
    case _ => None
    }
    */


  // TODO
  def toYear: String => Option[Year] = {
    case x if x.toInt != 0 => x.toInt match {
      case n if n >= 1900 && n < 3000 => Some(YearIm(n))
      case _ => None
    }
    case _ => None
  }

  // TODO
  def toViews: BigDecimal => Option[Views] = ???

  implicit val directorReads = Reads[Director] {
    case JsString(value) => toDirector(value).map(JsSuccess(_)).getOrElse(JsError("Not a valid Director"))
    case _ => JsError("Not a valid type for Director")
  }

  implicit val nameReads = Reads[Title] {
    case JsString(value) => JsSuccess(toName(value))
    case _ => JsError("Not a valid type for Name")
  }

  implicit val countryReads = Reads[Country] {
    case JsString(value) => toCountry(value).map(JsSuccess(_)).getOrElse(JsError("Not a valid Country"))
    case _ => JsError("Not a valid type for Country")
  }

  implicit val yearReads = Reads[Year] {
    case JsString(value) => toYear(value).map(JsSuccess(_)).getOrElse(JsError("Not a valid Year"))
    case _ => JsError("Not a valid type for Year")
  }

  implicit val viewsReads = Reads[Views] {
    case JsNumber(value) => toViews(value).map(JsSuccess(_)).getOrElse(JsError("Not a valid Views"))
    case _ => JsError("Not a valid type for Views")
  }

  implicit val movieReads: Reads[Movie] = ??? /*
    (
    (__ \ "title").read[Title] and
      (__ \ "director").read[Director] and
      (__ \ "year").read[Year] and
      (__ \ "views").read[Views] and
      (__ \ "country").read[Country]
    ) (Movie.apply _)
 */
}
