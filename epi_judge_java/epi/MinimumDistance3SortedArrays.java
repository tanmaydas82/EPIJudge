package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumDistance3SortedArrays {

  public static class ArrayData implements Comparable<ArrayData> {
    public int val;
    public int insideArrIdx;
    public int idx;

    public ArrayData(int idx, int insideArrIdx, int val) {
      this.val = val;
      this.insideArrIdx = insideArrIdx;
      this.idx = idx;
    }

    @Override
    public int compareTo(ArrayData o) {
      int result = Integer.compare(val, o.val);
      if (result == 0) {
        result = Integer.compare(idx, o.idx);
      }
      return result;
    }
  }

  @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")

  public static int
  findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {

      PriorityQueue<ArrayData> minHeap = new PriorityQueue<>();
      PriorityQueue<ArrayData> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

      for(int i=0; i < sortedArrays.size(); i++) {
          minHeap.add(new ArrayData(i, 0, sortedArrays.get(i).get(0)));
          maxHeap.add(new ArrayData(i, 0, sortedArrays.get(i).get(0)));
      }

      int diff = Integer.MAX_VALUE;

      while (true) {

          ArrayData minData = minHeap.poll();
          ArrayData maxData = maxHeap.peek();

          diff = Math.min(diff, maxData.val - minData.val);

          minData.insideArrIdx++;

          if (minData.insideArrIdx >= sortedArrays.get(minData.idx).size()) {
              break;
          }

          ArrayData nextEntry = new ArrayData(minData.idx, minData.insideArrIdx, sortedArrays.get(minData.idx).get(minData.insideArrIdx));
          if(nextEntry.val > maxData.val) {
              maxHeap.poll();
              maxHeap.add(nextEntry);
          }
          minHeap.add(nextEntry);
      }
      return diff;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumDistance3SortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
