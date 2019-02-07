package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
public class KLargestValuesInBst {
  @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")

  public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {

        List<Integer> res = new ArrayList<>();
        findKLargestInBst_helper(tree, res, k);
        return res;
  }
    public static void findKLargestInBst_helper(BstNode<Integer> tree, List<Integer> res, int k) {

        if(tree == null || res.size() == k) {
            return;
        }

        findKLargestInBst_helper(tree.right, res, k);

        if(res.size() < k) {
            res.add(tree.data);
            findKLargestInBst_helper(tree.left, res, k);
        }
    }

  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
