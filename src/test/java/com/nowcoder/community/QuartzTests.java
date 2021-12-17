package com.nowcoder.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author WH
 * @create 2021/12/3 22:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class QuartzTests {

    //删除Quartz数据库中的job
    @Autowired
    private Scheduler scheduler;

    @Test
    public void deleteJob(){
        // JobKey(job的名称，job的组名)
        try {
            boolean b=scheduler.deleteJob(new JobKey("alphaJob","alphaJobGroup"));
            System.out.println(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

