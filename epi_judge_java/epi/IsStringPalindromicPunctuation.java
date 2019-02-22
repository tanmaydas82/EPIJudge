package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsStringPalindromicPunctuation {
  @EpiTest(testDataFile = "is_string_palindromic_punctuation.tsv")

  public static boolean isPalindrome(String s) {

      s = s.toLowerCase();

      for(int start=0, end=s.length()-1; start < end; ) {

          char begChar = s.charAt(start);
          char endChar = s.charAt(end);

          if (!(Character.isAlphabetic(begChar) || Character.isDigit(begChar))) {
            start++;
            continue;
          }

          if (!(Character.isAlphabetic(endChar) || Character.isDigit(endChar))) {
              end--;
              continue;
          }

          if(begChar != endChar) return false;
          start++;
          end--;
      }

      return true;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsStringPalindromicPunctuation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
