package dao;

import java.util.List;

/**
 * 算法接口
 */
public interface WordSegment {

    /**
     * 正向最大匹配算法（Maximum Matching）接口
     * @param string 一段连续的中文字符
     * @return 分词结果
     */
    List<String> MM(String string);

    /**
     * 反向最大匹配算法（Reverse Maximum Matching）接口
     * @param string 一段连续的中文字符
     * @return 分词结果
     */
    List<String> RMM(String string);

    /**
     * 最少切分算法（Minimum Cut）接口
     * @param string
     * @return
     */
    List<String> MC(String string);

    /**
     * 双向最大匹配算法（Bi-direction Matching）接口
     * @param string
     * @return
     */
    List<String> BM(String string);

}
