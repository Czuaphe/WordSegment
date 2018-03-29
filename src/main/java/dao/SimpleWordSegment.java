package dao;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 算法的具体实现
 */
public class SimpleWordSegment implements WordSegment {

    private static Logger logger = Logger.getLogger(SimpleWordSegment.class);

    private static final List<String> DIC = new ArrayList<>();
    private static int MAX_LENGTH = 0;	// 词典中词语的最大长度
    // 静态static块
    static {
        try {
            System.out.println("开始初始化词典");
            int max=1;
            int count=0;
            URL url = SimpleWordSegment.class.getClassLoader().getResource("dic.txt");
            File file = new File(url.getPath());
            System.out.println(url.getPath());
            List<String> lines = Files.readAllLines(file.toPath(), Charset.forName("utf-8"));
            for(String line : lines){
                DIC.add(line);
                count++;
                if(line.length()>max){
                    max=line.length();
//                    System.out.println(line);
                }
            }
            MAX_LENGTH = max;
            System.out.println("完成初始化词典，词数目："+count);
            System.out.println("最大分词长度："+MAX_LENGTH);
        } catch (IOException ex) {
            System.err.println("词典装载失败:");
            ex.printStackTrace();
        }
    }

    /**
     * 实现正向最大匹配算法
     * @param text 要分词的句子
     * @return 返回分词结果
     */
    @Override
    public List<String> MM(String text) {
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
    public List<String> RMM(String string) {
        return null;
    }

    @Override
    public List<String> MC(String string) {
        return null;
    }

    @Override
    public List<String> BM(String string) {
        return null;
    }
}
