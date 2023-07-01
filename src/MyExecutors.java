public class MyExecutors {
    public static MyExecutorService newFixedThreadPool(int numThreads) {
        return new MyThreadPoolExecutor(numThreads);
    }
}
