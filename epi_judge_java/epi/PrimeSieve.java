package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {

        if (n <= 0) {
            return new ArrayList<>();
        }

        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        List<Integer> result = new ArrayList<>();

        for(int i=2; i <= n; i++) {
            if (isPrime[i]) {
                result.add(i);

                for(int j=2; j*i <= n; j++) {
                    isPrime[j*i] = false;
                }
            }
        }
        return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
