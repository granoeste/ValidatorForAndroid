
package net.granoeste.validator.test;

import net.granoeste.validator.DateValidator;
import net.granoeste.validator.DoubleRangeValidator;
import net.granoeste.validator.EmailValidator;
import net.granoeste.validator.IntRangeValidator;
import net.granoeste.validator.JapaneseOnebyteKatakanaValidator;
import net.granoeste.validator.JapanesePhoneValidator;
import net.granoeste.validator.JapaneseTwobyteCharValidator;
import net.granoeste.validator.MaxLengthValidator;
import net.granoeste.validator.MinLengthValidator;
import net.granoeste.validator.RangeValidator;
import net.granoeste.validator.RequiredValidator;
import net.granoeste.validator.SplitTokenCountValidator;
import net.granoeste.validator.StringContainValidator;
import net.granoeste.validator.UrlValidator;
import net.granoeste.validator.Validator;
import net.granoeste.validator.Validators;
import net.granoeste.validator.test.ValidatorTest.TestActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.EditText;

public class ValidatorTest extends android.test.ActivityUnitTestCase<TestActivity> {

    public static final class TestActivity extends Activity {

    }

    public ValidatorTest() {
        super(TestActivity.class);
    }

    private Context mContext;
    private Intent mStartIntent;
    private EditText edit;

    private static final String INVALID = "invalid";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mStartIntent = new Intent(Intent.ACTION_MAIN);
        mContext = getInstrumentation().getTargetContext();

