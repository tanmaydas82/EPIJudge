package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class ReverseWords {

  public static void reverseWords(char[] input) {
      reverse(input, 0, input.length-1);

      int first=0;

      for(int pos=0; pos < input.length; pos++) {

          if(input[pos] == ' ') {
              reverse(input, first, pos-1);
              first = pos+1;
          }
      }

      reverse(input, first, input.length-1);

      return;
  }

  private static void reverse(char[] input, int first, int last) {

      if (first >= last) return;

      for(int beg=first, end=last; beg < end; beg++, end--) {
          char t = input[beg];
          input[beg] = input[end];
          input[end] = t;
      }
  }

  @EpiTest(testDataFile = "reverse_words.tsv")
  public static String reverseWordsWrapper(TimedExecutor executor, String s)
      throws Exception {
    char[] sCopy = s.toCharArray();

    executor.run(() -> reverseWords(sCopy));

    return String.valueOf(sCopy);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
