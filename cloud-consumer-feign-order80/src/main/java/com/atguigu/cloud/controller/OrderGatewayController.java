package com.atguigu.cloud.controller;

import com.atguigu.cloud.api.PayFeignApi;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yujie
 * @Description
 * @Date Created in 2024-07-26 11:47
 * @Version 1.0
 */
@RestController
public class OrderGatewayController {
    @Resource
    private PayFeignApi payFeignApi;

    /**
     * GateWay进行网关测试案例01
     * @param id
     * @return
     */
    @GetMapping(value = "/feign/pay/gateway/get/{id}")
    public ResultData<Pay> getByIdGateway(@PathVariable("id") Integer id){
        ResultData<Pay> resultData = payFeignApi.getByIdGateway(id);
        return resultData;
    }


    /**
     * GateWay进行网关测试案例02
     * @return
     */
    @GetMapping(value = "/feign/pay/gateway/info")
    public ResultData<String> getGatewayInfo(){
        ResultData<String> resultData = payFeignApi.getGatewayInfo();
        return resultData;
    }


}
