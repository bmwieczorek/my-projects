package concurency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Assert;

import org.junit.Test;

public class CallableTest {

    @Test
    public void shouldReturnSentenceLength() throws Exception {
        // given
        String text = "Ala ma kota";

        // when
        ExecutorService pool = Executors.newFixedThreadPool(3);
        int result = 0;
        for (String word : text.split(" ")) {
            result += pool.submit(createCallable(word)).get();
        }

        // then
        Assert.assertEquals(text.length() - 2, result);

    }

    private Callable<Integer> createCallable(final String word) {
        return new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                return word.length();
            }
        };
    }

}
