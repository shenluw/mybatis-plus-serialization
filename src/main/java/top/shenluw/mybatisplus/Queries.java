package top.shenluw.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static top.shenluw.mybatisplus.Operation.ORDER_ASC;

/**
 * Query -> Wrapper 辅助转换函数
 *
 * @author Shenluw
 * created: 2020/10/24 14:36
 */
public abstract class Queries {

    private static Map<Class<?>, Map<String, ColumnCache>> allColumnMap = new HashMap<>();

    private static void cache(Class<?> clazz) {
        if (!allColumnMap.containsKey(clazz)) {
            Map<String, ColumnCache> columnMap = LambdaUtils.getColumnMap(clazz);
            allColumnMap.put(clazz, columnMap);
        }
    }

    private static String getRealColumn(Class<?> clazz, String fieldName) {
        Map<String, ColumnCache> columnMap = allColumnMap.get(clazz);
        if (columnMap == null) {
            return fieldName;
        }
        ColumnCache columnCache = columnMap.get(LambdaUtils.formatKey(fieldName));
        Assert.notNull(columnCache, "can not find lambda cache for this property [%s] of entity [%s]",
                fieldName, clazz.getName());
        return columnCache.getColumn();
    }

    private static Class<?> loadClass(Query<?> query) {
        try {
            return Class.forName(query.getFullClass());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("query class not found", e);
        }
    }

    public static <T> QueryWrapper<T> toQuery(Query<T> query) {
        Class<?> clazz = loadClass(query);
        cache(clazz);
        QueryWrapper<T> wrapper = Wrappers.query();
        to(wrapper, query, clazz);
        return wrapper;
    }

    public static <T> UpdateWrapper<T> toUpdate(Query<T> query) {
        Class<?> clazz = loadClass(query);
        cache(clazz);
        UpdateWrapper<T> wrapper = Wrappers.update();
        to(wrapper, query, clazz);
        return wrapper;
    }


    private static <T> void to(AbstractWrapper wrapper, Query<T> query, Class<?> clazz) {
        List<SqlSegment> segments = query.getSegments();

        for (SqlSegment segment : segments) {
            apply(wrapper, segment, clazz);
        }
    }

    private static void apply(AbstractWrapper wrapper, SqlSegment segment, Class<?> clazz) {
        Operation operation = segment.getOperation();

        Object value = segment.getValue();
        String name  = segment.getName();
        switch (operation) {
            case eq:
                wrapper.eq(getRealColumn(clazz, name), value);
                break;
            case ne:
                wrapper.ne(getRealColumn(clazz, name), value);
                break;
            case in:
                wrapper.in(getRealColumn(clazz, name), value);
                break;
            case notIn:
                wrapper.notIn(getRealColumn(clazz, name), value);
                break;

            case like:
                wrapper.like(getRealColumn(clazz, name), value);
                break;
            case notLike:
                wrapper.notLike(getRealColumn(clazz, name), value);
                break;
            case gt:
                wrapper.gt(getRealColumn(clazz, name), value);
                break;
            case ge:
                wrapper.ge(getRealColumn(clazz, name), value);
                break;
            case lt:
                wrapper.lt(getRealColumn(clazz, name), value);
                break;
            case le:
                wrapper.le(getRealColumn(clazz, name), value);
                break;
            case likeLeft:
                wrapper.likeLeft(getRealColumn(clazz, name), value);
                break;
            case likeRight:
                wrapper.likeRight(getRealColumn(clazz, name), value);
                break;
            case between:
                wrapper.between(getRealColumn(clazz, name), ((Object[]) value)[0], ((Object[]) value)[1]);
                break;
            case notBetween:
                wrapper.notBetween(getRealColumn(clazz, name), ((Object[]) value)[0], ((Object[]) value)[1]);
                break;
            case or:
                wrapper.or((Consumer<AbstractWrapper>) o -> to(o, (Query) value, clazz));
                break;
            case and:
                wrapper.and((Consumer<AbstractWrapper>) o -> to(o, (Query) value, clazz));
                break;
            case exists:
                wrapper.exists(getRealColumn(clazz, name));
                break;
            case notExists:
                wrapper.notExists(getRealColumn(clazz, name));
                break;
            case isNull:
                wrapper.isNull(getRealColumn(clazz, name));
                break;
            case isNotNull:
                wrapper.isNotNull(getRealColumn(clazz, name));
                break;
            case orderBy:
                wrapper.orderBy(true, ORDER_ASC.equalsIgnoreCase(value.toString()), getRealColumn(clazz, name));
                break;
            default:
                throw new IllegalArgumentException("not support operation: " + operation);
        }
    }
}
