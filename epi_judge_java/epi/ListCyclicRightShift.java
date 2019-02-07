package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ListCyclicRightShift {
  @EpiTest(testDataFile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(ListNode<Integer> L,
                                                           int k) {

      if(L == null || k ==0) return L;
    int len = findLength(L);

    k=k%len;

    if(k==0) {
        return L;
    }

    k = len-k;
    ListNode<Integer> iter = L;

    while(k > 1) {
        iter = iter.next;
        k--;
    }

    ListNode<Integer> newHead = iter.next;

    iter.next = null;
    iter = newHead;

    while(iter.next != null) {
        iter = iter.next;
    }

    iter.next = L;
    return newHead;
  }

  private static int findLength(ListNode<Integer> l) {
      int len=0;

      while (l != null) {
          len++;
          l = l.next;
      }

      return len;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ListCyclicRightShift.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
