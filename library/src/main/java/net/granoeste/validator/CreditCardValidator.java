
package net.granoeste.validator;

import android.text.TextUtils;

public class CreditCardValidator extends BaseValidator {
    private final org.apache.commons.validator.CreditCardValidator mValidator;

    public CreditCardValidator(String message) {
        super(message);
        mValidator = new org.apache.commons.validator.CreditCardValidator();
    }

    public CreditCardValidator(int options, String message) {
        super(message);
        mValidator = new org.apache.commons.validator.CreditCardValidator(options);
    }

    @Override
    protected boolean condition(String value) {
        return TextUtils.isEmpty(value) || mValidator.isValid(value);
    }
}
