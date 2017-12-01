package com.delta.architecturecomponents;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will executeIO on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private ThreadPoolExecutor threadPoolExecutor;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
//        BlockingQueue<Runnable> workQueue,
//        //new ThreadPoolExecutor()


        int i = 0;
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.executeIO(new MyRunnable(1));
//        executorService.executeIO(new MyRunnable(1));
//        executorService.executeIO(new MyRunnable(1));
        threadPoolExecutor = new ThreadPoolExecutor(1, 8, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));
        MyRunnable myRunnable = new MyRunnable(i);
        threadPoolExecutor.execute(new MyRunnable(i));
        threadPoolExecutor.execute(new MyRunnable(i));
        threadPoolExecutor.execute(new MyRunnable(i));
        threadPoolExecutor.execute(new MyRunnable(i));
        threadPoolExecutor.execute(new MyRunnable(i));
        //
        threadPoolExecutor.execute(myRunnable);
        threadPoolExecutor.execute(myRunnable);
        threadPoolExecutor.execute(myRunnable);
        threadPoolExecutor.execute(myRunnable);
        threadPoolExecutor.shutdown();




    }

    class MyRunnable implements Runnable {
        private int i;

        public MyRunnable(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            i++;
            System.out.println(Thread.currentThread()+ "---" + (i));
            System.out.println(threadPoolExecutor);
        }
    }
}