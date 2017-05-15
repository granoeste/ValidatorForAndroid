
package net.granoeste.InputFilter;

import java.util.regex.Pattern;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

public class MaskInputFilter implements InputFilter {
    private final Pattern mPattern;

    public MaskInputFilter(String mask) {
        super();
        mPattern = Pattern.compile(mask);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
            int dend) {

        String destStr = dest.toString();
        String newValue = destStr.substring(0, dstart) + source + destStr.substring(dend);

        if (!TextUtils.isEmpty(newValue) && !mPattern.matcher(newValue).find()) {
            return "";
        }

        return source;
    }
}
