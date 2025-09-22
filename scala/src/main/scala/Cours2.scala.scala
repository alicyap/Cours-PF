object Cours2 {
  def firstTwo(l: List[String]): List[String] = l.slice(0, 2)

  def lastTwo(l: List[String]): List[String] = {
    var taille = l.size
    l.slice(taille - 2, taille)
  }

  def movedFirstTwoToTheEnd(l: List[String]): List[String] = {
    var apresDeuxPremiers = l.slice(2, l.size)
    var deuxPremiers = firstTwo(l)
    return apresDeuxPremiers ++ deuxPremiers
  }

  def insertedBeforeLast(l: List[String], e: String): List[String] = {
    var toutSaufDernier = l.slice(0, l.size-1) // l.init
    var dernier = l.last
    return toutSaufDernier.appended(e).appended(dernier)
  }

  @main
  def main(): Unit = {
    val listeProbleme = List("un seul truc")
    println(firstTwo(listeProbleme))
    val liste: List[String] = List("un", "deux", "trois", "quatre")
    println(liste)
    println(firstTwo(liste))
    println(lastTwo(liste))
    println(movedFirstTwoToTheEnd(liste))
    println(insertedBeforeLast(liste, "ZZZ"))
    println(liste)
  }
}
