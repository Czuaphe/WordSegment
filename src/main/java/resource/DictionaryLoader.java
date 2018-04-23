package resource;

import org.apache.log4j.Logger;

import java.util.List;

public class DictionaryLoader extends FileResourceLoader{

    private static Logger logger = Logger.getLogger(DictionaryLoader.class);

    public static String defaultDicName = "dic.txt";


    @Override
    protected void analysis() {
        logger.debug("开始解析词典");
        // 词典什么都不需要干，返回数据即可
        logger.debug("解析词典结束");
    }

    public List<String> getDictionary() {
        return FILE_ROWS;
    }

    public int getMaxLength() {
        return MAX_LENGTH;
    }
}
