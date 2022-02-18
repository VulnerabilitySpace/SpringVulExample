package club.owefsad.example.controller;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;
import groovy.util.Eval;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author owefsad
 */
@RestController
@RequestMapping("/groovy-injection")
public class GroovyInjection {

    /**
     * @param script @groovy.transform.ASTTest(value={assert java.lang.Runtime.getRuntime().exec("touch pwned")})
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @GetMapping("/classLoader")
    void injectionViaClassLoader(String script) throws IllegalAccessException, InstantiationException {
        final GroovyClassLoader classLoader = new GroovyClassLoader();
        Class<?> groovy = classLoader.parseClass(script);
        groovy.newInstance();
    }


    /**
     * @param script
     */
    @GetMapping("/Eval")
    void injectionViaEval(String script) {
        Eval.me(script);
    }

    /**
     * @param script print java.lang.Runtime.getRuntime().exec('id')
     */
    @GetMapping("/GroovyShell")
    void injectionViaGroovyShell(String script) {
        GroovyShell shell = new GroovyShell();
        shell.evaluate(script);
    }

    @GetMapping("/GroovyCodeSource")
    void injectionViaGroovyShellGroovyCodeSource(String script) {
        GroovyShell shell = new GroovyShell();
        GroovyCodeSource gcs = new GroovyCodeSource(script, "test", "Test");
        shell.evaluate(gcs);
    }
}
