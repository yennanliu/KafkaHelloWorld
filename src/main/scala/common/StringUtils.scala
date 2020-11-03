package common

object StringUtils {
  /**
   * Replace string with specific pattern to it replacement for ingesting stream data
   */
  implicit class EscapeSpecialCharacters(s: String) {
    def escapeReserveChar: String = {
      s
        .replaceAll("_", "\\\\_")
        .replaceAll("%", "\\\\%")
    }

    def stripSpace: String = {
      s
        .replaceAll(" ", "")
        .replaceAll("  ", "")
        .replaceAll(" \n", "")
    }
  }
}
