import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyThreadPoolExecutor implements MyExecutorService {

    private static int poolCount = 0;
    private final Queue<Runnable> queue;
    List<Thread> threadPool;
    private boolean isShutdown;

    public MyThreadPoolExecutor(int numThreads) {
        ++poolCount;
        queue = new LinkedList<>();
        threadPool = new ArrayList<>(numThreads);
        for (int i = 0; i < numThreads; ++i) {
            spawnThread();
        }
    }

    @Override
    public void submit(final Runnable task) {
        synchronized (queue) {
            if (!isShutdown) {
                queue.offer(task);
                queue.notify();
            } else {
                throw new IllegalStateException("Cannot accept task when thread pool is in shutdown mode");
            }
        }
    }

    @Override
    public void shutdown() {
        synchronized (queue) {
            isShutdown = true;
            queue.notifyAll();
        }
    }

    @Override
    public boolean awaitTermination(long durationInMillis) throws InterruptedException {
        long stopTime = System.currentTimeMillis();
        for (Thread t : threadPool) {
            if (stopTime < 0) {
                return false;
            }
            t.join(stopTime);
            if (t.isAlive()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void forceShutdown() {
        isShutdown = true;
        for (Thread t : threadPool) {
            t.interrupt();
        }
        synchronized (queue) {
            // ensure all waiting threads are awoken to receive the interrupt
            queue.notifyAll();
        }
    }

    private void spawnThread() {
        Thread thread = new Thread(new ThreadPoolRunnable());
        thread.setName("Pool-" + poolCount + "-Thread-" + thread.getId());
        threadPool.add(thread);
        thread.start();
    }

    @Override
    public void invokeAll(Collection<Runnable> collection) {
        synchronized (queue) {
            for (Runnable runnable : collection) {
                queue.offer(runnable);
            }
            // make every waiting thread attempt to pick up tasks
            queue.notifyAll();
        }
    }

    private class ThreadPoolRunnable implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Runnable runnable;
                    synchronized (queue) {
                        while (queue.isEmpty()) {
                            if (isShutdown) {
                                return;
                            }
                            queue.wait();
                        }
                        if (Thread.currentThread().isInterrupted()) {
                            throw new InterruptedException("Thread was interrupted");
                        }
                        runnable = queue.poll();
                    }
                    runnable.run();
                }
            } catch (InterruptedException e) {
                if (!isShutdown) {
                    System.out.println("Thread was interrupted externally");
                    spawnThread(); // create new thread to maintain thread pool
                    throw new RuntimeException(e);
                }
                // force shutdown was used, no action needed
            }
        }
    }
}
