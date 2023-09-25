package readerwriter_task2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data {
    private static final int INITIAL_VALUE = 100;
    private int myValue = INITIAL_VALUE;

    private ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public void read(int id) {
        lock.readLock().lock();

        int val = myValue;

        System.out.println("Reader:" + id + " got the value: " + val);

        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Reader:" + id + " thinks that the value is: " + val + ", but the value is: " + myValue);

        lock.readLock().unlock();
    }

    public void write(int id, int val) {
        lock.writeLock().lock();

        myValue = val;
        System.out.println("Writer:" + id + " updated the value to :" + val);

        lock.writeLock().unlock();
    }
}
