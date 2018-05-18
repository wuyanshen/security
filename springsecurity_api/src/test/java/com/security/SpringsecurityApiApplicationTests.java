package com.security;

import com.web.security.entity.User;
import com.web.security.mapper.UserMapper;
import com.web.security.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringsecurityApiApplicationTests {
    @Autowired
    private UserService userService;
	@Test
	public void contextLoads() {
		/*userMapper.insert("wuyanshen", "123456",22, "12345678910");
		User u = userMapper.findUserByUserName("wuyanshen");
		Assert.assertEquals("wuyanshen", u.getUsername());*/
        User user = userService.findUser("jack");
        System.out.println(user.getUsername());

	}

}
