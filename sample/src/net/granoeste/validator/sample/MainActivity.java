
package net.granoeste.validator.sample;

import net.granoeste.validator.DoubleRangeValidator;
import net.granoeste.validator.EmailValidator;
import net.granoeste.validator.IntRangeValidator;
import net.granoeste.validator.JapanesePhoneValidator;
import net.granoeste.validator.MaxLengthValidator;
import net.granoeste.validator.RangeValidator;
import net.granoeste.validator.RequiredValidator;
import net.granoeste.validator.Validator;
import net.granoeste.validator.Validators;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    ViewHolder mViewHolder;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewHolder = new ViewHolder(getWindow().getDecorView());
    }

    public class ViewHolder {
        Validators mValidators = new Validators();

        public EditText mTextPersonName;
        public EditText mTextPassword;
        public EditText mNumberPassword;
        public EditText mTextEmailAddress;
        public EditText mPhone;
        public EditText mTextPostalAddress;
        public EditText mNumber;
        public EditText mNumberDecimal;
        public Button mButton;

        public ViewHolder(final View v) {
            mTextPersonName = findViewByIdAndCast(v, R.id.textPersonName);
            mTextPassword = findViewByIdAndCast(v, R.id.textPassword);
            mNumberPassword = findViewByIdAndCast(v, R.id.numberPassword);
            mTextEmailAddress = findViewByIdAndCast(v, R.id.textEmailAddress);
            mPhone = findViewByIdAndCast(v, R.id.phone);
            mTextPostalAddress = findViewByIdAndCast(v, R.id.textPostalAddress);
            mNumber = findViewByIdAndCast(v, R.id.number);
            mNumberDecimal = findViewByIdAndCast(v, R.id.numberDecimal);
            mButton = findViewByIdAndCast(v, R.id.button);

            mValidators.put(mTextPersonName, new Validator[] {
                    new RequiredValidator("Be sure to input. "),
                    new MaxLengthValidator(30, "Being allowed is to 30 characters. "),
            });
            mValidators.put(mTextPassword, new Validator[] {
                    new RequiredValidator("Be sure to input. "),
                    new RangeValidator(8, 16, "Being allowed is from 8 to 16 characters. "),
            });
            mValidators.put(mNumberPassword, new Validator[] {
                    new RangeValidator(4, 4, "Be sure to input 4 figures "),
                    new IntRangeValidator(1, 9999, "4 figures of numbers are allowed."),
            });
            mValidators.put(mTextEmailAddress, new Validator[] {
                    new EmailValidator("Input the right mail address. "),
            });
            mValidators.put(mPhone, new Validator[] {
                    new JapanesePhoneValidator("Input the right telephone number. "),
            });
            mValidators.put(mTextPostalAddress, new Validator[] {
                    new MaxLengthValidator(7, "Being allowed is to 7 characters. "),
            });
            mValidators.put(mNumber, new Validator[] {
                    new IntRangeValidator(0, Integer.MAX_VALUE, "Input a decimal number. "),
            });
            mValidators.put(mNumberDecimal, new Validator[] {
                    new DoubleRangeValidator(0, Double.MAX_VALUE, "Input an integral number. "),
            });

            mButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(final View v) {
                    mValidators.clearError();
                    if (mValidators.isValid()) {
                        Toast.makeText(getApplicationContext(), "It has some errors. ",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @SuppressWarnings("unchecked")
        private <T> T findViewByIdAndCast(final View v, final int id) {
            return (T) v.findViewById(id);
        }
    }

}
