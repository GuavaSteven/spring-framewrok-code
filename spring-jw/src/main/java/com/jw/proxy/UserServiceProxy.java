package com.jw.proxy;

/**
 * @author cjw
 * @Description:
 * @date 2021/5/12 14:25
 */
public class UserServiceProxy implements UserService {
	private UserService userService;

	public UserServiceProxy(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void sayHello() {
		userService.sayHello();
	}
}
