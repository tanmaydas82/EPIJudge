package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class NextPermutation {
  @EpiTest(testDataFile = "next_permutation.tsv")
  public static List<Integer> nextPermutation(List<Integer> perm) {
    int breakPoint=perm.size()-1;

    while (breakPoint > 0 && perm.get(breakPoint) <= perm.get(breakPoint-1)) {
        breakPoint--;
    }

    if (breakPoint == 0) {
        return new ArrayList<>();
    }

    int pivot=breakPoint-1;
    breakPoint=perm.size()-1;

    while(perm.get(breakPoint) <= perm.get(pivot)) {
        breakPoint--;
    }

    Collections.swap(perm, pivot, breakPoint);
    Collections.sort(perm.subList(pivot+1, perm.size()));
    return perm;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NextPermutation.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
