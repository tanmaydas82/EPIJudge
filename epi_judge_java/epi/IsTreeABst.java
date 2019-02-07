package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

      public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {

        return isBinaryTreeBST_helper(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
      }

    private static boolean isBinaryTreeBST_helper(BinaryTreeNode<Integer> tree, Integer minValue, Integer maxValue) {

        if(tree == null) return true;

        return tree.data >= minValue && tree.data <= maxValue
                && isBinaryTreeBST_helper(tree.left, minValue, tree.data)
                && isBinaryTreeBST_helper(tree.right, tree.data, maxValue);
    }


    public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
