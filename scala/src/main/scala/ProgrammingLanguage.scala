case class ProgrammingLanguage(name: String, year: Int) // type produit
val java = ProgrammingLanguage("Java", 1995)
val scala = ProgrammingLanguage("Scala", 2004)
val languages = List(java, scala)

def langNames(languages: List[ProgrammingLanguage]): List[String] = {
  languages.map(lang => lang.name)
}

def langAfter2000(languages: List[ProgrammingLanguage]): List[ProgrammingLanguage] = {
  languages.filter(lang => lang.year > 2000)
}

//@main
def main(): Unit = {
  println(langNames(languages)) // List("Java", "Scala")
  println(langAfter2000(languages)) // List(ProgrammingLanguage("Scala", 2004))
}