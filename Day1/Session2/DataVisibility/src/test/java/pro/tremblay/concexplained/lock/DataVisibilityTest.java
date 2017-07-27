package pro.tremblay.concexplained.lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.xml.crypto.Data;

/**
 * Will we ever get out of the loop?
 * <p>
 * Each example has the same pattern, you have the test running in the main thread and child thread spinning on a loop
 * until a flag changes.
 *
 * @author Henri Tremblay
 */
public class DataVisibilityTest {

    private boolean stopNormalField;
    private volatile boolean stopVolatileField;
    private volatile boolean[] stopArrayField = new boolean[1];
    private AtomicBoolean stopAtomicField;
    private Lock lock = new ReentrantLock();

    /**
     * <b>NO</b> Data visibility to another thread is not guaranteed by a normal field. The child thread might never see
     * {@link #stopNormalField} value changed.
     */
    @Test
    public void normalField() {
        new Thread(() -> {
            while (!stopNormalField);
            System.out.println("Done");
        }).start();
        stopNormalField = true;
    }

    /**
     * <b>YES</b> Volatile ensure data visibility. When a volatile field is changed, all other threads are seeing the
     * value right away.
     */
    @Test
    public void volatileField() {
        new Thread(() -> {
            while (!stopVolatileField);
            System.out.println("Done");
        }).start();
        stopVolatileField = true;
    }

    /**
     * <b>NO</b> Data visibility of array elements is not guaranteed. Elements of a volatile array are not volatile.
     */
    @Test
    public void volatileArrayField() {
        new Thread(() -> {
            while (!stopArrayField[0]);
            System.out.println("Done");
        }).start();
        stopArrayField[0] = true;
    }

    /**
     * <b>YES</b> Data visibility of atomic is guaranteed. The field doesn't need to be final nor volatile
     * since it was created before the thread was started.
     */
    @Test
    public void atomicField() {
        stopAtomicField = new AtomicBoolean();
        new Thread(() -> {
            while (!stopAtomicField.get());
            System.out.println("Done");
        }).start();
        stopAtomicField.set(true);
    }

    /**
     * <b>NO</b> Field initialization occurs in the thread and so might not be visible by the main thread. So the main thread
     * might never go out of its loop.
     */
    @Test
    public void atomicInitAfterField() {
        new Thread(() -> {
            stopAtomicField = new AtomicBoolean();
            while (!stopAtomicField.get());
            System.out.println("Done");
        }).start();
        while (stopAtomicField == null); // Might never get out of here
        stopAtomicField.set(true);
    }

    /**
     * <b>YES</b> Two threads synchronizing on the same mutex are seeing the same thing inside the
     * synchronized section.
     */
    @Test
    public void synchronizedField() {
        new Thread(() -> {
            while (true) {
                synchronized (this) {
                    if(stopNormalField) {
                        break;
                    }
                }
            }
            System.out.println("Done");
        }).start();
        synchronized (this) {
            stopNormalField = true;
        }
    }

    /**
     * <b>NO</b> If the threads are not on the same mutex, there is no guarantee.
     */
    @Test
    public void synchronizedOnDifferentMutexField() {
        new Thread(() -> {
            while (true) {
                synchronized (new Object()) { // Wrong mutex
                    if(stopNormalField) {
                        break;
                    }
                }
            }
            System.out.println("Done");
        }).start();
        synchronized (this) {
            stopNormalField = true;
        }
    }

    /**
     * <b>YES</b> Watch out. A lambda has not the same {@code this} as an inner class.
     */
    @Test
    public void synchronizedInnerClassField() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (DataVisibilityTest.this) { // Specify the this from the outer class
                        if (stopNormalField) {
                            break;
                        }
                    }
                }
                System.out.println("Done");
            }
        }).start();
        synchronized (this) {
            stopNormalField = true;
        }
    }

    /**
     * <b>YES</b> Locking provides the same data visibility as synchronizing.
     */
    @Test
    public void lockField() {
        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    if(stopNormalField) {
                        break;
                    }
                } finally {
                    lock.unlock();
                }
            }
            System.out.println("Done");
        }).start();
        lock.lock();
        try {
            stopNormalField = true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * <b>YES</b> In fact, all JUL are providing the necessary barriers to get the correct visibility.
     */
    @Test
    public void latchField() {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (!stopNormalField);
            System.out.println("Done");
        }).start();
        stopNormalField = true;
        latch.countDown();
    }

    /**
     * <b>YES</b> Finally, I slightly more complicated way using a volatile field ({@link #stopVolatileField}). Writing
     * to the volatile field causes a happens-before relationship so when the threads reads {@link #stopNormalField}
     * after having read {@link #stopVolatileField} it is certain to read the right value.
     * <p>
     * <b>Don't do this at home</b>
     */
    @Test
    public void mutexField() {
        new Thread(() -> {
            while (!stopVolatileField);
            while (!stopNormalField);
            System.out.println("Done");
        }).start();
        stopNormalField = true;
        stopVolatileField = true;
    }
}

