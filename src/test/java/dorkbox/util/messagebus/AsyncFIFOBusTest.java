package dorkbox.util.messagebus;

import dorkbox.util.messagebus.annotations.Handler;
import dorkbox.util.messagebus.common.MessageBusTest;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author bennidi
 *         Date: 3/30/14
 */
public class AsyncFIFOBusTest extends MessageBusTest {

    @Test
    public void testSingleThreadedSyncFIFO(){
        // create a fifo bus with 1000 concurrently subscribed listeners
        IMessageBus fifoBUs = new MultiMBassador();
        fifoBUs.start();

        List<Listener> listeners = new LinkedList<Listener>();
        for(int i = 0; i < 1000 ; i++){
            Listener listener = new Listener();
            listeners.add(listener);
            fifoBUs.subscribe(listener);
        }

        // prepare set of messages in increasing order
        int[] messages = new int[1000];
        for(int i = 0; i < messages.length ; i++){
             messages[i] = i;
        }
        // publish in ascending order
        for(Integer message : messages) {
            fifoBUs.publish(message);
        }

        while(fifoBUs.hasPendingMessages()) {
            pause(1000);
        }

        for(Listener listener : listeners){
            assertEquals(messages.length, listener.receivedSync.size());
            for(int i=0; i < messages.length; i++){
                assertEquals(messages[i], listener.receivedSync.get(i));
            }
        }
        fifoBUs.shutdown();
    }

    @Test
    public void testSingleThreadedSyncAsyncFIFO(){
        // create a fifo bus with 1000 concurrently subscribed listeners
        IMessageBus fifoBUs = new MultiMBassador(1);

        List<Listener> listeners = new LinkedList<Listener>();
        for(int i = 0; i < 1000 ; i++){
            Listener listener = new Listener();
            listeners.add(listener);
            fifoBUs.subscribe(listener);
        }

        // prepare set of messages in increasing order
        int[] messages = new int[1000];
        for(int i = 0; i < messages.length ; i++){
            messages[i] = i;
        }
        // publish in ascending order
        for (Integer message : messages) {
            fifoBUs.publishAsync(message);
        }

        while (fifoBUs.hasPendingMessages()) {
            pause(2000);
        }

        for (Listener listener : listeners) {
            List<Integer> receivedSync = listener.receivedSync;

            synchronized (receivedSync) {
                assertEquals(messages.length, receivedSync.size());

                for(int i=0; i < messages.length; i++){
                    assertEquals(messages[i], receivedSync.get(i));
                }
            }
        }

        fifoBUs.shutdown();
    }

    public static class Listener {

        private List<Integer> receivedSync = new LinkedList<Integer>();

        @Handler
        public void handleSync(Integer message){
            synchronized (this.receivedSync) {
                this.receivedSync.add(message);
            }
        }

    }
}
