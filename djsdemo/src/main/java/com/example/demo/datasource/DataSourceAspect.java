package com.example.demo.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class DataSourceAspect {
    public void pointCut() {
    }

    public void intercept(JoinPoint point) {
        //获取切点处的类或接口信息
        Class<?> target = point.getTarget().getClass();
        //初始化方法签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());
    }
    /**
     * 提取目标对象方法注解和类型注解中的数据源标识
     *
     * @param clazz
     * @param method
     */
    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource dataSource = clazz.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.setDataSource(dataSource.value());
            }
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource dataSource = m.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.setDataSource(dataSource.value());
            }
        } catch (Exception e) {
            System.out.println(clazz + ":" + e.getMessage());
        }
    }
}
