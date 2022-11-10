package com.camerondavis.stats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.stats.AnalyzerConfigurationException;
import org.stats.RatingAnalyzer;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RatingAnalyzerImplTest {

    private int[] ratings1 = {2, 5, 2, 3, 1, 4, 5, 2};
    private int[] ratings2 = {3, 3, 3, 3, 3, 3, 3, -3, -3, -3, -3, -3, -3, -3};
    private int[] ratings3 = {2, 5, 4, 3, 1, 5, 5, 2, 2};
    private int[] ratings4 = null;
    private int[] ratings5 = {};

    private RatingAnalyzer analyzer1;
    private RatingAnalyzer analyzer2;
    private RatingAnalyzer analyzer3;

    @Before
    public void setUp() throws Exception {
        analyzer1 = RatingAnalyzer.newInstance(ratings1);
        analyzer2 = RatingAnalyzer.newInstance(ratings2);
        analyzer3 = RatingAnalyzer.newInstance(ratings3);
    }

    @Test(expected = AnalyzerConfigurationException.class)
    public void testImplClassUsingNullRatings() {

        RatingAnalyzer analyzerUsingNull = RatingAnalyzer.newInstance(ratings4);
        analyzerUsingNull.mode();

        RatingAnalyzer analyzerUsingEmpty = RatingAnalyzer.newInstance(ratings5);
        analyzerUsingEmpty.mode();
    }

    @Test
    public void mean() {

        double actualMean1 = analyzer1.mean();
        double actualMean2 = analyzer2.mean();
        double actualMean3 = analyzer3.mean();

        double predictedMean1 = (2 + 5 + 2 + 3 + 1 + 4 + 5 + 2) / 8.0;
        double predictedMean2 = 0.0;
        double predictedMean3 = (2 + 5 + 4 + 3 + 1 + 5 + 5 + 2 + 2) / 9.0;

        assertEquals(predictedMean1, actualMean1, 0.001);
        assertEquals(predictedMean2, actualMean2, 0.001);
        assertEquals(predictedMean3, actualMean3, 0.001);
    }

    @Test
    public void median() {

        double actualMedian1 = analyzer1.median();
        double actualMedian2 = analyzer2.median();
        double actualMedian3 = analyzer3.median();

        double predictedMedian1 = 2.5;
        double predictedMedian2 = 0.0;
        double predictedMedian3 = 3.0;

        assertEquals(predictedMedian1, actualMedian1, 0.001);
        assertEquals(predictedMedian2, actualMedian2, 0.001);
        assertEquals(predictedMedian3, actualMedian3, 0.001);
    }

    @Test
    public void testModeUsingValidRatings() {

        int[] actualMode1 = analyzer1.mode();
        int[] actualMode2 = analyzer2.mode();
        int[] actualMode3 = analyzer3.mode();

        int[] predictedMode1 = {2};
        int[] predictedMode2 = {-3, 3};
        int[] predictedMode3 = {2, 5};

        Assert.assertEquals(Arrays.toString(predictedMode1), Arrays.toString(actualMode1));
        Assert.assertEquals(Arrays.toString(predictedMode2), Arrays.toString(actualMode2));
        Assert.assertEquals(Arrays.toString(predictedMode3), Arrays.toString(actualMode3));

    }

}