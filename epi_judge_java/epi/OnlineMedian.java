package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class OnlineMedian {
  public static List<Double> onlineMedian(Iterator<Integer> sequence) {

      List<Double> medians = new ArrayList<>();
      PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
      PriorityQueue<Integer> minHeap = new PriorityQueue<>();
      int count=0;

      while (sequence.hasNext()) {
          count++;
          Integer curElement = sequence.next();

          if( maxHeap.isEmpty() || maxHeap.peek() > curElement ) {
              maxHeap.add(curElement);
          } else {
              minHeap.add(curElement);
          }

          if(maxHeap.size() > minHeap.size()+1) {
              minHeap.add(maxHeap.poll());
          } else if(minHeap.size() > maxHeap.size()) {
              maxHeap.add(minHeap.poll());
          }

          if ((count&1) == 1) {
              medians.add(maxHeap.peek() * 1.0);
          } else {
              medians.add((maxHeap.peek() + minHeap.peek()) / 2.0);
          }

      }

      return medians;
  }
  @EpiTest(testDataFile = "online_median.tsv")
  public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
    return onlineMedian(sequence.iterator());
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "OnlineMedian.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
