package top.shenluw.mybatisplus;

import java.io.Serializable;

/**
 * sql片段
 * {@link com.baomidou.mybatisplus.core.conditions.ISqlSegment}
 *
 * @author Shenluw
 * created: 2020/10/24 14:36
 */
public class SqlSegment implements Serializable {
    private static final long serialVersionUID = -1561380630990933604L;

    /**
     * 字段名称
     */
    private String    name;
    /**
     * 目标值
     */
    private Object    value;
    /**
     * 操作符
     * eq  in  or ...
     */
    private Operation operation;

    public SqlSegment() {
    }

    public SqlSegment(Operation operation, String name) {
        this.operation = operation;
    }

    public SqlSegment(Operation operation, String name, Object value) {
        this.name = name;
        this.value = value;
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
