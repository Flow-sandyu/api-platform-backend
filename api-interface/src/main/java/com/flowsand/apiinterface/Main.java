package com.flowsand.apiinterface;

import com.flowsand.apiinterface.client.ApiClient;
import com.flowsand.apiinterface.model.User;

public class Main {
    public static void main(String[] args) {
        ApiClient yuApiClient = new ApiClient("yupi", "abcdefgh");
        String result1 = yuApiClient.getNameByGet("鱼皮");
        String result2 = yuApiClient.getNameByPost("鱼皮");
        User user = new User();
        user.setUsername("flow");
        String result3 = yuApiClient.getUserNameByPost(user);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
