/*
 * Copyright (C) 2012 granoeste@gmail.com (https://github.com/granoeste)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.granoeste.validator;

public class StringContainValidator extends BaseValidator {
    String[] mElements;
    char[] mCharElements;
    int mCount = -1;

    public StringContainValidator(final String[] elements, final String errorMessage) {
        super(errorMessage);
        mElements = elements;
    }

    public StringContainValidator(final String elements, final String errorMessage) {
        this(elements.split(","), errorMessage);
    }

    public StringContainValidator(final char[] elements, final int count, final String errorMessage) {
        super(errorMessage);
        mCharElements = elements;
        mCount = count;
    }

    @Override
    protected boolean condition(final String value) {
        if (mCount != -1) {
            int count = 0;
            for (final char c : value.toCharArray()) {
                for (final char e : mCharElements) {
                    if (c == e) {
                        count++;
                    }
                    if (count >= mCount) {
                        return true;
                    }
                }
            }
        } else {
            for (final String element : mElements) {
                if (value.contains(element)) {
                    return true;
                }
            }
        }
        return false;
    }

}
