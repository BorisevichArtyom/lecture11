package by.itacademy.javaenterprise.connection;

import by.itacademy.javaenterprise.exception.ConnectionPoolException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


import java.sql.Connection;

import java.sql.SQLException;


public class DataSource  {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static final String USER_PROPERTY = "xxx";
    private static final String PASSWORD_PROPERTY = "xxx";
    private static final String URL_PROPERTY = "jdbc:mysql://localhost:3306/Athlete_Diary?useSSL=false";
    private static final int MAX_POOL_SIZE = 30;



    static {
        config.setJdbcUrl(URL_PROPERTY);
        config.setUsername(USER_PROPERTY);
        config.setPassword(PASSWORD_PROPERTY);
        config.setMaximumPoolSize(MAX_POOL_SIZE);
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


}
