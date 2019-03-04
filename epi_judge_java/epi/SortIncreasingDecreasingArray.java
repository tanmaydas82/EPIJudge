package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class SortIncreasingDecreasingArray {
  @EpiTest(testDataFile = "sort_increasing_decreasing_array.tsv")

  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {

      Slope slope=Slope.NONE;
      int start=0; int end=0;

      List<SlopeIndicator> partitions = new ArrayList<>();

      for (int i=0; i < A.size()-1; i++) {

        if (A.get(i) < A.get(i+1)) {
            if(slope == Slope.DECREASING) {
                partitions.add(new SlopeIndicator(start, i, A.get(start), slope));
                start = i+1;
            }
            slope = Slope.INCREASING;
        } else {
            if(slope == Slope.INCREASING) {
                partitions.add(new SlopeIndicator(start, i, A.get(start), slope));
                start = i+1;
            }
            slope = Slope.DECREASING;
        }

      }

      partitions.add(new SlopeIndicator(start, A.size()-1, A.get(start), slope));

      for (SlopeIndicator indicator : partitions) {
          if (indicator.slope == Slope.DECREASING) {
              Collections.sort(A.subList(indicator.start, indicator.end+1));
          }
      }

      PriorityQueue<SlopeIndicator> minHeap = new PriorityQueue<>();

      for(int i=0; i < partitions.size(); i++) {
          SlopeIndicator slopeIndicator = partitions.get(i);
          slopeIndicator.value = A.get(slopeIndicator.start);
          minHeap.add(slopeIndicator);
          slopeIndicator.start++;
      }

      List<Integer> result = new ArrayList<>();


      while (!minHeap.isEmpty()) {
          SlopeIndicator indicator = minHeap.poll();
          result.add(indicator.value);

          if(indicator.start <= indicator.end) {
              indicator.value = A.get(indicator.start);
              indicator.start++;
              minHeap.add(indicator);
          }
      }

      return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortIncreasingDecreasingArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

class SlopeIndicator implements Comparable<SlopeIndicator>{
    int start;
    int end;
    int value;
    Slope slope;


    public SlopeIndicator(int start, int end, int value, Slope slope) {
        this.start = start;
        this.end = end;
        this.value = value;
        this.slope = slope;
    }

    @Override
    public int compareTo(SlopeIndicator other) {
        return this.value - other.value;
    }
}

enum Slope {
    INCREASING, DECREASING, NONE;
}
