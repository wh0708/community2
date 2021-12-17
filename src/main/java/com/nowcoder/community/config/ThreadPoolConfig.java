package com.nowcoder.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author WH
 * @create 2021/12/3 20:49
 */

@Configuration
@EnableScheduling
@EnableAsync
public class ThreadPoolConfig {
}
