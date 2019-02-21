package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ConvertBase {
  @EpiTest(testDataFile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {

      boolean isNegative = numAsString.charAt(0) == '-';
      int decimalValue = convertToDecimal(numAsString, b1, isNegative);
      StringBuffer sb = new StringBuffer();

      do {
          int reminder = decimalValue % b2;
          char digit = (char) (reminder >=0 && reminder <= 9 ? '0' + reminder : 'A' + reminder - 10);
          sb.append(digit);
          decimalValue = decimalValue/b2;

      } while(decimalValue != 0);

      return (isNegative ? "-" : "") + sb.reverse().toString();
  }

  private static int convertToDecimal(String num, int b1, boolean isNegative) {
      int endValue=0;
      int pos = isNegative ? 1 : 0;

      for(; pos < num.length(); pos++) {
          char posVal = num.charAt(pos);
          int value = ('0' <= posVal && '9' >= posVal) ? posVal - '0' : (10 + posVal -'A');
          endValue += value * Math.pow(b1, num.length()-pos-1);
      }

      return endValue;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ConvertBase.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
