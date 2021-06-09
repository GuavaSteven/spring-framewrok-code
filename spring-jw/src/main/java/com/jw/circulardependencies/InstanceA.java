package com.jw.circulardependencies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cjw
 * @Description:
 * @date 2021/5/17 17:10
 */
@Component
public class InstanceA {
	@Autowired
	private InstanceB instanceB;
}
