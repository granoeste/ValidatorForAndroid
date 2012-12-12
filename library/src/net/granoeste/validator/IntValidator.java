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

public class IntValidator extends BaseValidator {

    public IntValidator(final String errorMessage) {
        super(errorMessage);
    }

    @Override
    protected boolean condition(final String value) {
        try {
            if (TextUtils.isEmpty(value)) {
                return true;
            }
            Integer.parseInt(value);
            return true;
        } catch (final NumberFormatException e) {
        }
        return false;
    }

}
