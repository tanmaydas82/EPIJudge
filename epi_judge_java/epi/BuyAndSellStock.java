package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    Double maxProfit= Double.MIN_VALUE;
    Double cost = Double.MAX_VALUE;

    for(int i=0; i < prices.size(); i++) {
        cost = Math.min(cost, prices.get(i));
        maxProfit = Math.max(maxProfit, prices.get(i)-cost);
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
