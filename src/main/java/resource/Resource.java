package resource;

import java.util.List;
import java.util.Map;

public class Resource {

    private static DictionaryLoader dictionaryLoader = new DictionaryLoader();
    private static BigramLoader bigramLoader = new BigramLoader();

    /**
     * 加载所有资源
     */
    public static void loadAllResource() {
        dictionaryLoader.loadResource(DictionaryLoader.defaultDicName);
        bigramLoader.loadResource(BigramLoader.defaultBigramName);
    }

    /**
     * 得到词典加载器
     * @return 返回加载完成的词典加载器
     */
    public static DictionaryLoader getDictionaryLoader() {
        return dictionaryLoader;
    }

    /**
     * 得到二元映射加载器
     * @return 返回加载器
     */
    public static BigramLoader getBigramLoader() {
        return bigramLoader;
    }

    /**
     * 得到词典
     * @return 词典列表
     */
    public static List<String> getDictionary() {
        return dictionaryLoader.getDictionary();
    }

    /**
     * 得到词典中的最大长度
     * @return 返回最长的词
     */
    public static int getDicMaxLength() {
        return dictionaryLoader.getMaxLength();
    }

    /**
     *
     * @return
     */
    public static Map<String, Integer> getBigram() {
        return bigramLoader.getBigram();
    }
}
