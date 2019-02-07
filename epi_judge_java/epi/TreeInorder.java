package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeInorder {
  @EpiTest(testDataFile = "tree_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
      Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
      BinaryTreeNode<Integer> iter = tree;

      List<Integer> result = new ArrayList<>();

      while(iter != null || !stack.isEmpty()) {

          while(iter.left != null)  {
              stack.push(iter);
              iter = iter.left;
          }

          while(!stack.isEmpty() && iter.right == null) {
              result.add(iter.data);
              iter = stack.pop();
          }

          result.add(iter.data);
          iter = iter.right;
      }

      return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
