package com.vinit5619692.tasker;

import com.vinit5619692.tasker.controller.TaskerController;
import com.vinit5619692.tasker.dao.TaskerEntityDAO;
import com.vinit5619692.tasker.entity.TaskEntity;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * This class is starting point for the application
 */
public class ApplicationRunner extends Application<ApplicationConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    /**
     * Returns application name
     * @return String
     */
    @Override
    public String getName() {
        return "Tasker application";
    }

    public static void main(String[] args) throws Exception {
        new ApplicationRunner().run(args);
    }

    private final HibernateBundle<ApplicationConfiguration> hibernate =
            new HibernateBundle<ApplicationConfiguration>(TaskEntity.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(ApplicationConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    /**
     * initializing bootstrap
     * @param bootstrap the application bootstrap
     */
    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    /**
     * initializing and register tasker controller/resource
     * @param configuration the parsed {@link Configuration} object
     * @param environment   the application's {@link Environment}
     * @throws Exception
     */
    @Override
    public void run(ApplicationConfiguration configuration, Environment environment) throws Exception {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");

        cors.setInitParameter("Access-Control-Allow-Credentials", "true");
        cors.setInitParameter("Access-Control-Allow-Origin", "http://localhost:8000/");
        cors.setInitParameter("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Headers, Access-Control-Request-Method, Cache-Control, Pragma, Expires");
        cors.setInitParameter("Access-Control-Allow-Methods\" ", "OPTIONS,GET,PUT,POST,DELETE,HEAD");



        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());

        final TaskerEntityDAO taskerEntityDAO = new TaskerEntityDAO(hibernate.getSessionFactory());
         environment.jersey().register(new TaskerController(taskerEntityDAO));

    }


}
