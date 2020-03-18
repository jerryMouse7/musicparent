package com.liu.gateway;

//import com.ctrip.framework.apollo.model.ConfigChangeEvent;
//import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
//import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableApolloConfig
public class GatewayApplication {

    public static void main(String[] args) {
        System.setProperty("env","DEV");
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @ApolloConfigChangeListener
//    private void someOnChange(ConfigChangeEvent changeEvent) {
//        if(changeEvent.isChanged("username")) {
//            System.out.println("username发生修改了");
//        }
//    }

}
