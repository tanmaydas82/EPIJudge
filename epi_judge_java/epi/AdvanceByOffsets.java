package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class AdvanceByOffsets {
  @EpiTest(testDataFile = "advance_by_offsets.tsv")
  public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {
    int maxReach=0, i=0;

    while (i <= maxReach) {
        maxReach = Math.max(maxReach, i+maxAdvanceSteps.get(i));

        if(maxReach >= maxAdvanceSteps.size()-1) {
            return true;
        }

        i++;
    }
    return maxReach >= maxAdvanceSteps.size()-1;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "AdvanceByOffsets.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
