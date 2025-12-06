package com.chaekmate.logging;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Logback과 같이 Spring Bean이 아닌 곳에서 Spring의 Environment 속성에 접근할 수 있도록 도와주는 홀더 클래스입니다.
 * ApplicationContext가 초기화될 때 static하게 Environment를 저장합니다.
 */
@Component
public class SpringEnvironmentHolder implements ApplicationContextAware {

    private static Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringEnvironmentHolder.environment == null) {
            SpringEnvironmentHolder.environment = applicationContext.getEnvironment();
        }
    }

    /**
     * Spring Environment에서 프로퍼티 값을 가져옵니다.
     * @param key 프로퍼티 키
     * @return 프로퍼티 값
     */
    public static String getProperty(String key) {
        return environment == null ? null : environment.getProperty(key);
    }
}
