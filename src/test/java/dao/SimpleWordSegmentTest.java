package dao;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SimpleWordSegmentTest {

    private SimpleWordSegment segment = new SimpleWordSegment();

    @Test
    public void Test() {
        String string = "中国人民银行指出我国最近经济不景气";
//        String string = "习近平今日出席了中央气象台的联欢晚会";
        List<String> result = segment.MM(string);

        Assert.assertNotNull(result);
        for (String str : result) {
            System.out.print(str + "/");
        }

    }

}
