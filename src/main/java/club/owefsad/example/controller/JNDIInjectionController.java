package club.owefsad.example.controller;

import com.sun.jndi.url.dns.dnsURLContext;
import com.sun.rowset.JdbcRowSetImpl;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import javax.naming.NamingException;
import org.apache.activemq.artemis.jndi.ReadOnlyContext;
import org.apache.camel.util.jndi.JndiContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author owefsad
 */
@RestController
@RequestMapping("/jndi")
public class JNDIInjectionController {

    private static final Logger LOGGER = (Logger) LogManager.getLogger(JNDIInjectionController.class);

    /**
     * Log4j2 JNDI Injection
     *
     * @param name ${jndi:ldap://localhost:2145} -> %24%7Bjndi%3Aldap%3A%2F%2Fowefsad%7D
     * @return
     * @throws IOException
     */
    @GetMapping("/log4j2-error")
    public String runtime(String name) throws IOException {
        LOGGER.error(name);
        return "Log4j2 Payload is :" + name;
    }

    /**
     * @param name ldap://localhost:2145
     * @return
     * @throws NamingException
     */
    @GetMapping("dnsURLContext")
    public String dnsURLContext(String name) throws NamingException {
        dnsURLContext context = new dnsURLContext(new Hashtable<>());
        context.lookup(name);
        return "dnsURLContext lookup for: " + name;
    }

    /**
     * @param name ldap://localhost:2145
     * @return
     * @throws NamingException
     */
    @GetMapping("ReadOnlyContext")
    public String ReadOnlyContext(String name) throws NamingException {
        ReadOnlyContext context = new ReadOnlyContext();
        context.lookup(name);
        return "ReadOnlyContext lookup for: " + name;
    }

    /**
     * @param name ldap://localhost:2145
     * @return
     * @throws SQLException
     */
    @GetMapping("JdbcRowSetImpl")
    public String JdbcRowSetImpl(String name) throws SQLException {
        JdbcRowSetImpl impl = new JdbcRowSetImpl();
        impl.setDataSourceName(name);
        impl.setAutoCommit(true);
        return "JdbcRowSetImpl init for: " + name;
    }

    /**
     * @param name ldap://localhost:2145
     * @return
     * @throws Exception
     */
    @GetMapping("camelJndiContext")
    public String CamelJndiContext(String name) throws Exception {
        org.apache.camel.util.jndi.JndiContext context = new JndiContext();
        context.lookup(name);
        return "org.apache.camel.util.jndi.JndiContext lookup for: " + name;
    }
}
