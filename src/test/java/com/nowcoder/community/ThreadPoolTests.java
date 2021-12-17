package com.nowcoder.community;


import com.nowcoder.community.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @Author WH
 * @create 2021/12/3 20:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class ThreadPoolTests {

    private static final Logger logger= LoggerFactory.getLogger(ThreadPoolTests.class);

    //java
    private ExecutorService executorService=Executors.newFixedThreadPool(5);

    //spring线程池
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private AlphaService alphaService;

    private void sleep(long m){
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExecutorService(){
        Runnable task=new Runnable() {
            @Override
            public void run() {
                logger.debug("Hello");
            }
        };
        for(int i=0;i<10;i++){
            executorService.submit(task);
        }
        sleep(20);
    }

    @Test
    public void testThreadPoolTaskExecutor(){
        Runnable task=new Runnable() {
            @Override
            public void run() {
                logger.debug("Hello");
            }
        };
        for(int i=0;i<10;i++){
            threadPoolTaskExecutor.submit(task);
        }
        sleep(20);
    }

    @Test
    public void testThreadPoolTaskScheduler(){
        Runnable task=new Runnable() {
            @Override
            public void run() {
                logger.debug("Hello");
            }
        };

        Date date = new Date(System.currentTimeMillis() + 10000);
        threadPoolTaskScheduler.scheduleAtFixedRate(task,date, 1000);

        sleep(30000);
    }

    //
    @Test
    public void test1(){
        for (int i = 0; i < 10; i++) {
            alphaService.executor1();
            sleep(1011);
        }
    }

    @Test
    public void test2(){

        sleep(101100);

    }

}
