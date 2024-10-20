package pl.dykacz.courses.courses.data.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import pl.dykacz.courses.courses.configurations.SQLConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Profile("POSTGRESS")
public class PostgresConnection extends SQLQueries{

    @Autowired
    public PostgresConnection(@NonNull final SQLConfiguration sqlConfiguration) {
        super(sqlConfiguration);
    }


    @Override
    protected Connection getConnection() throws SQLException {
        final Connection connection = this.createConnection(this.sqlConfiguration);
        connection.setAutoCommit(false);

        return connection;
    }

    private Connection createConnection(@NonNull final SQLConfiguration sqlConfiguration) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(sqlConfiguration.getConnectionString(),sqlConfiguration.getLogin(),
                    sqlConfiguration.getPassword());
        }catch (final ClassNotFoundException exception){
            exception.printStackTrace();
        }

        return null;
    }
}
