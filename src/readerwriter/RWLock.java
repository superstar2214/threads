package readerwriter;

public class RWLock {
    private int readers = 0;
    private boolean writing = false;

    public RWLock() {
    }

    public synchronized void acquireRead() {
        while (writing) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        readers++;
    }

    public synchronized void releaseRead() {
        readers--;
        if (readers == 0) {
            notifyAll();
        }
    }

    public synchronized void acquireWrite() {
        while (readers > 0 || writing) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        writing = true;
    }

    public synchronized void releaseWrite() {
        writing = false;

        notifyAll();
    }
}
