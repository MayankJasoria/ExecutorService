import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting thread pool with 10 threads");
        MyExecutorService service = MyExecutors.newFixedThreadPool(10);

        System.out.println("Submitting 20 operations");
        for (int i = 0; i < 20; ++i) {
            service.submit(new TestOperation(i));
        }

        try {
            // expect normal shutdown
            service.shutdown();
            if (!service.awaitTermination(100_000)) {
                System.out.println("Force shutdown used!");
                service.forceShutdown();
            }
            System.out.println("Service shutdown normally");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Starting executor service with 5 threads");
        MyExecutorService service1 = MyExecutors.newFixedThreadPool(5);

        System.out.println("Submitting operations using invokeAll");
        List<Runnable> operations = new ArrayList<>(20);
        for (int i = 0; i < 20; ++i) {
            operations.add(new TestOperation(20 + i));
        }
        service1.invokeAll(operations);

        Thread.sleep(20);

        // force shutdown
        try {
            service1.forceShutdown();
            System.out.println("Force shutdown worked successfully");
        } catch (Exception e) {
            System.out.println("Force shutdown led to exception: " + e);
        }
    }
}
