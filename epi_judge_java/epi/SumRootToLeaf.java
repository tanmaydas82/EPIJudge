package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SumRootToLeaf {
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
      if(tree == null) return 0;
      return sumRootToLeaf_helper(tree, 0);
  }

    public static int sumRootToLeaf_helper(BinaryTreeNode<Integer> tree, int sum) {
        if(tree == null) return 0;

        sum = (sum << 1) + tree.data;

        if (tree.left == null && tree.right == null) {
            return sum;
        }

        return sumRootToLeaf_helper(tree.left, sum) + sumRootToLeaf_helper(tree.right, sum);
    }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
