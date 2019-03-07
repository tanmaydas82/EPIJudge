package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {

      if (tree == null) return new ArrayList<>();
      List<Integer> inorder = new ArrayList<>();
      BinaryTree<Integer> iter = tree;

      while (iter.left != null) {
          iter = iter.left;
      }
      inorder.add(iter.data);

      while ((iter = findSuccessor(iter)) != null) {
          inorder.add(iter.data);
      }
      return inorder;
  }

  private static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {

      if (node == null) {
          return null;
      }

      if (node.right != null) {
          node = node.right;

          while (node.left != null) {
              node = node.left;
          }

          return node;
      }

      while (node.parent != null &&  node.parent.left != node) {
          node = node.parent;
      }

      return node.parent;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
