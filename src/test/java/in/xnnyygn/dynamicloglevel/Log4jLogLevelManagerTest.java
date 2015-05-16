/**
 * 
 */
package in.xnnyygn.dynamicloglevel;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Test.
 * 
 * @author xnnyygn
 */
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
