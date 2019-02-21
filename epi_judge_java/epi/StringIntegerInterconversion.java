package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
public class StringIntegerInterconversion {

  public static String intToString(int x) {

      StringBuffer sb = new StringBuffer();
      boolean isNegative = x < 0;

      do {
          sb.append((char)('0' + Math.abs(x%10)));
          x = x/10;
      } while(x != 0);
      return (isNegative ? "-" : "") + sb.reverse().toString();
  }

  public static int stringToInt(String s) {
      boolean isNegative = s.charAt(0) == '-';
      int val=0;

      for(int i=(isNegative?1:0); i < s.length(); i++) {
        val = val * 10 + (s.charAt(i)-'0');
      }

    return isNegative ? 0-val : val;
  }
  @EpiTest(testDataFile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailure {
    if (!intToString(x).equals(s)) {
      throw new TestFailure("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailure("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "StringIntegerInterconversion.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
