import java.util.Collection;

public interface MyExecutorService {
    void submit(final Runnable task);
    void shutdown();
    boolean awaitTermination(long durationInMillis) throws InterruptedException;
    void forceShutdown();
    void invokeAll(Collection<Runnable> collection);
}
