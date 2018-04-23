package resource;

import org.junit.Test;

public class DictionaryLoaderTest {

    @Test
    public void Test() {
        DictionaryLoader loader = new DictionaryLoader();

        loader.loadResource(DictionaryLoader.defaultDicName);

    }
}
