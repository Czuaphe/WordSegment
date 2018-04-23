package resource;

import org.junit.Test;

import java.util.Map;

public class BigramLoaderTest {

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        BigramLoader bigramLoader = new BigramLoader();
        bigramLoader.loadResource("bigram.txt");
        Map<String, Integer> ngram = bigramLoader.getBigram();
        long end = System.currentTimeMillis();
//        System.out.println("时间为" + (end - start));
//        System.out.println(ngram.size());
    }
}
