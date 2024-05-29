package com.flowsand.apiinterface;

import com.flowsand.apiclientsdk.client.ApiClient;
import com.flowsand.apiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ApiInterfaceApplicationTests {
    @Resource
    private ApiClient apiClient;

    @Test
    void testApiClient() {
        String result1 = apiClient.getNameByGet("鱼皮");
        String result2 = apiClient.getNameByPost("鱼皮");
        User user = new User();
        user.setUsername("flow");
        String result3 = apiClient.getUserNameByPost(user);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
