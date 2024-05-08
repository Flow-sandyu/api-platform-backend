package com.flowsand.project.service;

import com.flowsand.project.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 用户服务测试
 *
 * @author flow
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        boolean result = userService.updateById(user);
        Assertions.assertTrue(result);
    }

    @Test
    void testDeleteUser() {
        boolean result = userService.removeById(1L);
        Assertions.assertTrue(result);
    }

    @Test
    void testGetUser() {
        User user = userService.getById(1L);
        Assertions.assertNotNull(user);
    }

    @Test
    void userRegister() {
        String userAccount = "flow";
        String userPassword = "";
        String checkPassword = "123456";
        try {
            long result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "fl";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "flow";
            userPassword = "123456";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "fl ow";
            userPassword = "12345678";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            checkPassword = "123456789";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "dogflow";
            checkPassword = "12345678";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
            userAccount = "flow";
            result = userService.userRegister(userAccount, userPassword, checkPassword);
            Assertions.assertEquals(-1, result);
        } catch (Exception e) {

        }
    }
}