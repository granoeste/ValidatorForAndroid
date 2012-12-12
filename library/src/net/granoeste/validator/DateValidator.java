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

import java.util.Locale;

import android.text.TextUtils;

public class DateValidator extends BaseValidator {

    org.apache.commons.validator.DateValidator mValidator;
    Locale mLocale;
    String mDatePattern;
    boolean mStrict;

    public DateValidator(final String errorMessage) {
        super(errorMessage);
        mValidator = org.apache.commons.validator.DateValidator.getInstance();
        mLocale = Locale.getDefault();
    }

    public DateValidator(final String errorMessage, final String datePattern, final boolean strict) {
        super(errorMessage);
        mValidator = org.apache.commons.validator.DateValidator.getInstance();
        mLocale = Locale.getDefault();
        mDatePattern = datePattern;
        mStrict = strict;
    }

    @Override
    protected boolean condition(final String value) {
        if (TextUtils.isEmpty(value)) {
            return true;
        }
        if (mDatePattern != null) {
            return mValidator.isValid(value, mDatePattern, mStrict);
        }
        return mValidator.isValid(value, mLocale);
    }

}
