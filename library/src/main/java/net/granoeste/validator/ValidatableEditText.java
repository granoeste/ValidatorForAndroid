
package net.granoeste.validator;

import java.util.regex.PatternSyntaxException;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

public class ValidatableEditText extends EditText {

    public ValidatableEditText(final Context context) {
        this(context, null);
    }

    public ValidatableEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ValidatableEditText(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    private boolean mRequired;
    private int mMinlength;
    private int mMaxlength;
    private String mMask;
    private boolean mIsInteger;
    private boolean mIsDouble;
    private boolean mIsDate;
    private String mDateFormat;
    private boolean mRange;
    private int mRangeFrom;
    private int mRangeTo;
    private boolean mIntRange;
    private int mIntRangeFrom;
    private int mIntRangeTo;
    private boolean mDoubleRange;
    private double mDoubleRangeFrom;
    private double mDoubleRangeTo;
    private boolean mIsEmail;
    private boolean mIsUrl;
    private String mLabel;
    private String mMessage;

    private void initView(final Context context, final AttributeSet attrs) {
        if (attrs != null) {
            for (int i = 0; i < attrs.getAttributeCount(); i++) {
                Log.v("ValidatableEditText",
                        attrs.getAttributeName(i) + "=" + attrs.getAttributeValue(i));
            }

            final TypedArray array = context.obtainStyledAttributes(attrs,
                    R.styleable.ValidatableEditText);
            mRequired = array.getBoolean(R.styleable.ValidatableEditText_required, false);
            mMaxlength = array.getInt(R.styleable.ValidatableEditText_maxlength, -1);
            mMinlength = array.getInt(R.styleable.ValidatableEditText_minlength, -1);
            mMask = array.getString(R.styleable.ValidatableEditText_mask);
            mIsInteger = array.getBoolean(R.styleable.ValidatableEditText_isInteger, false);
            mIsDouble = array.getBoolean(R.styleable.ValidatableEditText_isDouble, false);
            mDateFormat = array.getString(R.styleable.ValidatableEditText_isDate);
            if (mDateFormat != null) {
                mIsDate = true;
            }
            final String range = array.getString(R.styleable.ValidatableEditText_range);
            if (range != null) {
                try {
                    final String[] values = range.split(",");
                    mRangeFrom = Integer.parseInt(values[0]);
                    mRangeTo = Integer.parseInt(values[1]);
                    mRange = true;
                } catch (final ArrayIndexOutOfBoundsException e) {
                } catch (final NumberFormatException e) {
                } catch (final PatternSyntaxException e) {
                }
            }
            final String intRange = array.getString(R.styleable.ValidatableEditText_intRange);
            if (intRange != null) {
                try {
                    final String[] values = intRange.split(",");
                    mIntRangeFrom = Integer.parseInt(values[0]);
                    mIntRangeTo = Integer.parseInt(values[1]);
                    mIntRange = true;
                } catch (final ArrayIndexOutOfBoundsException e) {
                } catch (final NumberFormatException e) {
                } catch (final PatternSyntaxException e) {
                }
            }
            final String doubleRange = array.getString(R.styleable.ValidatableEditText_doubleRange);
            if (doubleRange != null) {
                try {
                    final String[] values = doubleRange.split(",");
                    mDoubleRangeFrom = Double.parseDouble(values[0]);
                    mDoubleRangeTo = Double.parseDouble(values[1]);
                    mDoubleRange = true;
                } catch (final ArrayIndexOutOfBoundsException e) {
                } catch (final NumberFormatException e) {
                } catch (final PatternSyntaxException e) {
                }
            }
            mIsEmail = array.getBoolean(R.styleable.ValidatableEditText_isEmail, false);
            mIsUrl = array.getBoolean(R.styleable.ValidatableEditText_isUrl, false);

            final int idMsg = array.getResourceId(R.styleable.ValidatableEditText_message, -1);
            if (idMsg != -1) {
                mMessage = getResources().getString(idMsg);
            } else {
                mMessage = array.getNonResourceString(R.styleable.ValidatableEditText_message);
            }

            final int id = array.getResourceId(R.styleable.ValidatableEditText_label, -1);
            if (id != -1) {
                mLabel = getResources().getString(id);
            } else {
                mLabel = array.getNonResourceString(R.styleable.ValidatableEditText_label);
            }

            array.recycle();
        }
    }

    public boolean isRequired() {
        return mRequired;
    }

    public int getMinlength() {
        return mMinlength;
    }

    public int getMaxlength() {
        return mMaxlength;
    }

    public String getMask() {
        return mMask;
    }

    public boolean isInteger() {
        return mIsInteger;
    }

    public boolean isDouble() {
        return mIsDouble;
    }

    public boolean isDate() {
        return mIsDate;
    }

    public String getDateFormat() {
        return mDateFormat;
    }

    public boolean isRange() {
        return mRange;
    }

    public int getRangeFrom() {
        return mRangeFrom;
    }

    public int getRangeTo() {
        return mRangeTo;
    }

    public boolean isIntRange() {
        return mIntRange;
    }

    public int getIntRangeFrom() {
        return mIntRangeFrom;
    }

    public int getIntRangeTo() {
        return mIntRangeTo;
    }

    public boolean isDoubleRange() {
        return mDoubleRange;
    }

    public double getDoubleRangeFrom() {
        return mDoubleRangeFrom;
    }

    public double getDoubleRangeTo() {
        return mDoubleRangeTo;
    }

    public boolean isEmail() {
        return mIsEmail;
    }

    public boolean isUrl() {
        return mIsUrl;
    }

    public String getLabel() {
        return mLabel;
    }

    public String getMessage() {
        return mMessage;
    }

    public boolean hasMessage() {
        return (mMessage != null);
    }

}
