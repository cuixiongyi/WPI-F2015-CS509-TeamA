package com.wpi.cs509.teamA.test.algo;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/**
 * Created by cuixi on 12/15/2015.
 */
public class AlgoRunner {


    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AlgoTestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }

}
