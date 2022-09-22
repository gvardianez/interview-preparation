package lesson_3_concurrent.ping_pong;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong {

    private final ExecutorService executorService;
    private final int repeat;
    private String flag;
    private final Object pojo;
    private final Lock lock;
    private final Condition conditionPing;
    private final Condition conditionPong;

    public PingPong(int repeat) {
        this.repeat = repeat;
        this.executorService = Executors.newFixedThreadPool(2);
        this.flag = "PING";
        this.pojo = new Object();
        this.lock = new ReentrantLock();
        this.conditionPing = lock.newCondition();
        this.conditionPong = lock.newCondition();
    }

    public void startWithWaitNotify() {
        executorService.execute(this::printPingWithWait);
        executorService.execute(this::printPongWithWait);
    }

    public void startWithLock() {
        executorService.execute(this::printPingWithLock);
        executorService.execute(this::printPongWithLock);
    }

    private void printPingWithWait() {
        synchronized (pojo) {
            for (int i = 0; i < repeat; i++) {
                while (!flag.equals("PING")) {
                    try {
                        pojo.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("PING");
                flag = "PONG";
                pojo.notifyAll();
            }
        }
    }

    private void printPongWithWait() {
        synchronized (pojo) {
            for (int i = 0; i < repeat; i++) {
                while (!flag.equals("PONG")) {
                    try {
                        pojo.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("PONG");
                flag = "PING";
                pojo.notifyAll();
            }
        }
    }

    private void printPingWithLock() {
        try {
            lock.lock();
            for (int i = 0; i < repeat; i++) {
                while (!flag.equals("PING")) {
                    conditionPing.await();
                }
                System.out.print("PING");
                flag = "PONG";
                conditionPong.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void printPongWithLock() {
        try {
            lock.lock();
            for (int i = 0; i < repeat; i++) {
                while (!flag.equals("PONG")) {
                    conditionPong.await();
                }
                System.out.print("PONG");
                flag = "PING";
                conditionPing.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void stop() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
