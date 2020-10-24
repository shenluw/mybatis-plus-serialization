package top.shenluw.mybatisplus;

/**
 * sql操作符号枚举
 * <p>
 * {@link com.baomidou.mybatisplus.core.enums.SqlKeyword}
 *
 * @author Shenluw
 * created: 2020/10/24 14:37
 */
public enum Operation {
    eq, ne,
    in, notIn,
    like, notLike,
    gt, ge,
    lt, le,
    likeLeft, likeRight,
    between, notBetween,
    exists, notExists,
    isNull, isNotNull,
    /**
     * 特殊处理
     */
    orderBy,
    and, or;

    public static final String ORDER_ASC  = "asc";
    public static final String ORDER_DESC = "desc";
}
