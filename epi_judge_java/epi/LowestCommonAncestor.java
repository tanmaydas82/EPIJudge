package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestor {
  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {


      return findNode(tree, node0, node1).node;
  }

  private static TreeNodeResult findNode(BinaryTreeNode<Integer> tree,
                              BinaryTreeNode<Integer> node0,
                              BinaryTreeNode<Integer> node1) {


      if (tree == null) {
          return new TreeNodeResult(null, 0);
      }

      TreeNodeResult leftResult = findNode(tree.left, node0, node1);

      if (leftResult.count == 2) {
          return leftResult;
      }

      TreeNodeResult rightResult = findNode(tree.right, node0, node1);

      if (rightResult.count == 2) {
          return rightResult;
      }

      int total = leftResult.count + rightResult.count + (tree == node0 ? 1 : 0 ) +  (tree == node1 ? 1 : 0);
      return new TreeNodeResult(total == 2 ? tree : null, total);
  }


  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}


class TreeNodeResult {
    BinaryTreeNode<Integer> node;
    int count;

    TreeNodeResult(BinaryTreeNode<Integer> tree, int c) {
        node = tree;
        count = c;
    }
}