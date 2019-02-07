package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {

    if(L1 == null) return L2;
    if(L2 == null) return L1;

    ListNode<Integer> first = L1;
    ListNode<Integer> second = L2;

    ListNode<Integer> head = new ListNode<>(0, L1.data < L2.data ? L1 : L2);

    ListNode<Integer> iter = new ListNode<>(0, null);

    while (first != null && second != null) {

        if(first.data < second.data) {
            iter.next = first;
            first = first.next;
        } else {
            iter.next = second;
            second = second.next;
        }
        iter = iter.next;
    }

    iter.next = first == null ? second : first;

    return head.next;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
