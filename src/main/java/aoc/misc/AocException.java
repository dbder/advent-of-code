package aoc.misc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AocException extends RuntimeException {
    private static final Logger log = LogManager.getLogger(AocException.class);

    public AocException(String message) {
        super(message);
        log.warn(message);
    }

    public AocException() {
        log.warn("exception thrown");
    }
}
