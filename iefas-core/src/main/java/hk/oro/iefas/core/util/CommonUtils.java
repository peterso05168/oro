package hk.oro.iefas.core.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 3252 $ $Date: 2018-06-21 15:22:55 +0800 (週四, 21 六月 2018) $
 * @author $Author: scott.feng $
 */
@Slf4j
public class CommonUtils {

    public static final String SPACE = StringUtils.SPACE;

    public static final String LF = StringUtils.LF;

    public static final String CR = StringUtils.CR;

    public static final String COLON = ":";

    public static boolean isNotEmpty(final CharSequence cs) {
        return StringUtils.isNotEmpty(cs);
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return StringUtils.isNotEmpty(cs);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return StringUtils.isEmpty(cs);
    }

    public static boolean isBlank(final CharSequence cs) {
        return StringUtils.isBlank(cs);
    }

    public static boolean isEmpty(final Collection<?> coll) {
        return CollectionUtils.isEmpty(coll);
    }

    public static boolean isNotEmpty(final Collection<?> coll) {
        return CollectionUtils.isNotEmpty(coll);
    }

    public static boolean isEmpty(final Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(final long[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(final int[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(final short[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(final char[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(final byte[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(final double[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(final float[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static boolean isEmpty(final boolean[] array) {
        return ArrayUtils.isEmpty(array);
    }

    public static <T> boolean isNotEmpty(final T[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    public static boolean isNotEmpty(final long[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    public static boolean isNotEmpty(final int[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    public static boolean isNotEmpty(final short[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    public static boolean isNotEmpty(final char[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    public static boolean isNotEmpty(final byte[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    public static boolean isNotEmpty(final double[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    public static boolean isNotEmpty(final float[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    public static boolean isNotEmpty(final boolean[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    public static boolean isEmpty(final Map<?, ?> map) {
        return MapUtils.isEmpty(map);
    }

    public static boolean isNotEmpty(final Map<?, ?> map) {
        return MapUtils.isNotEmpty(map);
    }

    public static BigDecimal getBigDecimal(BigDecimal bigDecimal) {
        if (bigDecimal != null) {
            return bigDecimal;
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal add(BigDecimal... numbers) {
        log.info("subtract start - and param : numbers=" + numbers);
        BigDecimal total = BigDecimal.ZERO;
        if (isNotEmpty(numbers)) {
            for (BigDecimal bigDecimal : numbers) {
                if (bigDecimal != null) {
                    total = total.add(bigDecimal);
                }
            }

        }
        log.info("add end - and return : " + total);
        return total;
    }

    public static BigDecimal subtract(BigDecimal head, BigDecimal... numbers) {
        log.info("subtract start - and param : head=" + head + ",numbers=" + numbers);
        BigDecimal total = head == null ? BigDecimal.ZERO : head;
        if (isNotEmpty(numbers)) {
            for (BigDecimal bigDecimal : numbers) {
                if (bigDecimal != null) {
                    total = total.subtract(bigDecimal);
                }
            }

        }
        log.info("subtract end - and return : " + total);
        return total;
    }

    /**
     * 
     * @param isSkipNull if true,then skip the null number;else if false, replaced the null number with ZERO
     * @param numbers multiply numbers
     * @return multiply total
     */
    public static BigDecimal multiply(boolean isSkipNull, BigDecimal... numbers) {
        log.info("multiply start - and param : isSkipNull=" + isSkipNull + ",numbers=" + numbers);
        BigDecimal total = BigDecimal.ZERO;
        if (isNotEmpty(numbers)) {
            total = BigDecimal.ONE;
            for (BigDecimal bigDecimal : numbers) {
                if (isSkipNull) {
                    if (bigDecimal != null) {
                        total = total.multiply(bigDecimal);
                    }
                } else {
                    total = total.multiply(bigDecimal == null ? BigDecimal.ZERO : bigDecimal);
                }

            }
        }
        log.info("multiply end - and return : " + total);
        return total;
    }

}
