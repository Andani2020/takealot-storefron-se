package org.takealot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.takealot.cucumber.CucumberRunnerOptions;

public class RunCucumberTests {

    private static final Logger logger = LogManager.getLogger(RunCucumberTests.class.getName());
    public RunCucumberTests() {

    }

    public Boolean cucumberTestRunner() {

        CucumberRunnerOptions cro = new CucumberRunnerOptions();

        Result result = null;

        try {
            result = JUnitCore.runClasses(org.takealot.cucumber.CucumberRunner.class);
        } catch (Exception e) {
            logger.error("Exception message = " + e.getMessage());
            e.printStackTrace();
        } finally {
            assert result != null;
            for (Failure failure : result.getFailures()) {
                logger.info("Failed test");
                logger.info("Failed test message = " + failure.getMessage());
            }
        }

        return result.wasSuccessful();
    }

}
