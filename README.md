Validator for Android
=========

This is a library of validators for View of Android.
Some classes wrap the apache commons validator.

Usage
---
This library consists of classes and the class to perform them to set the validator to View.

Here is an example of Activity:

    // Validators
    Validators mValidators = new Validators();

    EditText mTextPersonName;
    EditText mTextPassword;
    Button mButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextPersonName = (EditText) findViewById(R.id.textPersonName);
        mTextPassword = (EditText) findViewById(R.id.textPassword);
        mButton = findViewByIdAndCast(v, R.id.button);

        // Define validators for views
        mValidators.put(mTextPersonName, new Validator[] {
                    new RequiredValidator("Be sure to input. "),
                    new MaxLengthValidator(30, "Being allowed is to 30 characters. "),
        });
        mValidators.put(mTextPassword, new Validator[] {
                    new RequiredValidator("Be sure to input. "),
                    new RangeValidator(8, 16, "Being allowed is from 8 to 16 characters. "),
        });

        mButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Execute Validators
                    mValidators.clearError();
                    if (mValidators.isValid()) {
                        Toast.makeText(getApplicationContext(), "It has some errors. ", Toast.LENGTH_SHORT).show();
                    }
                }
            });    
    }

#### Custom Validator
You can create your own Validator.
It can be done by extending the BaseValidator.

    public class CustomValidator extends BaseValidator {
    
        // Argument of the constructor is the error message.
        public CustomValidator(final String errorMessage) {
            super(errorMessage);
        }

        @Override
        protected boolean condition(String value) {
            // Here, you will write a check condition.
            return false;
        }

    }
    
#### ValidatableEditText
You can use ValidatableEditText.
It is extended to EditText, has the validator.

Here is an example of layout.xml

    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:validator="http://schemas.android.com/apk/res-auto/net.granoeste.validator"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <net.granoeste.validator.ValidatableEditText
            android:id="@+id/textPersonName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            validator:message="PersonName is required "
            validator:required="true" />

        <net.granoeste.validator.ValidatableEditText
            android:id="@+id/textPassword"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            validator:label="@string/textPassword"
            validator:minlength="8"
            validator:required="true" />

    </LinearLayout>

Here is an example of Activity:

    // Validators
    Validators mValidators = new Validators();

    net.granoeste.validator.ValidatableEditText mTextPersonName;
    net.granoeste.validator.ValidatableEditText mTextPassword;
    Button mButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextPersonName = (net.granoeste.validator.ValidatableEditText) findViewById(R.id.textPersonName);
        mTextPassword = (net.granoeste.validator.ValidatableEditText) findViewById(R.id.textPassword);
        mButton = findViewByIdAndCast(v, R.id.button);

        // add ValidatableEditText to Validators
        mValidators.put(mTextPersonName);
        mValidators.put(mTextEmailAddress);

        mButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Execute Validators
                    mValidators.clearError();
                    if (mValidators.isValid()) {
                        Toast.makeText(getApplicationContext(), "It has some errors. ", Toast.LENGTH_SHORT).show();
                    }
                }
            });    
    }

License
---------------
* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)


Known
---------------
* Some classes wrap the apache commons validator.
* This uses the Jakarta-ORO
