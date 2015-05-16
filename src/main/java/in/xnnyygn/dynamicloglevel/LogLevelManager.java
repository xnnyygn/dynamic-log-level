package in.xnnyygn.dynamicloglevel;


/**
 * Log level manager.
 * 
 * @author xnnyygn
 */
public interface LogLevelManager {

  void changeLogLevel(String loggerName, String level);

  void resetLogLevel(String loggerName);

}
