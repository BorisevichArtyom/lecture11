package by.itacademy.javaenterprise.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataSource  {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static final String USER_PROPERTY = "xxx";
    private static final String PASSWORD_PROPERTY = "xxx";
    private static final String URL_PROPERTY = "jdbc:mysql://localhost:3306/Athlete_Diary?useSSL=false";
    private static final String DRIVER_PROPERTY = "com.mysql.jdbc.Driver";


    static {
        config.setJdbcUrl(URL_PROPERTY);
        config.setUsername(USER_PROPERTY);
        config.setPassword(PASSWORD_PROPERTY);
        config.setMaximumPoolSize(30);
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
