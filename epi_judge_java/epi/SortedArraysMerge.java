package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {
  @EpiTest(testDataFile = "sorted_arrays_merge.tsv")

  public static List<Integer>
  mergeSortedArrays(List<List<Integer>> sortedArrays) {

      List<Integer> result = new  ArrayList<>();
      PriorityQueue<ArrayElement> minHeap = new PriorityQueue<>();

      for(int i=0; i < sortedArrays.size(); i++) {
          minHeap.add(new ArrayElement(i, 0, sortedArrays.get(i).get(0)));
      }

      while(!minHeap.isEmpty()) {
          ArrayElement e = minHeap.poll();
          result.add(e.elementValue);

          int nextElementPos = e.elementPos+1;
          if (nextElementPos < sortedArrays.get(e.arrayNumber).size()) {
              minHeap.add(new ArrayElement(e.arrayNumber, nextElementPos, sortedArrays.get(e.arrayNumber).get(nextElementPos)));
          }
      }

      return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}

class ArrayElement implements Comparable<ArrayElement>{

    int arrayNumber;
    int elementPos;
    int elementValue;

    public ArrayElement(int arrayNumber, int elementPos, int elementValue) {
        this.arrayNumber = arrayNumber;
        this.elementPos = elementPos;
        this.elementValue = elementValue;
    }

    public int compareTo(ArrayElement other) {
        return this.elementValue - other.elementValue;
    }
}
