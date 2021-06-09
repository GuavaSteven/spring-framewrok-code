package com.jw.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author cjw
 * @Description:
 * @date 2021/5/12 14:27
 */
public class UserServiceDynamicProxy {
	public static void main(String[] args) {
		UserServiceImpl userService = new UserServiceImpl();
		UserService userServiceProxy = (UserService) getProxy(userService);
		userServiceProxy.sayHello();
	}
	private static Object getProxy(Object target){
		Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println(method.getName() + "方法开始执行...");
				Object result = method.invoke(target, args);
				System.out.println(result);
				System.out.println(method.getName() + "方法执行结束...");
				return result;

			}
		});
		return proxy;
	}
}
