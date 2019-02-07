package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {

      int start=A.size()-1, sum=0, carry=1;

      while (carry != 0 && start >= 0) {
          sum = A.get(start) + carry;
          A.set(start, sum%10);
          carry = sum/10;
          --start;
      }

      if (carry != 0) {
          A.add(0, carry);
      }

      return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
