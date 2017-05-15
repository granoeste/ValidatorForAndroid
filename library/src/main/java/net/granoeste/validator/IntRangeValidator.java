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

import android.text.TextUtils;

import java.text.NumberFormat;
import java.text.ParseException;

public class IntRangeValidator extends BaseValidator {
    int mMin;
    int mMax;

    public IntRangeValidator(final int min, final int max, final String errorMessage) {
        super(errorMessage);
        mMin = min;
        mMax = max;
    }

    @Override
    protected boolean condition(final String value) {
        if (TextUtils.isEmpty(value)) {
            return true;
        }
        try {
            Number number = NumberFormat.getInstance().parse(value);
            if (!(number instanceof Long)) {
                return false;
            }
            final int i = number.intValue();
            return mMin <= i && i <= mMax;
        } catch (ParseException e) {
        }
        return false;
    }

}
