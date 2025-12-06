package com.chaekmate.logging;

import ch.qos.logback.core.PropertyDefinerBase;

/**
 * Logback 설정에서 사용할 서비스 이름을 정의하는 클래스입니다.
 * Spring의 'spring.application.name' 프로퍼티를 읽어와 "chaekmate-" 접두사를 제거한 값을 반환합니다.
 */
public class ShortServiceNamePropertyDefiner extends PropertyDefinerBase {

    private static final String PREFIX_TO_REMOVE = "chaekmate-";
    private static final String DEFAULT_SERVICE_NAME = "unknown-service";

    @Override
    public String getPropertyValue() {
        String appName = SpringEnvironmentHolder.getProperty("spring.application.name");

        if (appName == null || appName.trim().isEmpty()) {
            return DEFAULT_SERVICE_NAME;
        }

        if (appName.startsWith(PREFIX_TO_REMOVE)) {
            return appName.substring(PREFIX_TO_REMOVE.length());
        }

        return appName;
    }
}
