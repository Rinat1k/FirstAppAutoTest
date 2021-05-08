package test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", //путь к папке с .feature файлами.
        glue = "src/test/java/OnlineTradePages/steps"//пакет, в котором находятся классы с реализацией шагов и «хуков"
)
public class OnlineTradeSmokeTest
{
}
