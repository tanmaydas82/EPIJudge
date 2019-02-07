package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.serialization_traits.IntegerTraits;

import java.util.Arrays;
import java.util.List;
public class BstFromPreorder {

    private static int inxPos=0;

  @EpiTest(testDataFile = "bst_from_preorder.tsv")

  public static BstNode<Integer>
  rebuildBSTFromPreorder(List<Integer> preorderSequence) {
      inxPos=0;
      return rebuildBSTFromPreorder_helper(preorderSequence, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

    public static BstNode<Integer>
    rebuildBSTFromPreorder_helper(List<Integer> preorderSequence, int minValue, int maxValue) {

        if(inxPos >= preorderSequence.size()) return null;

       int val = preorderSequence.get(inxPos);

       if( val < minValue || val > maxValue) {
           return null;
       }

       BstNode<Integer> curNode =  new BstNode<>(val);
       inxPos++;
       curNode.left = rebuildBSTFromPreorder_helper(preorderSequence, minValue, val);
       curNode.right = rebuildBSTFromPreorder_helper(preorderSequence, val, maxValue);
       return curNode;
    }

//  public static BstNode<Integer>
//  rebuildBSTFromPreorder(List<Integer> preorderSequence) {
//
//    return rebuildBSTFromPreorder_helper(preorderSequence, 0, preorderSequence.size()-1);
//  }
//
//    private static BstNode<Integer>
//    rebuildBSTFromPreorder_helper(List<Integer> preorderSequence, int start, int end) {
//
//        if(start > end) {
//            return null;
//        }
//
//        BstNode<Integer> curNode = new BstNode<>(preorderSequence.get(start));
//        int lastIdx = findLast(preorderSequence, start);
//        curNode.left = rebuildBSTFromPreorder_helper(preorderSequence, start+1, lastIdx);
//        curNode.right = rebuildBSTFromPreorder_helper(preorderSequence, lastIdx+1, end);
//        return curNode;
//    }
//
//    private static int findLast(List<Integer> preorderSequence, int start) {
//
//      if (start >= preorderSequence.size()) return start;
//
//      int end=start+1;
//      while(end < preorderSequence.size() && preorderSequence.get(end) < preorderSequence.get(start)) {
//          end++;
//      }
//      return --end;
//    }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromPreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }

}
