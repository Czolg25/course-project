package pl.dykacz.courses.courses.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQLConfiguration {
    @Value("${spring.datasource.url}")
    private String connectionString;
    @Value("${spring.datasource.username}")
    private String login;
    @Value("${spring.datasource.password}")
    private String password;

    public String getConnectionString() {
        return connectionString;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
