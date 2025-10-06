object wordscore {
  def score(word: String): Int = word.replaceAll("a", "").length
  def bonus(word: String): Int = if (word.contains("c")) 5 else 0
  def malus(word: String): Int = if (word.contains("s")) 7 else 0
  def rankedWords(scoring: String => Int, words: List[String]): List[String] = {
    words.sortBy(scoring).reverse
  }

  def wordScores(scoring: String => Int, words: List[String]): List[Int] = {
    words.map(scoring)
  }

  def highScoringWords(scoring: String => Int, words: List[String]): List[String] = {
    words.filter(w => scoring(w) > 1)
  }

  def lenWords(words: List[String]): List[String] = {
    words.filter(_.length < 5)
  }
  
  def moreThanOneL(words: List[String]): List[String] = {
    words.filter(w => w.count(_ == 'l') > 1)
  }//faire la version générique pour qu'on puisse appliquer n'importe quel filtre
  
  def evenNbrLength(words: List[String]): List[String] = {
    words.filter(w => w.length % 2 == 0)
  }

  def highScoringWords2(scoring: String => Int): (List[String], Int) => List[String] = {
  (words: List[String], n: Int) => words.filter(w => scoring(w) > n)
  }// version currifiée
  
  def add(x: Int, y: Int): Int = x + y // version normale
  
  def sub(x: Int)(y: Int): Int = x - y // currifiée
  
  def curry[T, U, V](f: (T, U) => V): T => U => V = {
    T => U => f(T, U) 
  }
  
  def uncurry[T, U, V](f: T => U => V): (T, U) => V = {
    (T, U) => f(T)(U)
  }

  def cumulativeScore(scoring: String => Int, words: List[String]): Int = {
    words.foldLeft(0)((acc, word) => acc + scoring(word))
  }
  
  def totalLength(words: List[String]): Int = {
    words.foldLeft(0)((acc, word) => acc + word.length)
  }
  
  def nbrOfS(words: List[String]): Int = {
    words.foldLeft(0)((acc, word) => acc + word.count(_ == 's'))
  }
  
  def nbrOfChar(c: Char)(words: List[String]): Int = {
    words.foldLeft(0)((acc, word) => acc + word.count(_ == c))
  }// version currifiée
  
  def maxScore(scoring: String => Int, words: List[String]): Int = {
    words.map(scoring).max
  }
  
  @main
  def main(): Unit = {
    val words: List[String] = List("ada", "haskell", "scala", "java", "rust")
    println(highScoringWords(w => score(w) + bonus(w) - malus(w), words))
    println(rankedWords(w => score(w) + bonus(w) - malus(w), words))
    println(wordScores(w => score(w) + bonus(w) - malus(w), words))
    println(lenWords(words))
    println(moreThanOneL(words))
    println(curry(add)(3)(4))
    println(uncurry(sub)(3, 4))
    println(cumulativeScore(w => score(w) + bonus(w) - malus(w), words))
    println(totalLength(words))
    println(nbrOfS(words))
    println(maxScore(w => score(w) + bonus(w) - malus(w), words))
    println(nbrOfChar('a')(words))
  }
  
}



