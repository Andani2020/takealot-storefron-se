package org.takealot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Run
{
    private static final Logger logger = LogManager.getLogger(Run.class.getName());
    public static void main(String[] args) {

        RunCucumberTests rct = new RunCucumberTests();
        boolean result = rct.cucumberTestRunner();
        logger.debug("Test execution result = " + result);
    }
}
