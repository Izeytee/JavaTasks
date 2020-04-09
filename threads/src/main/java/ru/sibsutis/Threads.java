package ru.sibsutis;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Threads {
    public static void main(String []args) {
        Resource res1 = new Resource();
        Resource res2 = new Resource();
        Thread sync = new Thread(new threadSync(res1));

        Long start = System.nanoTime();
        sync.start();
        try {
            sync.join();
        }
        catch (InterruptedException e) {}
        System.out.println(System.nanoTime() - start);

        ReentrantLock lock = new ReentrantLock();
        Thread sl = new Thread(new threadLock(res2, lock));

        start = System.nanoTime();
        sl.start();
        try {
            sl.join();
        }
        catch (InterruptedException e) {}
        System.out.println(System.nanoTime() - start);

        Store store = new Store();
        Producer producer = new Producer(store);
        new Thread(producer).start();
        for (int i = 0; i < 100; ++i)
            new Thread(new Consumer(store)).start();
        try {
            Thread.sleep(1000);
            System.out.println(TimeUnit.MILLISECONDS.convert(store.totalTime, TimeUnit.NANOSECONDS));
            System.exit(0); 
        }
        catch (InterruptedException e) {

        }
    }
}

class threadSync implements Runnable {
    Resource res;

    public threadSync(Resource res) {
        this.res = res;
    }

    @Override
    public void run() {
        synchronized (res) {
            for (int i = 0; i < 1 << 16; ++i)
                ++res.r;
        }
    }
}

class threadLock implements Runnable {
    Resource res;
    ReentrantLock lock;

    public threadLock(Resource res, ReentrantLock lock) {
        this.res = res;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        for (int i = 0; i < 1 << 16; ++i)
            ++res.r;
        lock.unlock();
    }
}

class Resource {
    public Integer r = 0;
}

class Store{
    private Long product = 0L;
    public Long totalTime = 0L;
    public synchronized void get() {
        while (product <= 0) {
            try {
                Long tempTime = System.nanoTime();
                wait();
                totalTime += System.nanoTime() - tempTime;
            }
            catch (InterruptedException e) {
            }
        }
        product -= 25;
    }

    public synchronized void put() {
        product += 1000;
        notifyAll();
    }
}

class Producer implements Runnable{

    Store store;
    Producer(Store store) {
        this.store=store;
    }
    public void run(){
        while (true)
            store.put();
    }
}

class Consumer implements Runnable{

    Store store;
    Consumer(Store store) {
        this.store=store;
    }
    public void run() {
        while (true)
            store.get();
    }
}