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

import android.view.View;
import android.widget.TextView;

public abstract class BaseValidator implements Validator {
    private final String mMessage;

    public BaseValidator(final String message) {
        super();
        mMessage = message;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }

    @Override
    public boolean isValid(final View v) {
        if (v != null && v instanceof TextView) {
            if (condition(((TextView) v).getText().toString())) {
                return true;
            }
        }

        return false;
    }

    abstract protected boolean condition(String value);
}
