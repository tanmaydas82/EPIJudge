package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TestUtils;
import epi.test_framework.TimedExecutor;
import java.util.List;
public class BstFromSortedArray {

    public static BstNode<Integer>
    buildMinHeightBSTFromSortedArray(List<Integer> A) {
      return buildMinHeightBSTFromSortedArray_helper(A, 0, A.size()-1);
    }

    private static BstNode<Integer>
    buildMinHeightBSTFromSortedArray_helper(List<Integer> A, int start, int end) {
        if(start > end) return null;
        int mid = start + ((end-start)/2);
        BstNode<Integer> root = new BstNode<>(A.get(mid));
        root.left = buildMinHeightBSTFromSortedArray_helper(A, start, mid-1);
        root.right = buildMinHeightBSTFromSortedArray_helper(A, mid+1, end);
        return root;
    }

  @EpiTest(testDataFile = "bst_from_sorted_array.tsv")
  public static int
  buildMinHeightBSTFromSortedArrayWrapper(TimedExecutor executor,
                                          List<Integer> A) throws Exception {
    BstNode<Integer> result =
        executor.run(() -> buildMinHeightBSTFromSortedArray(A));

    List<Integer> inorder = BinaryTreeUtils.generateInorder(result);

    TestUtils.assertAllValuesPresent(A, inorder);
    BinaryTreeUtils.assertTreeIsBst(result);
    return BinaryTreeUtils.binaryTreeHeight(result);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstFromSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
