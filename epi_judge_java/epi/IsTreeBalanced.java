package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
      return isBalanced_helper(tree).isBalanced;
  }

  private static NodeResult isBalanced_helper(BinaryTreeNode<Integer> tree) {
      if(tree == null) {
          return new NodeResult(0, true);
      }

      NodeResult left = isBalanced_helper(tree.left);

      if (!left.isBalanced) {
          return left;
      }

      NodeResult right = isBalanced_helper(tree.right);

      if (!right.isBalanced) {
          return right;
      }

      return new NodeResult(Math.max(left.height, right.height) + 1, Math.abs(left.height-right.height) <= 1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

class NodeResult {
    int height;
    boolean isBalanced;

    NodeResult(int h, boolean b) {
        height = h;
        isBalanced = b;
    }
}
