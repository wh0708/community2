package com.nowcoder.community.actuator;

import com.nowcoder.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author WH
 * @create 2021/12/22 13:57
 */
@Component
@Endpoint(id = "database")
public class DatabaseEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseEndpoint.class);

    //连接池，由Spring管理，注入即可
    @Autowired
    private DataSource dataSource;

    // @ReadOperation 表示该方法通过 get请求访问
    @ReadOperation
    public String checkConnection() {
        try (
                Connection conn = dataSource.getConnection(); // 尝试获取连接，编译时会自动加finally把初始化的资源关闭
        ) {
            return CommunityUtil.getJSONString(0, "获取连接成功!");  // 返回json字符串
        } catch (SQLException e) {
            logger.error("获取连接失败:" + e.getMessage());
            return CommunityUtil.getJSONString(1, "获取连接失败!");
        }
    }

}

