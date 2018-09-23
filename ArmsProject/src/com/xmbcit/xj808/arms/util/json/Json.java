package com.xmbcit.xj808.arms.util.json;

import java.util.HashSet;
import java.util.Set;

/**
 * Json字符串转换类
 */
public class Json implements Cloneable {
    public String name;//对象名称
    public Object value;//指
    public Set<Json> childValue;//数组
    public Object[] objs;//数组

    /**
     * json对象，名称，和值
     *
     * @param name
     * @param obj
     */
    public Json(String name, Object obj) {
        this.name = name;
        this.value = obj;
    }

    /**
     * json对象，名称，和值
     *
     * @param name
     * @param obj
     */
    public Json(String name, Set<Json> obj) {
        this(name);
        this.childValue = obj;
    }

    public Json(String name, Object... value) {
        this(name);
        objs = value;
    }

    public void setChildValue(Set<Json> childValue) {
        this.childValue = childValue;
    }

    /**
     * 创建Json对象
     *
     * @param name
     */
    public Json(String name) {
        this.name = name;
        childValue = new HashSet<>();
    }

    public void setChildValue(Json... jsons) {
        if (childValue == null) {
            childValue = new HashSet<>();
        }
        for (Json j : jsons) {
            childValue.add(j);
        }
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

    public Set<Json> getChildValue() {
        return childValue;
    }

    public Object[] getObjs() {
        return objs;
    }

    public void setObjs(Object[] objs) {
        this.objs = objs;
    }

    /**
     * 根据对象名，获取当前对象下的对象
     *
     * @param name
     * @return
     */
    public Json getChild(String name) {
        if (childValue == null)
            return null;
        for (Json jso : childValue) {
            if (jso.name.equals(name)) {
                return jso;
            }
        }
        return null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return JsonUtil.JsonToString(this);
    }
}
