package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController{
   // public static final String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码
   public static final String PaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称
        @Autowired
    private RestTemplate restTemplate;

    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * 参数可以不添加@RequestBody
     * @param payDTO
     * @return
     */
    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add",payDTO,ResultData.class);
    }
    // 删除+修改操作作为家庭作业，O(∩_∩)O。。。。。。。
    @DeleteMapping("/consumer/pay/del/{id}")
    public ResultData delPayInfo(@PathVariable("id") Integer id){
        //使用delete发送DELETE请求，返回值为void，方法public后换成void
        //restTemplate.delete(PaymentSrv_URL + "/pay/del/"+id,99);
        // 使用exchange发送DELETE请求,有返回值
        ResponseEntity<ResultData> result =  restTemplate.exchange(PaymentSrv_URL + "/pay/del/"+id, HttpMethod.DELETE,null,ResultData.class);
        return result.getBody();
    }

    @GetMapping( "/consumer/pay/update")
    public ResultData updatePayInfo( PayDTO payDTO){

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 发送请求
        HttpEntity<PayDTO> httpEntity = new HttpEntity<>(payDTO, headers);
        ResponseEntity<ResultData> result =  restTemplate.exchange
                (PaymentSrv_URL + "/pay/update", HttpMethod.PUT,httpEntity,ResultData.class);
        return result.getBody();
    }


    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/"+id, ResultData.class, id);
    }
    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul()
    {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
    }
    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }


}