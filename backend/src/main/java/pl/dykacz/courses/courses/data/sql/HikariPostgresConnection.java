package pl.dykacz.courses.courses.data.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pl.dykacz.courses.courses.configurations.SQLConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

@Component
@Profile("POSTGRESS_WITH_HIKARI")
public class HikariPostgresConnection extends SQLQueries{
    private final HikariDataSource dataSource;


    public HikariPostgresConnection(@NonNull final SQLConfiguration sqlConfiguration) {
        super(sqlConfiguration);

        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(sqlConfiguration.getConnectionString());
        hikariConfig.addDataSourceProperty("user", sqlConfiguration.getLogin());
        hikariConfig.addDataSourceProperty("password", sqlConfiguration.getPassword());

        hikariConfig.setAutoCommit(false);

        this.dataSource = new HikariDataSource(hikariConfig);
    }


    @Override
    protected Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
