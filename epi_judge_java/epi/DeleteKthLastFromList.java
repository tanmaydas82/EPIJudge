package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class DeleteKthLastFromList {
  @EpiTest(testDataFile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {

      ListNode<Integer> dummyHead = new ListNode<>(0, L);
      ListNode<Integer> forward = dummyHead.next;
      ListNode<Integer> follower = dummyHead.next;

      while(k > 0) {
          forward = forward.next;
          k--;
      }

      if(forward == null) {
          dummyHead.next = dummyHead.next.next;
          return dummyHead.next;
      }

      while(forward.next != null) {
          forward = forward.next;
          follower = follower.next;
      }

      follower.next = follower.next.next;

      return dummyHead.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "DeleteKthLastFromList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
