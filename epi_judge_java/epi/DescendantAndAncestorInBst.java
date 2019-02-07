package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class DescendantAndAncestorInBst {

  public static boolean
  pairIncludesAncestorAndDescendantOfM(BstNode<Integer> possibleAncOrDesc0,
                                       BstNode<Integer> possibleAncOrDesc1,
                                       BstNode<Integer> middle) {

      if(possibleAncOrDesc0 == middle || possibleAncOrDesc1 == middle) return false;

      BstNode<Integer> first = possibleAncOrDesc0;
      BstNode<Integer> second = possibleAncOrDesc1;

      while( (first != null && first != middle) || (second != null && second != middle) ) {

          if(first != null) {
              if (first.data > middle.data) {
                  first = first.left;
              } else {
                  first = first.right;
              }
          }

          if(second != null) {
              if (second.data > middle.data) {
                  second = second.left;
              } else {
                  second = second.right;
              }
          }
      }

      if(first == null && second == null) {
          return false;
      }

      if(first == middle) {

          second = possibleAncOrDesc1;

          while (middle != null && middle != second) {
              if(middle.data > second.data) {
                  middle = middle.left;
              } else {
                  middle =  middle.right;
              }
          }

          if(middle == null) {
              return false;
          }

      } else if(second == middle) {

          first = possibleAncOrDesc0;

          while (middle != null && middle != first) {
              if(middle.data > first.data) {
                  middle = middle.left;
              } else {
                  middle =  middle.right;
              }
          }

          if(middle == null) {
              return false;
          }
      }

      return true;
  }
  @EpiTest(testDataFile = "descendant_and_ancestor_in_bst.tsv")
  public static boolean pairIncludesAncestorAndDescendantOfMWrapper(
      TimedExecutor executor, BstNode<Integer> tree, int possibleAncOrDesc0,
      int possibleAncOrDesc1, int middle) throws Exception {
    final BstNode<Integer> candidate0 =
        BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc0);
    final BstNode<Integer> candidate1 =
        BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc1);
    final BstNode<Integer> middleNode =
        BinaryTreeUtils.mustFindNode(tree, middle);

    return executor.run(()
                            -> pairIncludesAncestorAndDescendantOfM(
                                candidate0, candidate1, middleNode));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DescendantAndAncestorInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
