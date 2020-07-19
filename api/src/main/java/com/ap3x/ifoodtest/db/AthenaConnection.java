package com.ap3x.ifoodtest.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class AthenaConnection {

    private static final String DRIVER_CLASS = "com.simba.athena.jdbc.Driver";

    @Autowired
    private Environment env;

    public AthenaConnection() throws ClassNotFoundException {
        Class.forName(DRIVER_CLASS);
    }

    public Connection getConnection() throws SQLException {
        final String connectionString = String.format(
                "jdbc:awsathena://AwsRegion=%s;User=%s;Password=%s;S3OutputLocation=%s;Schema=%s",
                env.getProperty("aws.region"),
                env.getProperty("aws.accessKey"),
                env.getProperty("aws.secretKey"),
                env.getProperty("aws.athena.queryOutputLocation"),
                env.getProperty("aws.athena.database"));
        return DriverManager.getConnection(connectionString);
    }

}


