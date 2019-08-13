package summer.jaffer.kvstore.bitcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class Test {

    @Autowired
    private BitCastDB bitCastDB;

    @RequestMapping("/db")
    public String test(int length) {
        int len = length;
        int count = 0;
        String[] ss1 = new String[len];
        String[] ss2 = new String[len];
        for (int i = 0; i < len; i++) {
            String k = randomStr(8);
            String v = randomStr(8);
            ss1[i] = k;
            ss2[i] = v;
        }
        long start = 0;
        System.out.println(start = System.currentTimeMillis());
        for (int i = 0; i < len; i++) {
            bitCastDB.put(ss1[i], ss2[i]);
        }
        long end = 0;
        System.out.println(end = (System.currentTimeMillis() - start));
        return end + "";
    }

    private String randomStr(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }

    @RequestMapping("/read")
    public String read(String key) {
        return bitCastDB.get(key);
    }
}
