package org.sw4j.sandbox;

/**
 * A dummy class in the sandbox to test GitHub integration.
 *
 * @author Uwe Plonus &lt;u.plonus@gmail.com&gt;
 */
public class Dummy {

    /**
     * Default constructor.
     */
    public Dummy() {
    }

    /**
     * A test method.
     * @param value1 a boolean value.
     * @param value2 another boolean value.
     * @return an integer.
     */
    public int method(final boolean value1, final boolean value2) {
        if (value1) {
            if (value2) {
                return 3;
            }
            return 1;
        } else if (value2) {
            return 2;
        }
        return 0;
    }

}
