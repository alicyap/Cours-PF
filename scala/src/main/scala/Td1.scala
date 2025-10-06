import _root_.java.time.LocalDateTime
import _root_.java.time.format.DateTimeFormatter

object Td1 {
  case class Information(message: String, country: String, timestamp: LocalDateTime, tags: List[String], price: Int)

  def parseInformation(line: String): Information = {
    val parts = line.split("--")
    val timestamp = parts(0).trim
    val country = parts(1).trim
    val message = parts(2).trim
    val tags = parts(3).split(",").map(_.trim).toList
    val price = parts(4).split("€")(0).trim.toInt
    Information(message, country, LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME), tags, price)
  }

  def informationTimestamps(infos: List[Information]): List[LocalDateTime] =
    infos.map(_.timestamp)

  def informationTag0(infos: List[Information], tag: String): List[Information] =
    infos.filter(_.tags.contains(tag))

  // version currifiée
  def informationTag(tag: String)(infos: List[Information]): List[Information] =
    infos.filter(_.tags.contains(tag))

  def informationTagOneOf(infos: List[Information], tags: List[String]): List[Information] = {
    infos.filter(info => info.tags.exists(tags.contains))
    //infos.filter(_.tags.intersect(tags).nonEmpty)
  }
  def informationMessageSuchThat(infos: List[Information], cond: String => Boolean): List[Information] =
    infos.filter(info => cond(info.message))

  def informationCountry(infos: List[Information], country: String): List[Information] =
    infos.filter(_.country == country)

  def selection[T](field: Information => T, cond: T => Boolean, infos: List[Information]): List[Information] =
    infos.filter(info => cond(field(info)))

  val messageSelecteur: (String => Boolean, List[Information]) => List[Information] =
    (cond, infos) => selection(_.message, cond, infos)

  val tagsSelecteur: (List[String] => Boolean, List[Information]) => List[Information] =
    (cond, infos) => selection(_.tags, cond, infos)

  val countrySelecteur: (String => Boolean, List[Information]) => List[Information] =
    (cond, infos) => selection(_.country, cond, infos)

  val timestampSelecteur: (LocalDateTime => Boolean, List[Information]) => List[Information] =
    (cond, infos) => selection(_.timestamp, cond, infos)

  @main
  def test(): Unit = {
    val rawInformation = List(
      "2017-05-08T14:39:06 -- France -- This is an information -- tag1 -- 2€",
      "2017-05-08T14:49:06 -- UK -- This is another information -- tag1,tag2 -- 4€",
      "2018-05-10T14:39:06 -- France -- This is a newer information -- tag3 -- 8€"
    )
    val information = rawInformation.map(parseInformation(_))
    information.foreach(println)

    // 1. Liste des timestamps
    println("Timestamps :")
    println(informationTimestamps(information))

    // 2. Informations contenant le mot "newer"
    println("\nInformations contenant 'newer' :")
    println(informationMessageSuchThat(information, _.contains("newer")))

    // 3. Informations commençant par "This"
    println("\nInformations commençant par 'This' :")
    println(informationMessageSuchThat(information, _.startsWith("This")))

    // 4a. Informations avec le tag0 "tag1"
    println("\nInformations avec le tag 'tag1' :")
    println(informationTag0(information, "tag1"))

    // 4b. Informations avec le tag "tag1"
    println("\nInformations avec le tag 'tag1' :")
    println(informationTag("tag1")(information))


    // 5. Informations avec le tag "tag2" ou "tag3"
    println("\nInformations avec le tag 'tag2' ou 'tag3' :")
    println(informationTagOneOf(information, List("tag2", "tag3")))

    // 6. Prix total des informations concernant la France
    val prixFrance = informationCountry(information, "France").map(_.price).sum
    println(s"\nPrix total des informations concernant la France : $prixFrance €")

    // 7a. Informations avec un prix supérieur à 3€
    println("\nInformations avec un prix supérieur à 3€ :")
    println(selection(_.price, (p: Int) => p > 3, information))

    // 8. Informations avec un message qui se termine par "information"
    println("\nInformations avec un message qui se termine par 'information' :")
    println(messageSelecteur((m: String) => m.endsWith("information"), information))  }

}
