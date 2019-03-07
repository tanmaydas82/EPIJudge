package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorWithParent {

    public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                          BinaryTree<Integer> node1) {
        int node0h = findHeight(node0);
        int node1h = findHeight(node1);

        int diff = Math.abs(node0h-node1h);

        BinaryTree<Integer> deepNode = node0h > node1h ? node0 : node1;
        BinaryTree<Integer> shallowNode = node0h > node1h ? node1 : node0;
        int step=0;

        while (step < diff) {
            deepNode = deepNode.parent;
            step++;
        }

        while (deepNode != shallowNode) {
            deepNode = deepNode.parent;
            shallowNode = shallowNode.parent;
        }

        return deepNode;
    }


    private static int findHeight(BinaryTree<Integer> node) {
        int height=0;
        while (node.parent != null) {
            height++;
            node = node.parent;
        }
        return height;
    }
//  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
//                                        BinaryTree<Integer> node1) {
//    List<BinaryTree<Integer>> firstNodePath = new ArrayList<>();
//    List<BinaryTree<Integer>> secondNodePath = new ArrayList<>();
//
//    while (node0 != null) {
//        firstNodePath.add(0, node0);
//        node0 = node0.parent;
//    }
//
//    while (node1 != null) {
//        secondNodePath.add(0, node1);
//        node1 = node1.parent;
//    }
//
//    BinaryTree<Integer> ancestor = null;
//    int count=0;
//
//    while (count < firstNodePath.size() && count < secondNodePath.size() && firstNodePath.get(count) == secondNodePath.get(count)) {
//        ancestor = firstNodePath.get(count);
//        count++;
//    }
//
//    return ancestor;
//  }
  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
