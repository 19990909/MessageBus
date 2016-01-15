/*
 * Copyright 2015 dorkbox, llc
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
package dorkbox.util.messagebus.common.adapter;

import java.util.concurrent.ConcurrentMap;

public
class Java6Adapter implements MapAdapter {

    @Override
    public final
    <K, V> ConcurrentMap<K, V> concurrentMap(final int size, final float loadFactor, final int stripeSize) {
        return new ConcurrentHashMapV8<K, V>(size, loadFactor, stripeSize);
    }
}