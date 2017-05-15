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

public class SplitTokenCountValidator extends BaseValidator {
    static final char IDEOGRAPHICS_SPACE = '\u3000';

    public static final int EQ = 0; //2つの数値が等しければ真である。
    public static final int GE = 1; //数値1が数値2以上であれば真である。
    public static final int GT = 2; //数値1が数値2より大きいのであれば真である。
    public static final int LE = 3; //数値1が数値2以下であれば真である。
    public static final int LT = 4; //数値1が数値2未満であれば真である。
    public static final int NE = 5; //2つの数値が等しくなければ真である。

    String mRegex;
    int mCount;
    int mOperator = EQ;

    public SplitTokenCountValidator(final String regex,
            final int count, final String errorMessage) {
        this(regex, count, EQ, errorMessage);
    }

    public SplitTokenCountValidator(final String regex,
            final int count, final int operator, final String errorMessage) {
        super(errorMessage);
        mRegex = regex;
        mCount = count;
        mOperator = operator;
    }

    @Override
    protected boolean condition(String value) {
        if (TextUtils.isEmpty(value)) {
            return true;
        }
        // trim one-byte space and trim two-byte space.
        value = value.trim()
                .replaceAll("^[\\s" + IDEOGRAPHICS_SPACE + "]*", "")
                .replaceAll("[\\s" + IDEOGRAPHICS_SPACE + "]*$", "");

        switch (mOperator) {
            case EQ:
                return value.split(mRegex).length == mCount;
            case GE:
                return value.split(mRegex).length >= mCount;
            case GT:
                return value.split(mRegex).length > mCount;
            case LE:
                return value.split(mRegex).length <= mCount;
            case LT:
                return value.split(mRegex).length < mCount;
            case NE:
                return value.split(mRegex).length != mCount;
            default:
                break;
        }
        return false;

    }
}
