package club.owefsad.example;

import club.owefsad.example.controller.IndexControllerTest;
import club.owefsad.example.controller.cmdInjectionControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        cmdInjectionControllerTest.class,
        IndexControllerTest.class
})
public class SuiteUnitTest {

}
