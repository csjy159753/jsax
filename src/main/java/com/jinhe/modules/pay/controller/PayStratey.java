package com.jinhe.modules.pay.controller;

import com.google.common.collect.Maps;
import com.jinhe.common.utils.ClassScaner;
import com.jinhe.modules.pay.annotation.PayType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
@Lazy(true)
public class PayStratey implements BeanFactoryPostProcessor {
   static HashMap<String, Class> payTypeHashMap = Maps.newHashMapWithExpectedSize(2);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        Set<Class<?>> scan = ClassScaner.scan("com.jinhe.modules.pay.service.impl", PayType.class);
        for (Class<?> c:scan) {
            PayType annotation = c.getAnnotation(PayType.class);
            payTypeHashMap.put(annotation.value(),c);
        }
    }
}
