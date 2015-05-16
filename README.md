# dynamic log level #

 a simple trick to change log level at runtime 

## Usage ##

    import org.apache.log4j.Logger;
    import org.junit.Test;

    public class Log4jLogLevelManagerTest {

      private static final Logger logger = Logger.getLogger("test");

      @Test
      public void test() {
        Log4jLogLevelManager manager = new Log4jLogLevelManager();

        // original level of logger 'test' is INFO
        logger.info("info message should be outputed");
        logger.debug("debug message should not be outputed");

        // change log level to debug
        manager.changeLogLevel("test", "DEBUG");

        logger.debug("debug message should be outputed now");

        // reset level of logger 'test'
        manager.resetLogLevel("test");

        logger.debug("debug message should not be outputed again");
      }

    }
    
## Log4jLogLevelManager ##

This trick is for log4j 1.x, which is used widely in Java application. `LogLevelManager` is the interface for this manager containing two main methods as follows

    public interface LogLevelManager {

      void changeLogLevel(String loggerName, String level);

      void resetLogLevel(String loggerName);

    }

`Log4jLogLevelManager` uses `org.apache.log4j.LogManager` rather than `org.apache.log4j.Logger` to provide log level management since `Logger#exists(String)` is a deprecated method.

For recovering log level, `Log4jLogLevelManager` pushes the original log level to a map when changed at the first time and sets it back to logger when `resetLogLevel` is called. The map here is `ConcurrentHashMap` to prevent concurrent issues.

Feel free to use this trick or try it if you have never thinked of changing log level at runtime. It may help you a lot when you have to solve an online problem but there's very few log at such log level. :)