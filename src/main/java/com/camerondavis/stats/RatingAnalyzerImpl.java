package com.camerondavis.stats;

import org.stats.RatingAnalyzer;
import java.util.*;

public class RatingAnalyzerImpl implements RatingAnalyzer {

    private int[] ratings;

    public RatingAnalyzerImpl(int[] ratings)
            throws IllegalArgumentException {

        if (ratings == null || ratings.length == 0) {
            throw new IllegalArgumentException();
        } else {
            this.ratings = ratings;
        }
    }

    @Override
    public double mean() {

        double meanOfRatingsArray = 0.0;
        double sumOfArrayValues = 0.0;

        for (int number : ratings) {
            sumOfArrayValues += number;
        }
        meanOfRatingsArray = sumOfArrayValues / ratings.length;

        return meanOfRatingsArray;
    }

    @Override
    public double median() {

        Arrays.sort(ratings);

        int midPointOfArray = ratings.length / 2;

        double medianValueOfRatingsArray = 0.0;
        //if rating array length is even, calculate median as follows
        if (ratings.length % 2 == 0) {
            medianValueOfRatingsArray = (ratings[midPointOfArray - 1] + ratings[midPointOfArray]) / 2.0;
        } else {
            medianValueOfRatingsArray = ratings[midPointOfArray];
        }

        return medianValueOfRatingsArray;
    }

    @Override
    public int[] mode() {

        //convert ratings into Integer wrapped array
        Integer[] ratingsAsIntegers = new Integer[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            ratingsAsIntegers[i] = ratings[i];
        }

        List<Integer> ratingsAsList = Arrays.asList(ratingsAsIntegers);
        System.out.println("Provided ratings argument: " + ratingsAsList);

        HashMap<Integer, Integer> myRatingsAsAMap = new HashMap<>();

        //create Map object from Integer array. Keys = distinct numbers, values = occurrences
        for (Integer currentNumber : ratingsAsList) {
            if (myRatingsAsAMap.get(currentNumber) == null) {
                myRatingsAsAMap.put(currentNumber, 1);
            } else {
                Integer count = myRatingsAsAMap.get(currentNumber);
                count = count + 1;
                myRatingsAsAMap.put(currentNumber, count);
            }
        }

        //get max # of occurrences
        Integer maxRatingsValue = Collections.max(myRatingsAsAMap.values());

        //define entry set as the ratings map
        Set<Map.Entry<Integer, Integer>> entrySetOfRatingsMap = myRatingsAsAMap.entrySet();


        //find # of times the max occurrence value occurs
        int numberOfMaxOccurrences = 0;
        for (Map.Entry<Integer, Integer> entry : entrySetOfRatingsMap) {
            if (entry.getValue().equals(maxRatingsValue)) {
                numberOfMaxOccurrences = numberOfMaxOccurrences + 1;
            }
        }

        //set mode array length to numberOfMaxOccurrences (array of correct size)
        int[] modeOfRatingsArray = new int[numberOfMaxOccurrences];

        //fill mode array with keys that occurred highest number of times
        int arrayIndexCounter = 0;
        for (Map.Entry<Integer, Integer> entry : entrySetOfRatingsMap) {
            if (entry.getValue().equals(maxRatingsValue)) {
                modeOfRatingsArray[arrayIndexCounter++] = entry.getKey();
            }
        }
        System.out.println("The mode of the ratings array is: " + Arrays.toString(modeOfRatingsArray));
        return modeOfRatingsArray;
    }
}
