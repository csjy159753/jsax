package com.jinhe.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @author hongsir 2017-11-03 19:36
 * @apiNote spring上下文工具类，用于普通类调用springIOC中的对象
 */

//如果有报ApplicationContext空指针，则可能原因是没加载之前就往下走了，要在要 使用的类 前面加
//@DependsOn("springContextUtils")
@Component
public class SpringContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (SpringContextUtils.applicationContext == null) {
			System.out.println("------------------contex---------");
			SpringContextUtils.applicationContext = applicationContext;
		}
	}

	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}


	/**
	 * @apiNote 获取applicationContext
	 * @author hongsir 2017/11/3 19:40:00
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}

	/**
	 * @apiNote 通过name获取 Bean.
	 * @author hongsir 2017/11/3 19:39:00
	 */
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	/**
	 * @apiNote 通过class获取Bean.
	 * @author hongsir 2017/11/3 19:39:00
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * @apiNote 通过name, 以及Clazz返回指定的Bean
	 * @author hongsir 2017/11/3 19:39:00
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}
}

