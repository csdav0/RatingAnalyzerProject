package com.camerondavis.stats;

import org.stats.RatingAnalyzer;

public class RatingAnalyzerImplClient {

    static int[] ratingsEmpty = {};
    static int[] ratingsNull = null;
    static int[] ratingsArray1 = {2, 5, 6, 3, 1, 4, 5, 2, 1, 9, 11};
    static int[] ratingsArray2 = {3, 3, 3, 3};
    public static void main(String[] args) {
        RatingAnalyzer ratingAnalyzer = new RatingAnalyzerImpl(ratingsArray1);
        double median = ratingAnalyzer.median();
        System.out.println(median);
    }
}
