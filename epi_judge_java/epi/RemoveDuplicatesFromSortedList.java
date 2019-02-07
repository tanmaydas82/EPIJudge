package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class RemoveDuplicatesFromSortedList {
  @EpiTest(testDataFile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {

      if(L == null) {
          return null;
      }

      ListNode<Integer> cur = L;
      ListNode<Integer> front = L.next;

      while(front != null) {
          if(cur.data != front.data) {
              cur.next = front;
              cur = front;
          }

          front = front.next;
      }

      cur.next = null;

      return L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "RemoveDuplicatesFromSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
