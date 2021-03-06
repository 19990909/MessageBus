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
package dorkbox.messageBus.common;

/**
 * @author dorkbox, llc
 *         Date: 2/3/16
 */
public
class MultiClass implements Comparable<MultiClass> {
    private final int value;

    public
    MultiClass(int value) {
        this.value = value;
    }

    @Override
    public
    int compareTo(final MultiClass o) {
        if (value < o.value) {
            return -1;
        }
        else if (value == o.value) {
            return 0;
        }
        else {
            return 1;
        }
    }

    @Override
    public
    boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final MultiClass that = (MultiClass) o;

        return value == that.value;

    }

    @Override
    public
    int hashCode() {
        return value;
    }
}
