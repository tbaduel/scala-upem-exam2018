package fr.upem.partiel.part2.model

import fr.upem.partiel.part2.model.Movie._

// TODO You have to create all the classes you need for the exam
// TODO Don't forget to read the existing code and the unit tests to get some clues !

// TODO Create this model
trait Movie{
  val title : Title
  val director : Director
  val year : Year
  val views : Views
  val country : Country
}
case class MovieIm(title : Title, director : Director, year : Year, views : Views, country : Country) extends Movie


object Movie{

  def apply: Movie = ???

  // TODO Create this model
  trait Title{
    val title :String
  }
  case class TitleIm(title : String) extends Title


  // TODO Create this model
  trait Director{
    val fn : String
    val ln : String
  }
  case class DirectorIm(fn : String, ln : String) extends Director

  // TODO Create this model
  trait Year{
    val value : Int
  }
  case class YearIm(value : Int) extends Year

  // TODO Create this model
  trait Views{
    val value : Long
  }
  case class ViewsIm(value : Long) extends Views

  trait Country

  object Country {
    final case object France extends Country
    final case object England extends Country
    final case object Italy extends Country
    final case object Germany extends Country
    final case object UnitedStates extends Country
  }


  // TODO Create this method
  def movie(title: Title, director: Director, year: Year, views: Views, country: Country): Movie =
    MovieIm(title,director,year,views,country)

  // TODO Create this method
  def title(s: String): Title = TitleIm(s)

  // TODO Create this method
  def director(fn: String, ln: String): Director = DirectorIm(fn,ln)

  // TODO Create this method
  def year(value: Int): Year = YearIm(value)

  // TODO Create this method
  def views(value: Long): Views = ViewsIm(value)

}