        edit = new EditText(mContext);
    }

    @MediumTest
    public void testRequiredValidator() throws Exception {
        startActivity(mStartIntent, null, null);

        final RequiredValidator validator = new RequiredValidator(INVALID);

        // success
        edit.setText("あいうえお");
        assertTrue(validator.isValid(edit));

        // invalid
        edit.setText("");
        assertFalse(validator.isValid(edit));

        edit.setText(null);
        assertFalse(validator.isValid(edit));

        edit.setText("　　　　");
        assertFalse(validator.isValid(edit));

        edit.setText("   ");
        assertFalse(validator.isValid(edit));

        edit.setText("　 　 ");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testRangeValidator() throws Exception {
        final RangeValidator validator = new RangeValidator(
                3, 5, INVALID);

        edit.setText("あいうえお");
        assertTrue(validator.isValid(edit));

        edit.setText("ア　イ　　");
        assertTrue(validator.isValid(edit));

        edit.setText("１ ２ ３");
        assertTrue(validator.isValid(edit));

        edit.setText("ｱｲｳｴｵ");
        assertTrue(validator.isValid(edit));

        edit.setText("12345");
        assertTrue(validator.isValid(edit));

        edit.setText("ラモス");
        assertTrue(validator.isValid(edit));

        edit.setText("ﾗﾓｽ");
        assertTrue(validator.isValid(edit));

        edit.setText("Ramo");
        assertTrue(validator.isValid(edit));

        edit.setText("");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("あいうえおか");
        assertFalse(validator.isValid(edit));

        edit.setText("１ ２ ３ ");
        assertFalse(validator.isValid(edit));

        edit.setText("ｱｲｳｴｵｶ");
        assertFalse(validator.isValid(edit));

        edit.setText("ラモス 瑠偉");
        assertFalse(validator.isValid(edit));

        edit.setText("１２");
        assertFalse(validator.isValid(edit));

        edit.setText("12");
        assertFalse(validator.isValid(edit));

        edit.setText("ｱｱ");
        assertFalse(validator.isValid(edit));

        edit.setText("aa");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testMaxLengthValidator() throws Exception {
        final MaxLengthValidator validator = new MaxLengthValidator(
                5, INVALID);

        edit.setText("あいうえお");
        assertTrue(validator.isValid(edit));

        edit.setText("ア　イ　　");
        assertTrue(validator.isValid(edit));

        edit.setText("１ ２ ３");
        assertTrue(validator.isValid(edit));

        edit.setText("ｱｲｳｴｵ");
        assertTrue(validator.isValid(edit));

        edit.setText("12345");
        assertTrue(validator.isValid(edit));

        edit.setText("ラモス瑠偉");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("あいうえおか");
        assertFalse(validator.isValid(edit));

        edit.setText("１ ２ ３ ");
        assertFalse(validator.isValid(edit));

        edit.setText("ｱｲｳｴｵｶ");
        assertFalse(validator.isValid(edit));

        edit.setText("ラモス 瑠偉");
        assertFalse(validator.isValid(edit));
    }

    @MediumTest
    public void testMinLengthValidator() throws Exception {
        final MinLengthValidator validator = new MinLengthValidator(
                5, INVALID);

        edit.setText("あいうえお");
        assertTrue(validator.isValid(edit));

        edit.setText("ア　イ　　");
        assertTrue(validator.isValid(edit));

        edit.setText("１ ２ ３");
        assertTrue(validator.isValid(edit));

        edit.setText("ｱｲｳｴｵ");
        assertTrue(validator.isValid(edit));

        edit.setText("12345");
        assertTrue(validator.isValid(edit));

        edit.setText("あいうえおか");
        assertTrue(validator.isValid(edit));

        edit.setText("１ ２ ３ ");
        assertTrue(validator.isValid(edit));

        edit.setText("ｱｲｳｴｵｶ");
        assertTrue(validator.isValid(edit));

        edit.setText("ラモス 瑠偉");
        assertTrue(validator.isValid(edit));

        edit.setText("");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("ラモス");
        assertFalse(validator.isValid(edit));

        edit.setText("ﾗﾓｽ");
        assertFalse(validator.isValid(edit));

        edit.setText("Ramo");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testUrlValidator() throws Exception {
        final UrlValidator validator = new UrlValidator(INVALID);

        edit.setText("http://google.com");
        assertTrue(validator.isValid(edit));

        edit.setText("http://google.jp");
        assertTrue(validator.isValid(edit));

        edit.setText("http://google.co.jp");
        assertTrue(validator.isValid(edit));

        edit.setText("http://goo.gl");
        assertTrue(validator.isValid(edit));

        edit.setText("https://goo.ch");
        assertTrue(validator.isValid(edit));

        edit.setText("ftp://mame.zoo");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("abc-abc-abc");
        assertFalse(validator.isValid(edit));

        edit.setText("mame.zoo");
        assertFalse(validator.isValid(edit));

        edit.setText("mailto://mame.zoo");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testEmailValidator() throws Exception {
        final EmailValidator validator = new EmailValidator(INVALID);

        edit.setText("hogehoge@mail.com");
        assertTrue(validator.isValid(edit));

        edit.setText("hogehoge@mail.jp");
        assertTrue(validator.isValid(edit));

        edit.setText("hogehoge@mail.co.jp");
        assertTrue(validator.isValid(edit));

        edit.setText("hogegoge@mail.me");
        assertTrue(validator.isValid(edit));

        edit.setText("hogegoge123@mail.me");
        assertTrue(validator.isValid(edit));

        edit.setText("hoge.goge@mail.me");
        assertTrue(validator.isValid(edit));

        edit.setText("hoge+goge@mail.me");
        assertTrue(validator.isValid(edit));

        edit.setText("hoge-goge@mail.me");
        assertTrue(validator.isValid(edit));

        edit.setText("hoge_goge@mail.me");
        assertTrue(validator.isValid(edit));

        edit.setText("123hoge@mail.me");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("hogehoge@mail");
        assertFalse(validator.isValid(edit));

        edit.setText("@mail");
        assertFalse(validator.isValid(edit));

        edit.setText("@mail.com");
        assertFalse(validator.isValid(edit));

        edit.setText("abc-abc-abc");
        assertFalse(validator.isValid(edit));

        edit.setText("mame.zoo");
        assertFalse(validator.isValid(edit));

        edit.setText("mailto://mame.zoo");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testIntRangeValidator() throws Exception {
        final IntRangeValidator validator = new IntRangeValidator(
                1, 10, INVALID);

        edit.setText("1");
        assertTrue(validator.isValid(edit));

        edit.setText("10");
        assertTrue(validator.isValid(edit));

        edit.setText("5");
        assertTrue(validator.isValid(edit));

        edit.setText("007");
        assertTrue(validator.isValid(edit));

        edit.setText("");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("1.1");
        assertFalse(validator.isValid(edit));

        edit.setText("9.5");
        assertFalse(validator.isValid(edit));

        edit.setText("0");
        assertFalse(validator.isValid(edit));

        edit.setText("11");
        assertFalse(validator.isValid(edit));

        edit.setText("-1");
        assertFalse(validator.isValid(edit));

        edit.setText("ABC");
        assertFalse(validator.isValid(edit));

        edit.setText("１２３");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testNumberFormatIntRangeValidator() throws Exception {
        final IntRangeValidator validator = new IntRangeValidator(
                1000, 5000000, INVALID);

        edit.setText("1,000");
        assertTrue(validator.isValid(edit));

        edit.setText("5,000");
        assertTrue(validator.isValid(edit));

        edit.setText("1,000,000");
        assertTrue(validator.isValid(edit));

        edit.setText("5,000,000");
        assertTrue(validator.isValid(edit));

        // invalid
        edit.setText("100");
        assertFalse(validator.isValid(edit));

        edit.setText("5,000,001");
        assertFalse(validator.isValid(edit));

        edit.setText("5,100,000");
        assertFalse(validator.isValid(edit));

        edit.setText("999,000,000");
        assertFalse(validator.isValid(edit));

        // irregular

        edit.setText("1000,000");
        assertTrue(validator.isValid(edit));

        edit.setText("5000,000");
        assertTrue(validator.isValid(edit));

        edit.setText("500,0000");
        assertTrue(validator.isValid(edit));

        edit.setText("900,0000");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testDoubleRangeValidator() throws Exception {
        final DoubleRangeValidator validator = new DoubleRangeValidator(
                0.01d, 11.89d, INVALID);

        edit.setText("0.01");
        assertTrue(validator.isValid(edit));

        edit.setText("11.89");
        assertTrue(validator.isValid(edit));

        edit.setText("1");
        assertTrue(validator.isValid(edit));

        edit.setText("0.1");
        assertTrue(validator.isValid(edit));

        edit.setText("11.0");
        assertTrue(validator.isValid(edit));

        edit.setText("0.011");
        assertTrue(validator.isValid(edit));

        edit.setText("");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("-0.1");
        assertFalse(validator.isValid(edit));

        edit.setText("0.001");
        assertFalse(validator.isValid(edit));

        edit.setText("11.90");
        assertFalse(validator.isValid(edit));

        edit.setText("11.899");
        assertFalse(validator.isValid(edit));

        edit.setText("0.009");
        assertFalse(validator.isValid(edit));

        edit.setText("ABC");
        assertFalse(validator.isValid(edit));

        edit.setText("１２３");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testNumberFormatDoubleRangeValidator() throws Exception {
        final DoubleRangeValidator validator = new DoubleRangeValidator(
                0.10d, 5000000.89d, INVALID);

        edit.setText("1,000.01");
        assertTrue(validator.isValid(edit));

        edit.setText("1,100.89");
        assertTrue(validator.isValid(edit));

        edit.setText("1,000");
        assertTrue(validator.isValid(edit));

        edit.setText("1,000.1");
        assertTrue(validator.isValid(edit));

        edit.setText("1,100.0");
        assertTrue(validator.isValid(edit));

        edit.setText("1,000.011");
        assertTrue(validator.isValid(edit));

        // invalid
        edit.setText("0.01");
        assertFalse(validator.isValid(edit));

        edit.setText("500,0000.891");
        assertFalse(validator.isValid(edit));

        edit.setText("500,0000.9");
        assertFalse(validator.isValid(edit));

        edit.setText("900,0000.999");
        assertFalse(validator.isValid(edit));

        // irregular

        edit.setText("1000,000.88");
        assertTrue(validator.isValid(edit));

        edit.setText("5000,000.88");
        assertTrue(validator.isValid(edit));

        edit.setText("500,0000.88");
        assertTrue(validator.isValid(edit));

        edit.setText("900,0000.88");
        assertFalse(validator.isValid(edit));
    }

    @MediumTest
    public void testDateValidator() {
        final DateValidator validator = new DateValidator(INVALID);

        edit.setText("1970/01/01");
        assertTrue(validator.isValid(edit));

        edit.setText("2020/12/31");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("45/13/13");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testJapaneseOnebyteKatakanaValidator() {
        final JapaneseOnebyteKatakanaValidator validator = new JapaneseOnebyteKatakanaValidator(
                INVALID);

        edit.setText("ｱｲｳｴｵ");
        assertTrue(validator.isValid(edit));

        edit.setText("ﾀﾅｶ ﾏﾙｸｽ ﾄｩｰﾘｵ");
        assertTrue(validator.isValid(edit));

        edit.setText("ｶﾞｷﾞｸﾞｹﾞｺﾞ");
        assertTrue(validator.isValid(edit));

        edit.setText("ﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟ");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("田中・マルクス　トゥーリオ　Ⅳ世");
        assertFalse(validator.isValid(edit));

        edit.setText("12345");
        assertFalse(validator.isValid(edit));

        edit.setText("abcdefg");
        assertFalse(validator.isValid(edit));

        edit.setText("ラモス 瑠偉");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testJapaneseTwobyteCharValidator() {
        final JapaneseTwobyteCharValidator validator = new JapaneseTwobyteCharValidator(
                INVALID);

        edit.setText("あいうえお");
        assertTrue(validator.isValid(edit));

        edit.setText("アイウエオ");
        assertTrue(validator.isValid(edit));

        edit.setText("１２３４５６７８９０");
        assertTrue(validator.isValid(edit));

        edit.setText("ＡＢＣＤＥＦ");
        assertTrue(validator.isValid(edit));

        edit.setText("αβγ");
        assertTrue(validator.isValid(edit));

        edit.setText("壱弐参四伍六七八九零");
        assertTrue(validator.isValid(edit));

        edit.setText("！”＃＄％＆’（）〜＝〜｜＿？＞＜』＊＋『｀ー＾");
        assertTrue(validator.isValid(edit));

        edit.setText("田中・マルクス　トゥーリオ　Ⅳ世");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("ｱｲｳｴｵ");
        assertFalse(validator.isValid(edit));

        edit.setText("12345");
        assertFalse(validator.isValid(edit));

        edit.setText("abcdefg");
        assertFalse(validator.isValid(edit));

        edit.setText("ラモス 瑠偉");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testJapanesePhoneValidator() {
        final JapanesePhoneValidator validator = new JapanesePhoneValidator(
                INVALID);

        // 国内プレフィックス:0
        // 市外局番  : 1 - 4桁 <--+-- 合計5桁
        // 市内局番  : 1 - 4桁 <--+
        // 加入者番号: 4桁
        //
        // 0A-BCDE-1234
        // 0AB-CDE-1234
        // 0ABC-DE-1234
        // 0ABCD-E-1234
        // 090-1234-1234

        edit.setText("01-2345-1234");
        assertTrue(validator.isValid(edit));

        edit.setText("012-345-1234");
        assertTrue(validator.isValid(edit));

        edit.setText("0123-45-1234");
        assertTrue(validator.isValid(edit));

        //岐阜県高山市荘川町大野郡 5769 E 9999
        edit.setText("05769-6-1013");
        assertTrue(validator.isValid(edit));

        // 携帯電話
        edit.setText("090-1234-1234");
        assertTrue(validator.isValid(edit));

        edit.setText("0120-444-444");
        assertTrue(validator.isValid(edit));

        edit.setText("0123-00-2222");
        assertTrue(validator.isValid(edit));

        edit.setText("1234-444-444");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("abc-abc-abc");
        assertFalse(validator.isValid(edit));

        edit.setText("0123444555");
        assertFalse(validator.isValid(edit));

    }

    @MediumTest
    public void testSplitTokenCountValidator() {
        final int ge = SplitTokenCountValidator.GE;
        final int gt = SplitTokenCountValidator.GT;
        final int le = SplitTokenCountValidator.LE;
        final int lt = SplitTokenCountValidator.LT;
        final int ne = SplitTokenCountValidator.NE;

        final String regex = ",";
        final int count = 3;

        SplitTokenCountValidator validator = null;

        // EQ
        validator = new SplitTokenCountValidator(regex, count, INVALID);

        edit.setText("abc,def,1.23");
        assertTrue(validator.isValid(edit));

        // invalid

        edit.setText("abc.abc.abc");
        assertFalse(validator.isValid(edit));

        edit.setText("abc,abc");
        assertFalse(validator.isValid(edit));

        edit.setText("abc,abc,abc,abc");
        assertFalse(validator.isValid(edit));

        // GE
        validator = new SplitTokenCountValidator(regex, count, ge, INVALID);

        edit.setText("abc,def,ghi");
        assertTrue(validator.isValid(edit));

        edit.setText("abc,def,ghi,jkl");
        assertTrue(validator.isValid(edit));

        edit.setText("abc,def");
        assertFalse(validator.isValid(edit));

        // GT
        validator = new SplitTokenCountValidator(regex, count, gt, INVALID);

        edit.setText("abc,def,ghi");
        assertFalse(validator.isValid(edit));

        edit.setText("abc,def,ghi,jkl");
        assertTrue(validator.isValid(edit));

        edit.setText("abc,def");
        assertFalse(validator.isValid(edit));

        // LE
        validator = new SplitTokenCountValidator(regex, count, le, INVALID);

        edit.setText("abc,def,ghi");
        assertTrue(validator.isValid(edit));

        edit.setText("abc,def,ghi,jkl");
        assertFalse(validator.isValid(edit));

        edit.setText("abc,def");
        assertTrue(validator.isValid(edit));

        // LT
        validator = new SplitTokenCountValidator(regex, count, lt, INVALID);

        edit.setText("abc,def,ghi");
        assertFalse(validator.isValid(edit));

        edit.setText("abc,def,ghi,jkl");
        assertFalse(validator.isValid(edit));

        edit.setText("abc,def");
        assertTrue(validator.isValid(edit));

        // NE
        validator = new SplitTokenCountValidator(regex, count, ne, INVALID);

        edit.setText("abc,def,ghi");
        assertFalse(validator.isValid(edit));

        edit.setText("abc,def,ghi,jkl");
        assertTrue(validator.isValid(edit));

        edit.setText("abc,def");
        assertTrue(validator.isValid(edit));

    }

    @MediumTest
    public void testStringContainValidator() {
        final String[] elements = {
                "abc", "def", "ghi"
        };
        StringContainValidator validator;
        validator = new StringContainValidator(elements, INVALID);

        edit.setText("xyzabc123def");
        assertTrue(validator.isValid(edit));

        edit.setText("xyz123");
        assertFalse(validator.isValid(edit));

        validator = new StringContainValidator("+,-,*,/,@", INVALID);

        edit.setText("p@ssw*rd");
        assertTrue(validator.isValid(edit));

        edit.setText("password");
        assertFalse(validator.isValid(edit));

        final char[] charElements = new char[] {
                '1', '+', '-', '*', '/', '@'
        };
        validator = new StringContainValidator(charElements, 4, INVALID);

        edit.setText("p@ssw*rd");
        assertFalse(validator.isValid(edit));

        edit.setText("password");
        assertFalse(validator.isValid(edit));

        edit.setText("p@++w*rd");
        assertTrue(validator.isValid(edit));

        edit.setText("p@++w*r/");
        assertTrue(validator.isValid(edit));

    }

    @MediumTest
    public void testCombination() {
        final EditText name = new EditText(mContext);
        final EditText age = new EditText(mContext);
        final EditText phone = new EditText(mContext);

        final Validators validators = new Validators();
        validators.put(name, new Validator[] {
                new RequiredValidator("RequiredValidator"),
                new JapaneseTwobyteCharValidator("JapaneseTwobyteCharValidator"),
        });
        validators.put(age, new Validator[] {
                new RequiredValidator("RequiredValidator"),
                new IntRangeValidator(18, 100, "IntRangeValidator"),
        });
        validators.put(phone, new Validator[] {
                new JapanesePhoneValidator("JapanesePhoneValidator"),
        });

        name.setText("山田太郎");
        age.setText("21");
        phone.setText("090-1234-1234");

        validators.clearError();
        validators.isValid();

        assertNull(name.getError());
        assertNull(age.getError());
        assertNull(phone.getError());

        name.setText("YAMADA");
        age.setText("10");
        phone.setText("AAA-AAA-1234");

        validators.clearError();
        validators.isValid();

        assertNotNull(name.getError());
        assertNotNull(age.getError());
        assertNotNull(phone.getError());

        name.setText(null);
        age.setText("");
        phone.setText("");

        validators.clearError();
        validators.isValid();

        assertNotNull(name.getError());
        assertNotNull(age.getError());
        assertNull(phone.getError());

    }

}
