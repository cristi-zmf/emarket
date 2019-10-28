package com.cristi.web.jobgram.application;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true, //fail if there any missing steps
        plugin = {"pretty", "html:target/cucumber"},
        tags = "~@todo", // execute all files that dont have the tag "t0d0"
        glue = {// where to look for steps and hooks
                "com.cristi.web.jobgram.application",
                "com.cristi.web.jobgram.domain",
                "cucumber.api.spring",
                "cucumber.runtime.java.spring"
        }
)
public class EmarketAcceptanceLocalIT {
}
