package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class ReverseSublist {
  @EpiTest(testDataFile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {

        ListNode<Integer> iter = new ListNode<>(0, L);
        ListNode<Integer> prev = iter;

        for(int i=0; i < start; i++) {
            prev = iter;
            iter = iter.next;
        }
        
        ListNode<Integer> beforeStartNode = prev;
        ListNode<Integer> firstNode = iter;

        for(int i=start; i <= finish; i++) {
            ListNode<Integer> nextNode = iter.next;
            iter.next = prev;
            prev = iter;
            iter = nextNode;
        }

        firstNode.next = iter;
        beforeStartNode.next = prev;

        return start == 1 ? beforeStartNode.next : L;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseSublist.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
