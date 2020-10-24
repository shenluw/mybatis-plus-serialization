package top.shenluw.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 参考
 * {@link com.baomidou.mybatisplus.extension.service.IService}
 *
 * @author Shenluw
 * created: 2020/10/24 14:29
 */
public interface CRUDService<T> {
    /**
     * 根据 entity 条件，删除记录
     *
     * @param query 实体包装类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default boolean remove(Query<T> query) {
        return SqlHelper.retBool(getBaseMapper().delete(Queries.toQuery(query)));
    }

    /**
     * 根据 UpdateWrapper 条件，更新记录 需要设置sqlset
     *
     * @param updateQuery 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper}
     */
    default boolean update(Query<T> updateQuery) {
        return update(null, updateQuery);
    }

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity      实体对象
     * @param updateQuery 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper}
     */
    default boolean update(T entity, Query<T> updateQuery) {
        return SqlHelper.retBool(getBaseMapper().update(entity, Queries.toUpdate(updateQuery)));
    }

    /**
     * 根据 Wrapper，查询一条记录
     * <p>结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")</p>
     *
     * @param query 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default T getOne(Query<T> query) {
        return getBaseMapper().selectOne(Queries.toQuery(query));
    }

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param query 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default int count(Query<T> query) {
        return SqlHelper.retCount(getBaseMapper().selectCount(Queries.toQuery(query)));
    }

    /**
     * 查询列表
     *
     * @param query 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default List<T> list(Query<T> query) {
        return getBaseMapper().selectList(Queries.toQuery(query));
    }

    /**
     * 翻页查询
     *
     * @param page  翻页对象
     * @param query 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default <E extends IPage<T>> E page(E page, Query<T> query) {
        return getBaseMapper().selectPage(page, Queries.toQuery(query));
    }

    /**
     * 查询列表
     *
     * @param query 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default List<Map<String, Object>> listMaps(Query<T> query) {
        return getBaseMapper().selectMaps(Queries.toQuery(query));
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param query 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default List<Object> listObjs(Query<T> query) {
        return listObjs(query, Function.identity());
    }

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param query  实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     * @param mapper 转换函数
     */
    default <V> List<V> listObjs(Query<T> query, Function<? super Object, V> mapper) {
        return getBaseMapper().selectObjs(Queries.toQuery(query)).stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    /**
     * 翻页查询
     *
     * @param page  翻页对象
     * @param query 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    default <E extends IPage<Map<String, Object>>> E pageMaps(E page, Query<T> query) {
        return getBaseMapper().selectMapsPage(page, Queries.toQuery(query));
    }

    /**
     * 获取对应 entity 的 BaseMapper
     *
     * @return BaseMapper
     */
    BaseMapper<T> getBaseMapper();
}
