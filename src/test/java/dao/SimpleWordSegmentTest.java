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

    @Test
    public void TestBM() {
//        String string = "中国人民银行指出我国最近经济不景气";
//        String string = "结婚的和尚未结婚的";
//        String string = "我们在野生动物园玩";
//        String string = "发展中国家兔的计划"; // 都没有分词成功
        String string = "美国会通过赫尔姆斯伯顿法";
        long start = System.currentTimeMillis();
        List<String> result = segment.BM(string);
        long end = System.currentTimeMillis();
        Assert.assertNotNull(result);
        for (String str : result) {
            System.out.print(str + "/");
        }
        System.out.println();
        System.out.println("time: " + (end - start) + "ms");
    }

    @Test
    public void TestMC() {
//        String string = "中国人民银行指出我国最近经济不景气";
//        String string = "发展中国家兔的计划";
        String string = "结婚的和尚未结婚的";
        long start = System.currentTimeMillis();
        List<String> result = segment.MC(string);
        long end = System.currentTimeMillis();
//        Assert.assertNotNull(result);
//        for (String str : result) {
//            System.out.print(str + "/");
//        }
        System.out.println();
        System.out.println("time: " + (end - start) + "ms");
    }

}
