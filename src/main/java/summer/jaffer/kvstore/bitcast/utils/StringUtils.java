package summer.jaffer.kvstore.bitcast.utils;

import java.util.Objects;

public class StringUtils {

    public static Long str2Long(String s) {
        if (s != null && !s.trim().equals(""))
            return Long.parseLong(s);
        else return null;
    }

    public static Integer str2Int(String s) {
        if (s != null && !s.trim().equals(""))
            return Integer.parseInt(s);
        else return null;
    }
}
