package com.vinit5619692.tasker;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * This class is for doing application level configuration
 */
public class ApplicationConfiguration extends Configuration {


    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    /**
     * This Method is used get the DataSourceFactory
     * @return DataSourceFactory
     */
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    /**
     * This Method is used set the DataSourceFactory
     */
    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }
}
