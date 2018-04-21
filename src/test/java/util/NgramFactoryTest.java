package util;

import org.junit.Test;

import java.util.Map;

public class NgramFactoryTest {

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        Map<String, Integer> ngram = NgramFactory.getNgram();
        long end = System.currentTimeMillis();
        System.out.println("时间为" + (end - start));
        System.out.println(ngram.size());
    }
}
