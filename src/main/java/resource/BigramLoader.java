package resource;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class BigramLoader extends FileResourceLoader{

    private static Logger logger = Logger.getLogger(BigramLoader.class);
    public static String defaultBigramName = "bigram.txt";

    private Map<String, Integer> bigram = new HashMap<>();

    public Map<String, Integer> getBigram() {
        return bigram;
    }

    @Override
    protected void analysis() {
        logger.debug("开始解析bigram文件");
        for (String line : FILE_ROWS) {
            String[] str = line.split(" ");
            bigram.put(str[0], Integer.valueOf(str[1]));
        }
        logger.debug("解析bigram文件结束");
        logger.debug("文件长度为：" + bigram.size());
    }
}
