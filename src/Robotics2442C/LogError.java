package Robotics2442C;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * A basic logging class so a logger is not needed in every class.
 *
 * @author Octogonapus
 */

public class LogError {
    private static FileHandler fileHandler;

    /**
     * Open a new session to start logging.
     */
    public static void openSession() throws IOException {
        fileHandler = new FileHandler(Controller.mainDirectory.toString() + "Controller-log.%u.%g.txt", true);
    }

    /**
     * Log an error.
     *
     * @param level The level of severity of the error
     * @param msg   The message to be displayed
     */
    public static void log(Level level, String msg) {
        fileHandler.publish(new LogRecord(level, msg));
    }

    /**
     * Close a session and finalize logs.
     */
    public static void closeSession() {
        fileHandler.close();
    }
}
