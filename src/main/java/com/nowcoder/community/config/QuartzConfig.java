package com.nowcoder.community.config;

import com.nowcoder.community.quartz.AlphaJob;
import com.nowcoder.community.quartz.PostScoreRefreshJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @Author WH
 * @create 2021/12/3 22:03
 */
@Configuration
public class QuartzConfig {

    //FactoryBean作用：简化bean的实例化过程
    //1.通过FactoryBean封装Bean的实例化过程
    //2.将FactoryBean装配到spring容器中
    //3.将FactoryBean注入给其他的bean
    //4.其他的bean即可获得FactoryBean所管理的对象实例

    //1.配置jobDetail
//    @Bean
    public JobDetailFactoryBean alphaJobDetail(){
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(AlphaJob.class);    //声明要配置的job类
        factoryBean.setName("alphaJob");           //声明job的名称
        factoryBean.setGroup("alphaJobGroup");    //声明job的分组
        factoryBean.setDurability(true);         //job是否长久的存在
        factoryBean.setRequestsRecovery(true);  //job是否可被恢复

        return factoryBean;
    }

    //2.配置trigger
    //  SimpleTriggerFactoryBean：简单的定时
    //  CronTriggerFactoryBean：可实现复杂的定时，如每年的1号进行清除
//    @Bean
    public SimpleTriggerFactoryBean alphaTrigger(JobDetail alphaJobDetail){
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(alphaJobDetail);         //确定JobDetail
        factoryBean.setName("alphaTrigger");             //Trigger的名称
        factoryBean.setGroup("alphaTriggerGroup");      //Trigger的分组
        factoryBean.setRepeatInterval(3000);           //时间间隔3秒
        factoryBean.setJobDataMap(new JobDataMap());  //保存数据的类型

        return factoryBean;
    }

    // 刷新帖子分数任务
    @Bean
    public JobDetailFactoryBean postScoreRefreshJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(PostScoreRefreshJob.class);
        factoryBean.setName("postScoreRefreshJob");
        factoryBean.setGroup("communityJobGroup");
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        return factoryBean;
    }
    // 配置Trigger(SimpleTriggerFactoryBean, CronTriggerFactoryBean)
    @Bean
    public SimpleTriggerFactoryBean postScoreRefreshTrigger(JobDetail postScoreRefreshJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(postScoreRefreshJobDetail);
        factoryBean.setName("postScoreRefreshTrigger");
        factoryBean.setGroup("communityTriggerGroup");
        factoryBean.setRepeatInterval(1000 * 60 * 5);
        factoryBean.setJobDataMap(new JobDataMap());
        return factoryBean;
    }

}

