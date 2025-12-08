package com.chaekmate.logging;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

public class LoggingInitializer implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private static final String APP_NAME_PROPERTY = "spring.application.name";
    private static final String LOGBACK_VARIABLE_NAME = "SERVICE_NAME";
    private static final String PREFIX_TO_REMOVE = "chaekmate-";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        Environment environment = event.getEnvironment();
        String appName = environment.getProperty(APP_NAME_PROPERTY);

        String serviceName = "unknown-service"; // Default value

        if (appName != null && !appName.trim().isEmpty()) {
            if (appName.startsWith(PREFIX_TO_REMOVE)) {
                serviceName = appName.substring(PREFIX_TO_REMOVE.length());
            } else {
                serviceName = appName;
            }
        }

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.putProperty(LOGBACK_VARIABLE_NAME, serviceName);
    }
}
