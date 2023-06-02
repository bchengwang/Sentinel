/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
@Component
public abstract class AbstractRuleNacosPublisher<T> implements DynamicRulePublisher<List<T>> {

    private final ConfigService configService;

    private final Converter<List<T>, String> converter;

    @Autowired
    public AbstractRuleNacosPublisher(ConfigService configService, Converter<List<T>, String> converter) {
        this.configService = configService;
        this.converter = converter;
    }

    @Override
    public void publish(String app, List<T> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        configService.publishConfig(app + getDataIdPostfix(), NacosConfigUtil.GROUP_ID, converter.convert(rules), ConfigType.JSON.getType());
    }

    public abstract String getDataIdPostfix();
}
