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
        long start = System.currentTimeMillis();
        List<String> result = segment.MM(string);
        long end = System.currentTimeMillis();
        Assert.assertNotNull(result);
        for (String str : result) {
            System.out.print(str + "/");
        }
        System.out.println();
        System.out.println("time: " + (end - start) + "ms");

    }

    @Test
    public void TestRMM() {
//        String string = "中国人民银行指出我国最近经济不景气";
//        String string = "习近平今日出席了中央气象台的联欢晚会";
        String string = "结婚的和尚未结婚的";
        long start = System.currentTimeMillis();
        List<String> result = segment.RMM(string);
        long end = System.currentTimeMillis();
        Assert.assertNotNull(result);
        for (String str : result) {
            System.out.print(str + "/");
        }
        System.out.println();
        System.out.println("time: " + (end - start) + "ms");

    }

}
