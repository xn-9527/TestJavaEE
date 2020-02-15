package cn.chay.base.mapper;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by xiaoni on 2020/2/15.
 *
 * 不能放到其他beanMapper的扫描目录，否则会报错tk.mybatis.mapper.MapperException: java.lang.ClassCastException: sun.reflect.generics.reflectiveObjects.TypeVariableImpl cannot be cast to java.lang.Class
 */
public interface BaseMapper<T> extends Mapper<T> {

}
