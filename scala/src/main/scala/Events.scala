package ov_chipcard


sealed trait Event

/* Sample events. Replace with your own.
 *
 * Use case classes that extend Event and (de)serializers
 * will be derived for them automatically.
 */

/** For SomethingHappened("Something good") serialized form is
  *
  * {
  *   "SomethingHappened" : {
  *     "what" : "Something good"
  *   }
  * }
  */
case class SomethingHappened(what: String) extends Event

/** For ErIsIetsGebeurd("Something good", "nu") serialized form is
  *
  * {
  *   "ErIsIetsGebeurd" : {
  *     "wat" : "goed",
  *     "wanneer" : "nu"
  *   }
  * }
  */
case class ErIsIetsGebeurd(wat: String, wanneer: String) extends Event
