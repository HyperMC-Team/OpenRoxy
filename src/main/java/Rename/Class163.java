package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.NonNull;

public class Class163 {
    private static final ScheduledExecutorService RUNNABLE_POOL = Executors.newScheduledThreadPool(3, new ThreadFactory(){
        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public Thread newThread(@NonNull Runnable r) {
            if (r == null) {
                throw new NullPointerException("r is marked non-null but is null");
            }
            return new Thread(r, "Class163 Thread " + this.counter.incrementAndGet());
        }
    });
    public static ExecutorService POOL = Executors.newCachedThreadPool(new ThreadFactory(){
        private final AtomicInteger counter = new AtomicInteger(0);

        @Override
        public Thread newThread(@NonNull Runnable r) {
            if (r == null) {
                throw new NullPointerException("r is marked non-null but is null");
            }
            return new Thread(r, "Class163 Thread " + this.counter.incrementAndGet());
        }
    });

    public static void schedule(Runnable r, long initialDelay, long delay, TimeUnit unit) {
        RUNNABLE_POOL.scheduleAtFixedRate(r, initialDelay, delay, unit);
    }

    public static ScheduledFuture<?> schedule(Runnable r, long delay, TimeUnit unit) {
        return RUNNABLE_POOL.schedule(r, delay, unit);
    }

    public static int getTotal() {
        return ((ThreadPoolExecutor)POOL).getActiveCount();
    }

    public static void runAsync(Runnable runnable) {
        POOL.execute(runnable);
    }
}

