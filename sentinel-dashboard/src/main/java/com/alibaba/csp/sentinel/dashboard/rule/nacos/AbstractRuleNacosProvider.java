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

import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 北橙
 * @since 1.8.6
 */
@Component
public abstract class AbstractRuleNacosProvider<T> implements DynamicRuleProvider<List<T>> {

    private final ConfigService configService;

    private final Converter<String, List<T>> converter;

    @Autowired
    public AbstractRuleNacosProvider(ConfigService configService, Converter<String, List<T>> converter) {
        this.configService = configService;
        this.converter = converter;
    }

    @Override
    public List<T> getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + getDataIdPostfix(), NacosConfigUtil.GROUP_ID, 3000);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return converter.convert(rules);
    }

    public abstract String getDataIdPostfix();
}
