import java.util.concurrent.ThreadLocalRandom;

public class TestOperation implements Runnable {

    private final int operationId;

    TestOperation(int operationId) {
        this.operationId = operationId;
    }

    @Override
    public void run() {
        System.out.println("Starting operation " + operationId + " in thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(50, 500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Ending operation " + operationId);
    }
}
