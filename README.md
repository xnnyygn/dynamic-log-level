# dynamic log level #

A simple trick to change log level dynamically at runtime. You can copy all the code (only 2 files) into your project to use it. Please note the mechanism is designed for log4j 1.x, which is used widely in Java applications.

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

`LogLevelManager` is the main interface to manage the log level.

    public interface LogLevelManager {

      void changeLogLevel(String loggerName, String level);

      void resetLogLevel(String loggerName);

    }

Under the hood, `Log4jLogLevelManager` calls `org.apache.log4j.LogManager` to change log level.

In order to reset the log level, `Log4jLogLevelManager` pushes the original log level to an internal map and sets it back to logger when `resetLogLevel` is called. The map here is `ConcurrentHashMap` so you don't need to worry about the concurrent issues.

Feel free to use this trick if you want to change log level at runtime. It may help you a lot like inspecting an online problem but there's few logs, then you can change the log level to output more. :)
