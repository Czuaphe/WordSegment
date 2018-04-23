package resource;

import org.junit.Test;

public class FileResourceLoaderTest {

    @Test
    public void Test() {
        FileResourceLoader loader = new FileResourceLoader() {
            @Override
            protected void analysis() {
                System.out.println("匿名子类没有实现");
            }
        };

        loader.loadResource("dic.txt");
    }

}
