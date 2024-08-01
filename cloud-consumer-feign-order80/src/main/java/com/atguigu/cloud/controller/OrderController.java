package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.api.PayFeignApi;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
  @Resource
  private PayFeignApi payFeignApi;

    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * 参数可以不添加@RequestBody
     * @param payDTO
     * @return
     */
    @PostMapping("/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        ResultData<String> resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }

    @DeleteMapping(value = "/feign/pay/del/{id}")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id){
        ResultData<Integer> resultData = payFeignApi.deletePay(id);
        return resultData;
    }

    @PutMapping(value = "/feign/pay/update")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        ResultData<String> resultData = payFeignApi.updatePay(payDTO);
        return resultData;
    }

    @GetMapping(value = "/feign/pay/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        ResultData<Pay> resultData = null;
        try {
            System.out.println("支付微服务开始调用"+ DateUtil.now());
            resultData = payFeignApi.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("支付微服务结束调用"+ DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return resultData;
    }

    @GetMapping("/feign/pay/getAll")
    public ResultData<List> getAll(){
        ResultData<List> resultData = payFeignApi.getAll();
        return resultData;
    }
    /**
     * openfeign天然支持负载均衡演示
     *
     * @return
     */
    @GetMapping(value = "/feign/pay/mylb")
    public String mylb()
    {
        return payFeignApi.mylb();
    }


}