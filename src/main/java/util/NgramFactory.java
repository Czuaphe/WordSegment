package util;

import dao.SimpleWordSegment;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NgramFactory {

    private static Map<String, Integer> ngram = new HashMap<>();

    static {
        try {
            System.out.println("开始初始化映射表");
            int count=0;
            URL url = SimpleWordSegment.class.getClassLoader().getResource("bigram.txt");
            File file = new File(url.getPath());
            System.out.println(url.getPath());
            List<String> lines = Files.readAllLines(file.toPath(), Charset.forName("utf-8"));
            for(String line : lines){
                String[] strings = line.split(" ");
                ngram.put(strings[0], Integer.valueOf(strings[1]));
                count++;
            }
            System.out.println("映射数目："+count);
        } catch (IOException ex) {
            System.err.println("映射表装载失败:");
            ex.printStackTrace();
        }
    }

    public static Map<String, Integer> getNgram() {
        return ngram;
    }
}
