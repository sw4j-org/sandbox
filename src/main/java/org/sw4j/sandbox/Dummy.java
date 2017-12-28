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

    public int method(boolean value1, boolean value2) {
        if (value1) {
            return 1;
        } else if (value2) {
            return 2;
        }
        if (value1 && value2) {
            return 3;
        }
        return 1;
    }

}
