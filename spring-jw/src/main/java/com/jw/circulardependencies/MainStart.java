package com.jw.circulardependencies;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cjw
 * @Description:
 * @date 2021/5/17 17:11
 */
public class MainStart {
	private static Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

	public static Map<String,Object> singletonObjects = new ConcurrentHashMap<>();

	/**
	 * 读取bean定义，在spring中肯定是根据配置 动态扫描注册
	 */
	public static void loadBeanDefinitions(){
		RootBeanDefinition beanDefinitionA = new RootBeanDefinition(InstanceA.class);
		RootBeanDefinition beanDefinitionB = new RootBeanDefinition(InstanceB.class);
		beanDefinitionMap.put("instanceA",beanDefinitionA);
		beanDefinitionMap.put("instanceB",beanDefinitionB);
	}
	public static void main(String[] args) throws Exception {
		//加载beanDefinition
		loadBeanDefinitions();
		for(String key: beanDefinitionMap.keySet()){
				getBean(key);
		}
	}
	public static Object getBean(String beanName) throws Exception {
		RootBeanDefinition beanDefinition = (RootBeanDefinition) beanDefinitionMap.get(beanName);
		//实例化
		Class<?> beanClass = beanDefinition.getBeanClass();
		Object o = beanClass.newInstance();//通过无参构造函数
		//属性赋值
		Field[] declaredFields = beanClass.getDeclaredFields();
		for(Field declaredField : declaredFields){
			Autowired annotation = declaredField.getAnnotation(Autowired.class);
			//说明属性有@Autowired
			if(annotation != null){
				declaredField.setAccessible(true);
				String name = declaredField.getName();
				Object fileObject = getBean(name);
				declaredField.set(o,fileObject);
			}
		}

		//初始化  init-method
		//添加到一级缓存
		singletonObjects.put(beanName,o);
		return o;
	}

}
