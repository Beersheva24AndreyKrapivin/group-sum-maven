package telran.numbers;

import java.util.concurrent.FutureTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsPoolGroupSum extends ThreadsGroupSum {

    public ThreadsPoolGroupSum(int[][] groups) {
        super(groups);
    }

    @Override
    protected void startTasks(FutureTask<Long>[] tasks) {
        // Write this method by using threads pool
        ExecutorService executor = Executors.newFixedThreadPool(4);
        try {
            for (int i = 0; i < tasks.length; i++) {
                tasks[i] = new FutureTask<>(new OneGroupSum(groups[i]));
                executor.execute(tasks[i]);
            }
        } finally {
            executor.shutdown();
        }
    }

}
