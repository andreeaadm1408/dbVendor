package ro.db.vendor.config.web.server;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


public enum SupportAssistApplicationContext {
    INSTANCE;

    public AnnotationConfigWebApplicationContext getWebContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(WEB_CONFIG_LOCATION);
        return context;
    }
    public AnnotationConfigWebApplicationContext getPersistenceContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(PERSISTANCE_CONFIG_LOCATION);
        return context;
    }

    private static final String PERSISTANCE_CONFIG_LOCATION = "ro.db.vendor.config.persistence";
    private static final String WEB_CONFIG_LOCATION = "ro.db.vendor.config.web";

}