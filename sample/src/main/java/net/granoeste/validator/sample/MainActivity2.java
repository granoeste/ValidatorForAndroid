
package net.granoeste.validator.sample;

import net.granoeste.validator.Validators;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends Activity {
    ViewHolder mViewHolder;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        mViewHolder = new ViewHolder(getWindow().getDecorView());
    }

    public class ViewHolder {
        Validators mValidators = new Validators();

        public net.granoeste.validator.ValidatableEditText mTextPersonName;
        public net.granoeste.validator.ValidatableEditText mTextPassword;
        public net.granoeste.validator.ValidatableEditText mNumberPassword;
        public net.granoeste.validator.ValidatableEditText mTextEmailAddress;
        public net.granoeste.validator.ValidatableEditText mPhone;
        public net.granoeste.validator.ValidatableEditText mTextPostalAddress;
        public net.granoeste.validator.ValidatableEditText mNumber;
        public net.granoeste.validator.ValidatableEditText mNumberDecimal;
        public net.granoeste.validator.ValidatableEditText mEditDate;
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
            mEditDate = findViewByIdAndCast(v, R.id.editDate);
            mButton = findViewByIdAndCast(v, R.id.button);

            mValidators.put(getApplicationContext(), mTextPersonName);
            mValidators.put(getApplicationContext(), mTextEmailAddress);
            mValidators.put(getApplicationContext(), mTextPassword);
            mValidators.put(getApplicationContext(), mNumberPassword);
            mValidators.put(getApplicationContext(), mPhone);
            mValidators.put(getApplicationContext(), mTextPostalAddress);
            mValidators.put(getApplicationContext(), mNumber);
            mValidators.put(getApplicationContext(), mNumberDecimal);
            mValidators.put(getApplicationContext(), mEditDate);

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
