package dao;

import org.apache.log4j.Logger;
import resource.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 算法的具体实现
 */
public class SimpleWordSegment implements WordSegment {

    private static Logger logger = Logger.getLogger(SimpleWordSegment.class);

    // 得到词频映射表（二元模型）
    private final Map<String, Integer> N_GRAM = Resource.getBigram();
    // 词典列表
    private final List<String> DIC = Resource.getDictionary();
    private int MAX_LENGTH = Resource.getDicMaxLength();	// 词典中词语的最大长度

    /**
     * 实现正向最大匹配算法
     * @param text 要分词的句子
     * @return 返回分词结果
     */
    @Override
    public List<String> MM(String text) {
        logger.debug("分词算法为：MM算法");
        logger.debug("要分词的句子为：" + text);
        List<String> result = new ArrayList<>();
        while(text.length()>0){
            int len=MAX_LENGTH;
            if(text.length()<len){
                len=text.length();
            }
            //取指定的最大长度的文本去词典里面匹配
            String tryWord = text.substring(0, 0+len);
            while(!DIC.contains(tryWord)){
                //如果长度为一且在词典中未找到匹配，则按长度为一切分
                if(tryWord.length()==1){
                    break;
                }
                //如果匹配不到，则长度减一继续匹配
                tryWord=tryWord.substring(0, tryWord.length()-1);
            }
            result.add(tryWord);
            //从待分词文本中去除已经分词的文本
            text=text.substring(tryWord.length());
        }
        return result;
    }

    @Override
    public List<String> RMM(String text) {
        logger.debug("分词算法为：RMM算法");
        logger.debug("要分词的句子为：" + text);
        List<String> result = new ArrayList<>();
        while(text.length()>0){
            int len=MAX_LENGTH;
            if(text.length()<len){
                len=text.length();
            }
            //取指定的最大长度的文本去词典里面匹配
            String tryWord = text.substring(text.length() - len, text.length());
            while(!DIC.contains(tryWord)){
                //如果长度为一且在词典中未找到匹配，则按长度为一切分
                if(tryWord.length()==1){
                    break;
                }
                //如果匹配不到，则长度减一继续匹配
                tryWord=tryWord.substring(1, tryWord.length());
            }
            result.add(tryWord);
            //从待分词文本中去除已经分词的文本
            text=text.substring(0, text.length() - tryWord.length());
        }

        Collections.reverse(result);

        return result;
    }


    /**
     * 最少切分算法
     * @param string
     * @return
     */
    @Override
    public List<String> MC(String string) {
        List<List<String>> word = new ArrayList<>();
        System.out.println("最少切分算法开始");
        for (int i = 0; i < string.length(); i ++) {
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(string.charAt(i)));
            for (int j = i + 2; j <= string.length(); j ++) {
                String str = string.substring(i, j);
                if (DIC.contains(str)) {
                    list.add(str);
//                    System.out.println(i + ": " + str);
                }
            }
            word.add(list);
        }

        for(int i = 0; i < word.size(); i ++) {
            System.out.println(i + ": " + word.get(i));
        }
        // 构造成一棵树，得到所有子节点
        List<Node> leaf = new ArrayList<>();
        for (String first: word.get(0)) {
            Node node = new Node(first, null);
            buildChildNode(node, word, first.length(), leaf);
        }
        System.out.println("可能的情况有：" + leaf.size());

        // 得到所有子结点对应的情况
        List<List<String>> result = new ArrayList<>();
        for (Node node: leaf) {
            List<String> list = new ArrayList<>();
            while (node != null) {
                list.add(node.getText());
                node = node.getParent();
            }
            Collections.reverse(list);
            result.add(list);
        }

        for (List<String> list: result) {
            System.out.println("结果：" + list + " " + list.size());
        }

        // 得到长度最短的分词结果
        int min = result.get(0).size();
        for (List<String> list: result) {
            if (list.size() < min) {
                min = list.size();
            }
        }
        List<List<String>> minResult = new ArrayList<>();
        for (List<String> list: result) {
            if (list.size() == min) {
                minResult.add(list);
            }
        }
        System.out.println("词数最少的结果有：" + minResult.size());
        for (List<String> list : minResult) {
            System.out.println(list);
        }
        // 计算分值
        List<Integer> scoreList = new ArrayList<>();
        for (List<String> list : minResult) {
            int score = 0;
            for (int i = 0; i < list.size() - 1; i ++) {
                String key = list.get(i) + ":" + list.get(i + 1);
                if (N_GRAM.containsKey(key)) {
                    System.out.println(key + " : " + N_GRAM.get(key));
                    score += N_GRAM.get(key);
                } else {
                    System.out.println(key + " : 没有这个映射");
                }
            }
            scoreList.add(score);
            System.out.println(list + " 得分为: " + score);
        }

        // 得到得分最高的分词结果
        int minScore = scoreList.get(0);
        List<String> finalResult = minResult.get(0);
        for (int i = 1; i < scoreList.size(); i ++) {
            if (scoreList.get(i) > minScore) {
                minScore = scoreList.get(i);
                finalResult = minResult.get(i);
            }
        }
        System.out.println("最高的得分为：" + minScore);
        System.out.println("最后的分词结果为：" + finalResult);

        return finalResult;
    }

    /**
     * 通过父结点找到子节点（递归函数）
     * @param parent 父结点
     * @param word 所有的词
     * @param length 当前结点的长度
     * @param leaf 子结点的集合
     */
    private void buildChildNode(Node parent, List<List<String>> word, int length, List<Node> leaf) {
        if (length >= word.size()) {
            // 达到子结点，加入列表中
            leaf.add(parent);
            return ;
        }
        for (String string: word.get(length)) {
            Node child = new Node(string, parent);
            buildChildNode(child, word, length + string.length(), leaf);
        }
    }

    @Override
    public List<String> BM(String text) {

        logger.debug("分词算法为：BM算法");
        logger.debug("要分词的句子为：" + text);

        List<String> mm = MM(text);

        List<String> rmm = RMM(text);

        List<String> result = null;

        // 1、首先，比较两种算法的分词的个数，选出最少的
        if (mm.size() < rmm.size()) {
            return mm;
        }

        if (rmm.size() < mm.size()) {
            return rmm;
        }

        // 2、长度相同，是否分词完全相同
        boolean flag = true;
        for (int i = 0; i < mm.size(); i ++) {
            if (!mm.get(i).equals(rmm.get(i))) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return mm;
        }

        // 3、如果不同，选择单字较少的分词结果，
        int mm_number = 0;
        int rmm_number = 0;
        for (int i = 0; i < mm.size(); i ++) {
            if (mm.get(i).length() == 1) {
                mm_number ++;
            }
            if (rmm.get(i).length() == 1) {
                rmm_number ++;
            }
        }
        logger.debug("mm_number:" + mm_number);
        logger.debug("rmm_number:" + rmm_number);
        if (mm_number < rmm_number) {
            return mm;
        }
        if (rmm_number < mm_number) {
            return rmm;
        }

        // 4、如果单字也相同，选择RMM算法
        return rmm;
    }

    @Override
    public List<String> FS(String string) {
        return null;
    }

    private class Node {
        private String text;
        private Node parent;

        public Node(String text, Node parent) {
            this.text = text;
            this.parent = parent;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }
}
