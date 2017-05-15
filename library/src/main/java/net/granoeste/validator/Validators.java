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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Validators
 */
public class Validators {

    /** Validators */
    private final Map<View, Validator[]> mValidatorMap = new HashMap<View, Validator[]>();

    /** Add validations of view. */
    public void put(final View v, final Validator[] validators) {
        mValidatorMap.put(v, validators);
    }

    /** Add validations of view. */
    public void put(final Context context, final ValidatableEditText v) {
        final List<Validator> listValidator = new ArrayList<Validator>();

        if (v.isRequired()) {
            listValidator.add(
                    new RequiredValidator(
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_required, v.getLabel())));
        }
        if (v.getMinlength() != -1) {
            listValidator.add(
                    new MinLengthValidator(v.getMinlength(),
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_minlength, v.getLabel(),
                                            v.getMinlength())));
        }
        if (v.getMaxlength() != -1) {
            listValidator.add(
                    new MaxLengthValidator(v.getMinlength(),
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_maxlength, v.getLabel(),
                                            v.getMaxlength())));
        }
        if (v.getMask() != null) {
            listValidator.add(
                    new MaskValidator(v.getMask(),
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_mask, v.getLabel())));
        }
        if (v.isInteger()) {
            listValidator.add(
                    new IntValidator(
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_isInteger, v.getLabel())));
        }
        if (v.isDouble()) {
            listValidator.add(
                    new DoubleValidator(
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_isDouble, v.getLabel())));
        }
        if (v.isDate()) {
            listValidator.add(
                    new DateValidator(
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_isDate, v.getLabel()), v
                                    .getDateFormat(), false));
        }
        if (v.isRange()) {
            listValidator.add(
                    new RangeValidator(v.getRangeFrom(), v.getRangeTo(),
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_range,
                                            v.getLabel(), v.getRangeFrom(), v.getRangeTo())));
        }
        if (v.isIntRange()) {
            listValidator.add(
                    new IntRangeValidator(v.getIntRangeFrom(), v.getIntRangeTo(),
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_intRange,
                                            v.getLabel(), v.getIntRangeFrom(), v.getIntRangeTo())));
        }
        if (v.isDoubleRange()) {
            listValidator.add(
                    new DoubleRangeValidator(v.getDoubleRangeFrom(), v.getDoubleRangeTo(),
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_doubleRange,
                                            v.getLabel(), v.getDoubleRangeFrom(),
                                            v.getDoubleRangeTo())));
        }
        if (v.isEmail()) {
            listValidator.add(
                    new EmailValidator(
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_isEmail, v.getLabel())));
        }
        if (v.isUrl()) {
            listValidator.add(
                    new UrlValidator(
                            v.hasMessage() ? v.getMessage() :
                                    context.getString(R.string.errors_isUrl, v.getLabel())));
        }

        final Validator[] validators = listValidator.toArray(new Validator[0]);

        mValidatorMap.put(v, validators);
    }

    /** Remove view for validators. */
    public void remove(final View v) {
        mValidatorMap.remove(v);
    }

    /** Clear validators. */
    public void clear() {
        mValidatorMap.clear();
    }

    /** Get views for validators. */
    public Set<View> getAllView() {
        return mValidatorMap.keySet();
    }

    /** Execute validation of view. */
    public ValidatorResult execute(final View v) {
        final Validator[] vals = mValidatorMap.get(v);
        final StringBuilder sb = new StringBuilder();
        for (final Validator val : vals) {
            if (!val.isValid(v)) {
                sb.append(val.getMessage()).append('\n');
            }
        }
        if (sb.length() > 0) {
            return new ValidatorResult(v, sb.deleteCharAt(sb.length() - 1).toString());
        }
        return null;
    }

    /** Execute validation all. */
    public List<ValidatorResult> execute() {
        final List<ValidatorResult> results = new ArrayList<ValidatorResult>();

        final Iterator<View> i = mValidatorMap.keySet().iterator();
        while (i.hasNext()) {
            final View v = i.next();
            final Validator[] validators = mValidatorMap.get(v);
            final StringBuilder sb = new StringBuilder();
            for (final Validator val : validators) {
                if (!val.isValid(v)) {
                    sb.append(val.getMessage()).append('\n');
                }
            }
            if (sb.length() > 0) {
                results.add(new ValidatorResult(v, sb.deleteCharAt(sb.length() - 1).toString()));
            }
        }
        return results;
    }

    /** Test valid for view */
    public boolean isValid(final View v) {
        final ValidatorResult result = execute(v);
        boolean ret = false;
        if (ret = result != null) {
            if (result.getView() instanceof TextView) {
                ((TextView) result.getView()).setError(result.getMessage());
            }
        }
        return ret;
    }

    /** Test valid for all view */
    public boolean isValid() {
        final List<ValidatorResult> results = execute();
        final boolean ret = !results.isEmpty();
        if (ret) {
            for (final ValidatorResult result : results) {
                if (result.getView() instanceof TextView) {
                    ((TextView) result.getView()).setError(result.getMessage());
                }
            }
        }
        return ret;
    }

    /** Clear error of view */
    public void clearError(final View v) {
        if (v instanceof TextView) {
            ((TextView) v).setError(null);
        }
    }

    /** Clear all error */
    public void clearError() {
        final Iterator<View> i = mValidatorMap.keySet().iterator();
        while (i.hasNext()) {
            final View v = i.next();
            if (v instanceof TextView) {
                ((TextView) v).setError(null);
            }
        }
    }

}
