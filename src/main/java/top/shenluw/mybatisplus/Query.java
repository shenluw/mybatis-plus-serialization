package top.shenluw.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 参考 mybatis plus
 * {@link com.baomidou.mybatisplus.core.conditions.AbstractWrapper}
 *
 * @author Shenluw
 * created: 2020/10/24 14:36
 */
public class Query<T> implements Serializable {
    private static final long serialVersionUID = -2890485966094144099L;

    private String fullClass;

    private List<SqlSegment> segments = new ArrayList<>();

    public static <T> Query<T> of(Class<T> clazz) {
        Query<T> query = new Query<>();
        query.setFullClass(clazz.getName());
        return query;
    }

    public String getFullClass() {
        return fullClass;
    }

    public void setFullClass(String fullClass) {
        this.fullClass = fullClass;
    }

    public List<SqlSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<SqlSegment> segments) {
        this.segments = segments;
    }

    public Query<T> eq(SFunction<T, ?> func, Object value) {
        segments.add(new SqlSegment(Operation.eq, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> ne(SFunction<T, ?> func, Object value) {
        segments.add(new SqlSegment(Operation.ne, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> gt(SFunction<T, ?> func, Object value) {
        segments.add(new SqlSegment(Operation.gt, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> le(SFunction<T, ?> func, Object value) {
        segments.add(new SqlSegment(Operation.le, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> in(SFunction<T, ?> func, Object... value) {
        segments.add(new SqlSegment(Operation.in, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> in(SFunction<T, ?> func, Collection<?> value) {
        segments.add(new SqlSegment(Operation.in, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> notIn(SFunction<T, ?> func, Object... value) {
        segments.add(new SqlSegment(Operation.notIn, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> notIn(SFunction<T, ?> func, Collection<?> value) {
        segments.add(new SqlSegment(Operation.notIn, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> isNull(SFunction<T, ?> func) {
        segments.add(new SqlSegment(Operation.isNull, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName())));
        return this;
    }

    public Query<T> isNotNull(SFunction<T, ?> func) {
        segments.add(new SqlSegment(Operation.isNotNull, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName())));
        return this;
    }

    public Query<T> like(SFunction<T, ?> func, Object value) {
        segments.add(new SqlSegment(Operation.like, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> notLike(SFunction<T, ?> func, Object value) {
        segments.add(new SqlSegment(Operation.notLike, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> ge(SFunction<T, ?> func, Object value) {
        segments.add(new SqlSegment(Operation.ge, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> lt(SFunction<T, ?> func, Object value) {
        segments.add(new SqlSegment(Operation.lt, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> likeLeft(SFunction<T, ?> func, Object value) {
        segments.add(new SqlSegment(Operation.likeLeft, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> likeRight(SFunction<T, ?> func, Object value) {
        segments.add(new SqlSegment(Operation.likeRight, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> exists(SFunction<T, ?> func, String value) {
        segments.add(new SqlSegment(Operation.exists, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> notExists(SFunction<T, ?> func, String value) {
        segments.add(new SqlSegment(Operation.notExists, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

    public Query<T> between(SFunction<T, ?> func, Object v1, Object v2) {
        segments.add(new SqlSegment(Operation.between, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), new Object[]{v1, v2}));
        return this;
    }

    public Query<T> notBetween(SFunction<T, ?> func, Object v1, Object v2) {
        segments.add(new SqlSegment(Operation.notBetween, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), new Object[]{v1, v2}));
        return this;
    }


    public Query<T> or(Query<T> child) {
        segments.add(new SqlSegment(Operation.or, null, child));
        return this;
    }

    public Query<T> and(Query<T> child) {
        segments.add(new SqlSegment(Operation.and, null, child));
        return this;
    }

    /**
     * @param func
     * @param value asc or desc
     * @return
     */
    public Query<T> orderBy(SFunction<T, ?> func, String value) {
        segments.add(new SqlSegment(Operation.orderBy, PropertyNamer.methodToProperty(LambdaUtils.resolve(func).getImplMethodName()), value));
        return this;
    }

}
