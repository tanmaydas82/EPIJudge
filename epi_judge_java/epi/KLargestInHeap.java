package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;
public class KLargestInHeap {
  @EpiTest(testDataFile = "k_largest_in_heap.tsv")

  public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
      List<Integer> kLargest = new ArrayList<>();
      PriorityQueue<Element> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
      maxHeap.add(new Element(0, A.get(0)));

      while (kLargest.size() < k) {
          Element e = maxHeap.poll();
          kLargest.add(0, e.val);
          if( e.pos*2+1 < A.size()) maxHeap.add(new Element(e.pos*2+1, A.get(e.pos*2+1)));
          if( e.pos*2+2 < A.size()) maxHeap.add(new Element(e.pos*2+2, A.get(e.pos*2+2)));
      }

      return kLargest;
  }

  static class Element implements Comparable<Element> {

      int pos;
      int val;


      public Element(int pos, int val) {
          this.pos = pos;
          this.val = val;
      }

      @Override
      public int compareTo(Element o) {
          return val-o.val;
      }
  }
  @EpiTestComparator
  public static BiPredicate<List<Integer>, List<Integer>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    Collections.sort(expected);
    Collections.sort(result);
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestInHeap.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
