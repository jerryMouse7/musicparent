//package com.liu.clouddisk.config;
//
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Map;
//import java.util.Set;
//
//@Configuration
//@EnableConfigurationProperties(HbaseConfig.class)
//public class HbaseConfig {
//    private HbaseProperties properties;
//
//    public HbaseConfig(HbaseProperties hbaseProperties){
//        this.properties = hbaseProperties;
//    }
//
//    public org.apache.hadoop.conf.Configuration configuration(){
//        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
//        Map<String,String> config= properties.getConfig();
//        Set<String> keySet = config.keySet();
//        for(String key : keySet){
//            configuration.set(key,config.get(key));
//        }
//        return configuration;
//    }
//}
