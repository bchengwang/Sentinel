package com.alibaba.csp.sentinel.dashboard.rule.nacos;

/**
 * @Author 北橙
 * @Create 2023/6/2
 * @Description TODO
 * @Version 1.0
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Nacos配置
 */
@ConfigurationProperties(prefix = "sentinel.nacos")
public class NacosProperties {
    /**
     * 服务地址
     */
    private String serverAddr;
    /**
     * 分组ID
     */
    private String groupId = NacosConfigUtil.GROUP_ID;
    /**
     * 命名空间
     */
    private String namespace;


    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
}
