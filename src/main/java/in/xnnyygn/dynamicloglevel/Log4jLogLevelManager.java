package in.xnnyygn.dynamicloglevel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Implementation for log4j.
 * 
 * @author xnnyygn
 */
public class Log4jLogLevelManager implements LogLevelManager {

  private ConcurrentMap<String, Level> levels =
      new ConcurrentHashMap<String, Level>();

  public void changeLogLevel(String loggerName, String level) {
    Logger logger = determineLogger(loggerName);
    if (logger == null) return; // logger not found

    // push original level in the first time of changing
    levels.putIfAbsent(loggerName, logger.getLevel());
    logger.setLevel(Level.toLevel(level));
  }

  private Logger determineLogger(String loggerName) {
    if ("ROOT".equals(loggerName)) return LogManager.getRootLogger();
    // don't use LogManager#getLogger here since getLogger will cause
    // making of new logger if logger not found
    return LogManager.exists(loggerName);
  }

  public void resetLogLevel(String loggerName) {
    Logger logger = determineLogger(loggerName);
    if (logger == null) return; // logger not found

    Level originalLevel = levels.get(loggerName);
    if(originalLevel == null) return; // level of logger is not changed

    logger.setLevel(originalLevel);
  }

}
