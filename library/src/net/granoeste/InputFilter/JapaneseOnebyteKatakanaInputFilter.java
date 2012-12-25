
package net.granoeste.InputFilter;

public class JapaneseOnebyteKatakanaInputFilter extends MaskInputFilter {

    public JapaneseOnebyteKatakanaInputFilter() {
        super("^[\\uFF65-\\uFF9F\\s-]+$");
    }
}
