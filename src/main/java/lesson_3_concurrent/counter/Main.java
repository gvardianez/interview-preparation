package lesson_3_concurrent.counter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(counter::increase);
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println(counter.getValue());
    }
}