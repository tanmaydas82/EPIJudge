package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SpreadsheetEncoding {
  @EpiTest(testDataFile = "spreadsheet_encoding.tsv")

  public static int ssDecodeColID(final String col) {

      int value = 0;

      for(int i=0; i < col.length(); i++) {
          value = value * 26 + (col.charAt(i) - 'A' + 1);
      }

      calculateColId(col, value);
      return value;
  }

  private static void calculateColId(String col, int value) {
      int x = value;
      StringBuffer sb = new StringBuffer();

      do {
          --value;
          int digit = value%26;
          sb.append((char)('A'+digit));
          value /= 26;
      } while(value > 0);

      System.out.println("\n" + col + "-" + x + "-" + sb.reverse().toString() + "\n");
  }

  public static void main(String[] args) {
//      ssDecodeColID("ZZ");
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SpreadsheetEncoding.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
