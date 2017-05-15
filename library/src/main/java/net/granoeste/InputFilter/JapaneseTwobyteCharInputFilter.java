
package net.granoeste.InputFilter;

public class JapaneseTwobyteCharInputFilter extends MaskInputFilter {

    public JapaneseTwobyteCharInputFilter() {
        super("^[^ -~｡-ﾟ]+$");
    }
}
