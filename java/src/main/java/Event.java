package ov_chipcard;

import lombok.Value;


/* Sample events. Replace with your own.
 *
 * If you use classes that extend Event and annotated with @Value,
 * (de)serialization will work for them automatically.
 */
public interface Event {

  /** For SomethingHappened("Something good") serialized form is
    *
    * {
    *   "SomethingHappened" : {
    *     "what" : "Something good"
    *   }
    * }
    */
  @Value public static class SomethingHappened implements Event {
    String what;
  }

  /** For ErIsIetsGebeurd("Something good", "nu") serialized form is
    *
    * {
    *   "ErIsIetsGebeurd" : {
    *     "wat" : "iets goed",
    *     "wanneer" : "nu"
    *   }
    * }
    */
  @Value public static class ErIsIetsGebeurd implements Event {
    String wat;
    String wanneer;
  }
}

