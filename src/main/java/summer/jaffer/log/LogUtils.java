package summer.jaffer.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils{

    public static Logger getLogger(Object o) {
        return LoggerFactory.getLogger(o.getClass());
    }

    public static void info(Object o, String msg) {
//        LoggerFactory.getLogger(o.getClass()).info(msg);
        System.out.println(msg);
    }
}