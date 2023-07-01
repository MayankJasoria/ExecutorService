# ExecutorService
An implementation of ExecutorService using monitor and `wait()`, `notify()` for thread synchronization. Currently the
only available implementation is of the fixed thread pool. This executor service exposes some of the methods that are
provided by the actual ExecutorService from `java.util.concurrent` package.

## Sample run
```
Starting thread pool with 10 threads
Submitting 20 operations
Starting operation 3 in thread Pool-1-Thread-18
Starting operation 1 in thread Pool-1-Thread-20
Starting operation 2 in thread Pool-1-Thread-19
Starting operation 4 in thread Pool-1-Thread-17
Starting operation 5 in thread Pool-1-Thread-16
Starting operation 0 in thread Pool-1-Thread-15
Starting operation 6 in thread Pool-1-Thread-21
Starting operation 7 in thread Pool-1-Thread-22
Starting operation 8 in thread Pool-1-Thread-23
Starting operation 9 in thread Pool-1-Thread-24
Ending operation 7
Starting operation 10 in thread Pool-1-Thread-22
Ending operation 8
Starting operation 11 in thread Pool-1-Thread-23
Ending operation 4
Starting operation 12 in thread Pool-1-Thread-17
Ending operation 2
Starting operation 13 in thread Pool-1-Thread-19
Ending operation 9
Starting operation 14 in thread Pool-1-Thread-24
Ending operation 0
Starting operation 15 in thread Pool-1-Thread-15
Ending operation 12
Starting operation 16 in thread Pool-1-Thread-17
Ending operation 11
Starting operation 17 in thread Pool-1-Thread-23
Ending operation 1
Starting operation 18 in thread Pool-1-Thread-20
Ending operation 5
Starting operation 19 in thread Pool-1-Thread-16
Ending operation 6
Ending operation 14
Ending operation 15
Ending operation 3
Ending operation 17
Ending operation 10
Ending operation 13
Ending operation 16
Ending operation 19
Ending operation 18
Service shutdown normally
Starting executor service with 5 threads
Submitting operations using invokeAll
Starting operation 20 in thread Pool-2-Thread-25
Starting operation 21 in thread Pool-2-Thread-26
Starting operation 22 in thread Pool-2-Thread-27
Starting operation 23 in thread Pool-2-Thread-28
Starting operation 24 in thread Pool-2-Thread-29
Force shutdown worked successfully
Exception in thread "Pool-2-Thread-25" Exception in thread "Pool-2-Thread-26" java.lang.RuntimeException: java.lang.InterruptedException: sleep interrupted
	at TestOperation.run(TestOperation.java:17)
	at MyThreadPoolExecutor$ThreadPoolRunnable.run(MyThreadPoolExecutor.java:91)
	at java.base/java.lang.Thread.run(Thread.java:833)
Caused by: java.lang.InterruptedException: sleep interrupted
	at java.base/java.lang.Thread.sleep(Native Method)
	at TestOperation.run(TestOperation.java:15)
	... 2 more
Exception in thread "Pool-2-Thread-29" java.lang.RuntimeException: java.lang.InterruptedException: sleep interrupted
	at TestOperation.run(TestOperation.java:17)
	at MyThreadPoolExecutor$ThreadPoolRunnable.run(MyThreadPoolExecutor.java:91)
	at java.base/java.lang.Thread.run(Thread.java:833)
Caused by: java.lang.InterruptedException: sleep interrupted
	at java.base/java.lang.Thread.sleep(Native Method)
	at TestOperation.run(TestOperation.java:15)
	... 2 more
Exception in thread "Pool-2-Thread-28" java.lang.RuntimeException: java.lang.InterruptedException: sleep interrupted
	at TestOperation.run(TestOperation.java:17)
	at MyThreadPoolExecutor$ThreadPoolRunnable.run(MyThreadPoolExecutor.java:91)
	at java.base/java.lang.Thread.run(Thread.java:833)
Caused by: java.lang.InterruptedException: sleep interrupted
	at java.base/java.lang.Thread.sleep(Native Method)
	at TestOperation.run(TestOperation.java:15)
	... 2 more
Exception in thread "Pool-2-Thread-27" java.lang.RuntimeException: java.lang.InterruptedException: sleep interrupted
	at TestOperation.run(TestOperation.java:17)
	at MyThreadPoolExecutor$ThreadPoolRunnable.run(MyThreadPoolExecutor.java:91)
	at java.base/java.lang.Thread.run(Thread.java:833)
Caused by: java.lang.InterruptedException: sleep interrupted
	at java.base/java.lang.Thread.sleep(Native Method)
	at TestOperation.run(TestOperation.java:15)
	... 2 more
java.lang.RuntimeException: java.lang.InterruptedException: sleep interrupted
	at TestOperation.run(TestOperation.java:17)
	at MyThreadPoolExecutor$ThreadPoolRunnable.run(MyThreadPoolExecutor.java:91)
	at java.base/java.lang.Thread.run(Thread.java:833)
Caused by: java.lang.InterruptedException: sleep interrupted
	at java.base/java.lang.Thread.sleep(Native Method)
	at TestOperation.run(TestOperation.java:15)
	... 2 more
```