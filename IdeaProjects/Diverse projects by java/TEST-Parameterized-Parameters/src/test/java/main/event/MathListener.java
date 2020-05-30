package main.event;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class MathListener extends RunListener
{
    @Override
    public void testRunStarted(Description description) throws Exception
    {
        System.out.println("testRunStarted - " + description.getDisplayName());
    }

    @Override
    public void testRunFinished(Result result) throws Exception
    {
        System.out.println("result.getIgnoreCount() : " + result.getIgnoreCount());
        System.out.println("result.getRunCount() : " + result.getRunCount());
        System.out.println("result.getRunTime() : " + result.getRunTime());
        System.out.println("result.wasSuccessful() : " + result.wasSuccessful());
        System.out.println("result.getFailureCount() : " + result.getFailureCount());
        System.out.println("result.getFailures() : " + result.getFailures());
    }

    @Override
    public void testStarted(Description description) throws Exception
    {
        System.out.println("Test " + description.getMethodName() + " started");
    }

    @Override
    public void testFinished(Description description) throws Exception
    {
        System.out.println("Test " + description.getMethodName() + " finished");
    }

    @Override
    public void testFailure(Failure failure) throws Exception
    {
        System.out.println("failure.getTestHeader() : " + failure.getTestHeader());
        System.out.println("failure.getMessage() : " + failure.getMessage());
        System.out.println("failure.getDescription() : " + failure.getDescription());
        System.out.println("failure.getException() : " + failure.getException());
    }

    @Override
    public void testAssumptionFailure(Failure failure)
    {
        System.out.println("testAssumptionFailure");
    }

    @Override
    public void testIgnored(Description description) throws Exception
    {
        System.out.println("Test " + description.getMethodName() + " ignored");
    }
}
