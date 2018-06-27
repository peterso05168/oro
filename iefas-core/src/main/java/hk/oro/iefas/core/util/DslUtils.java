/**
 * 
 */
package hk.oro.iefas.core.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.EntityPathBase;

import javassist.Modifier;
import lombok.extern.slf4j.Slf4j;

/**
 * @version $Revision: 1974 $ $Date: 2018-04-09 18:41:28 +0800 (週一, 09 四月 2018) $
 * @author $Author: marvel.ma $
 */
@Slf4j
public class DslUtils {

    @SuppressWarnings({"rawtypes"})
    public static <T extends EntityPathBase<?>> Expression[] getAllExpression(T qEntity) {

        List<Expression<T>> columns = getAllExpressionList(qEntity);

        Expression[] array = columns.toArray(new Expression[columns.size()]);

        return array;
    }

    @SuppressWarnings({"unchecked"})
    public static <T extends EntityPathBase<?>> List<Expression<T>> getAllExpressionList(T qEntity) {

        final List<Expression<T>> columns = Arrays.stream(qEntity.getClass().getDeclaredFields())
            .filter(field -> !Modifier.isStatic(field.getModifiers())).map(field -> {
                try {
                    return (Expression<T>)field.get(qEntity);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.error(e.getMessage(), e);
                }
                return null;
            }).collect(Collectors.toList());

        return columns;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T extends EntityPathBase<?>> Expression[] addExpression(Expression[] expressions,
        Path<String>... newExpression) {
        return ArrayUtils.addAll(expressions, newExpression);
    }

}
