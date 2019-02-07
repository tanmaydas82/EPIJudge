package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    Double[] buyProfit = new Double[prices.size()];
    Double[] sellProfit = new Double[prices.size()];

    double maxProfit=0.0, minPrice=Double.MAX_VALUE, maxPrice=Double.MIN_VALUE;

    for(int i=0; i < prices.size(); i++) {
        minPrice = Math.min(minPrice, prices.get(i));
        maxProfit = Math.max(maxProfit, prices.get(i)-minPrice);
        sellProfit[i] = maxProfit;
    }

    maxProfit = 0.0;
    for(int i=prices.size()-1; i >= 0; i--) {
        maxPrice = Math.max(maxPrice, prices.get(i));
        maxProfit = Math.max(maxProfit, maxPrice-prices.get(i));
        buyProfit[i] = maxProfit;
    }

    for(int i=1; i < prices.size(); i++) {
        maxProfit = Math.max(maxProfit, buyProfit[i] + sellProfit[i-1]);
    }

    return maxProfit;
  }

  public static void main(String[] args) {

//      buyAndSellStockTwice(Arrays.asList(0.1, 0.2));
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
