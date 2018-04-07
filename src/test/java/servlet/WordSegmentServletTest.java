package servlet;

import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.List;

public class WordSegmentServletTest {

    private WordSegmentServlet wordSegmentServlet = new WordSegmentServlet();

    @Test
    public void testString2dataSegment() {
        String string = "中国人民银行指出我国最近经济不景气";
        List<String> result = wordSegmentServlet.string2dataSegment("RMM", string);
        Assert.assertNotNull(result);
        for (String str : result) {
            System.out.print(str + "/");
        }
    }

    @Test
    public void testSegment() {
        String string = "中国人民银行指出我国最近经济不景气";
        wordSegmentServlet.segment2Json(string);
        JsonObject jsonObject = wordSegmentServlet.getJson();
        System.out.println(jsonObject.toString());

    }

}
