package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class PhoneNumberMnemonic {


  @EpiTest(testDataFile = "phone_number_mnemonic.tsv")


  public static List<String> phoneMnemonic(String phoneNumber) {
      List<String> result = new ArrayList<>();

      Map<Character, String> mapping = new HashMap<>();

      mapping.put('0', "0");
      mapping.put('1', "1");
      mapping.put('2', "ABC");
      mapping.put('3', "DEF");
      mapping.put('4', "GHI");
      mapping.put('5', "JKL");
      mapping.put('6', "MNO");
      mapping.put('7', "PQRS");
      mapping.put('8', "TUV");
      mapping.put('9', "WXYZ");

      phoneMnemonic_helper(phoneNumber, 0, result, new ArrayList<Character>(), mapping);
      return result;
  }

  private static void phoneMnemonic_helper(String phoneNumber, int pos, List<String> result, List<Character> mnemonic, Map<Character, String> mapping) {
      if(pos == phoneNumber.length()) {
          result.add(mnemonic.stream().map(Object::toString).collect(Collectors.joining()));
          return;
      }

      char[] alphas = mapping.get(phoneNumber.charAt(pos)).toCharArray();
      for(int i=0; i < alphas.length; i++) {
          char ch = alphas[i];
          mnemonic.add(ch);
          phoneMnemonic_helper(phoneNumber, pos+1, result, mnemonic, mapping);
          mnemonic.remove(mnemonic.size()-1);
      }
  }


//  public static List<String> phoneMnemonic(String phoneNumber) {
//    List<String> result = new ArrayList<>();
//
//    Map<Character, String> mapping = new HashMap<>();
//
//    mapping.put('2', "ABC");
//    mapping.put('3', "DEF");
//    mapping.put('4', "GHI");
//    mapping.put('5', "JKL");
//    mapping.put('6', "MNO");
//    mapping.put('7', "PQRS");
//    mapping.put('8', "TUV");
//    mapping.put('9', "WXYZ");
//
//    phoneMnemonic_helper(phoneNumber, 0, result, new ArrayList<Character>(), mapping);
//    return result;
//  }
//
//    private static void phoneMnemonic_helper(String phoneNumber, int initPos, List<String> result, List<Character> combination, Map<Character, String> mapping) {
//
//        if(phoneNumber.length() == initPos) {
//            result.add(combination.stream().map(Object::toString).collect(Collectors.joining()));
//            return;
//        }
//
//        Character digit = phoneNumber.charAt(initPos);
//        String values = "";
//
//        if (digit == '0' || digit == '1') {
//            values = "" + digit;
//        } else {
//            values = mapping.get(digit);
//        }
//
//        for(Character ch : values.toCharArray()) {
//            combination.add(ch);
//            phoneMnemonic_helper(phoneNumber, initPos+1, result, combination, mapping);
//            combination.remove(combination.size()-1);
//        }
//    }

  @EpiTestComparator
  public static BiPredicate<List<String>, List<String>> comp =
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
            .runFromAnnotations(args, "PhoneNumberMnemonic.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
