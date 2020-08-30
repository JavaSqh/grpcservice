package com.effsim.main.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyScheduled {

    @Scheduled(cron = "0/10 * * * * *")
    public void test(){
        System.out.println(new Date()+"开始执行 ");
    }
}