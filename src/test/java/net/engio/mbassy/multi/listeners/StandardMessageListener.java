package net.engio.mbassy.multi.listeners;

import net.engio.mbassy.multi.annotations.Handler;
import net.engio.mbassy.multi.messages.StandardMessage;

/**
 *
 * @author bennidi
 *         Date: 5/24/13
 */
public class StandardMessageListener {

    private static abstract class BaseListener {

        @Handler()
        public void handle(StandardMessage message){
            message.handled(this.getClass());
        }

    }

    public static class DefaultListener extends BaseListener {

        @Override
        public void handle(StandardMessage message){
            super.handle(message);
        }
    }

    public static class NoSubtypesListener extends BaseListener {

        @Override
        @Handler(rejectSubtypes = true)
        public void handle(StandardMessage message){
            super.handle(message);
        }
    }


    public static class DisabledListener extends BaseListener {

        @Override
        @Handler(enabled = false)
        public void handle(StandardMessage message){
            super.handle(message);
        }

    }


}