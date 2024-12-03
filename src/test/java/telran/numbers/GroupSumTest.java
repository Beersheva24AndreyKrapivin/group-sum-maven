package telran.numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GroupSumTest {
    private static final long N_GROUPS = 100000;
    private static final long N_NUMBERS_PER_GROUP = 1000;

    int[][] groups = {
            {1, 2}, {3, 4}, {5, 6}
    };

    static int[][] groupPerfomance = Stream.generate(() -> getRandomArray())
            .limit(N_GROUPS).toArray(int[][]::new);

    @Test
    void threadsGroupSumTest() {
        runFunctionalTest(new ThreadsGroupSum(groups));
    }

    @Test
    @Disabled
    void perfomanceThreadsGroupSumTest() {
        runPerfomanceTest(new ThreadsGroupSum(groupPerfomance));
    }

    private void runFunctionalTest(GroupSum groupSum) {
        assertEquals(21, groupSum.computeSum());
    }

    private void runPerfomanceTest(GroupSum groupSum) {
        groupSum.computeSum();
    }

    static int[] getRandomArray() {
        return new Random().ints(N_NUMBERS_PER_GROUP).toArray();
    }

    @Test
    void threadsPoolGroupSumTest() {
        runFunctionalTest(new ThreadsPoolGroupSum(groups));
    }

    @Test
    void perfomanceThreadsPoolGroupSumTest() {
        runPerfomanceTest(new ThreadsPoolGroupSum(groupPerfomance));
    }
}
