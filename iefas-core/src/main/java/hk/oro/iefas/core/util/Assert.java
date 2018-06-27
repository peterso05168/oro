/**
 * 
 */
package hk.oro.iefas.core.util;

/**
 * @version $Revision: 3145 $ $Date: 2018-06-14 18:24:19 +0800 (週四, 14 六月 2018) $
 * @author $Author: marvel.ma $
 */
public class Assert {

    protected Assert() {}

    static public void assertTrue(String message, boolean condition) {
        if (!condition) {
            fail(message);
        }
    }

    static public void assertTrue(boolean condition) {
        assertTrue(null, condition);
    }

    static public void assertFalse(String message, boolean condition) {
        assertTrue(message, !condition);
    }

    static public void assertFalse(boolean condition) {
        assertFalse(null, condition);
    }

    static public void fail(String message) {
        if (message == null) {
            throw new AssertionError();
        }
        throw new AssertionError(message);
    }

    static public void assertNotNull(String message, Object object) {
        assertTrue(message, object != null);
    }

    static public void assertNotNull(Object object) {
        assertNotNull(null, object);
    }

    static public void assertNull(String message, Object object) {
        if (object == null) {
            return;
        }
        failNotNull(message, object);
    }

    static public void assertNull(Object object) {
        assertNull(null, object);
    }

    static private void failNotNull(String message, Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }
        fail(formatted + "expected null, but was:<" + actual + ">");
    }

}
