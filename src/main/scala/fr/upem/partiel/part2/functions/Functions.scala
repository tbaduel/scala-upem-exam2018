package fr.upem.partiel.part2.functions

import fr.upem.partiel.part2.model.Movie
import fr.upem.partiel.part2.model.Movie.{Director, DirectorIm, ViewsIm}

object Functions {

  // TODO
  lazy val getDirectorNames: List[Movie] => List[String] = {
    case x :: xs => x.director match {
      case DirectorIm(fn, ln) => fn + " " + ln :: getDirectorNames(xs)
      case DirectorIm(_, _) => Nil
    }
    case Nil => List()
  }


  // TODO
  lazy val viewMoreThan: Long => List[Movie] => List[Movie] = x => {
    case m :: xs => m.views match{
      case ViewsIm(v) if v >= x => m :: viewMoreThan(x)(xs)
      case ViewsIm(_) => viewMoreThan(x)(xs)
    }
    case _ :: xs => viewMoreThan(x)(xs)
    case Nil => List()
  }

  // TODO
  lazy val byDirector: List[Movie] => Map[Director, List[Movie]] = ??? /*  {
    case x :: xs =>

    case Nil => _

*/



}
