package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class ApplyPermutation {
  public static void applyPermutation(List<Integer> perm, List<Integer> A) {
    int curPos, nextPos, curElement, nextElement;

    for(int i=0; i < A.size(); i++) {
        curPos=i; curElement=A.get(curPos);

        while (perm.get(curPos) >= 0 && perm.get(curPos) != curPos) {
            nextPos = perm.get(curPos);
            nextElement = A.get(nextPos);
            A.set(nextPos, curElement);
            perm.set(curPos, 0-A.size());
            curPos = nextPos;
            curElement = nextElement;
        }
    }
  }
  @EpiTest(testDataFile = "apply_permutation.tsv")
  public static List<Integer> applyPermutationWrapper(List<Integer> perm,
                                                      List<Integer> A) {
    applyPermutation(perm, A);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ApplyPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
