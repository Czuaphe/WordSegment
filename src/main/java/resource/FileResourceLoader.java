package resource;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个抽象工厂类
 */
public abstract class FileResourceLoader {

    private static Logger logger = Logger.getLogger(FileResourceLoader.class);
    // 导入文件的所有行
    protected List<String> FILE_ROWS = new ArrayList<>();
    // 文件所有行中最长的一行的长度
    protected Integer MAX_LENGTH = 0;

    /**
     * 加载资源时，先加载文件，再解析文件
     */
    public void loadResource(String fileName) {
        // 导入文件
        loadFile(fileName);
        // 解析文件，由子类实现
        analysis();
    }

    /**
     * 加载文件，将文件的每一行
     * @param fileName 文件名
     */
    private void loadFile(String fileName) {
        logger.debug("---开始初始化文件---");
        URL url = DictionaryLoader.class.getClassLoader().getResource(fileName);
        File file = new File(url.getPath());
        logger.debug("文件的路径为：" + url.getPath());
        // 文件的行列表
        List<String> lines = null;
        // 每行的最大长度和行数
        int max = 0, count = 0;
        try {
            lines = Files.readAllLines(file.toPath(), Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO 这里得到文件中所有行进行了两次，是否可以简化成一次
        // 遍历每一行
        for(String line : lines){
            FILE_ROWS.add(line);
            count ++;
            if(line.length()>max){
                max=line.length();
            }
        }
        MAX_LENGTH = max;
        logger.debug("文件的总行数为：" + FILE_ROWS.size());
        logger.debug("文件中最长的一行的长度为：" + MAX_LENGTH);
        logger.debug("---初始化文件结束---");
    }

    /**
     * 对文件中的每一行进行解析
     */
    protected abstract void analysis();
}
