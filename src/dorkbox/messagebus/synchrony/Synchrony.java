/*
 * Copyright 2016 dorkbox, llc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dorkbox.messagebus.synchrony;

import dorkbox.messagebus.dispatch.Dispatch;

/**
 * @author dorkbox, llc Date: 2/3/16
 */
public
interface Synchrony {
    void publish(Dispatch dispatch, Object message1);
    void publish(Dispatch dispatch, Object message1, Object message2);
    void publish(Dispatch dispatch, Object message1, Object message2, Object message3);

    void shutdown();
    boolean hasPendingMessages();
}
