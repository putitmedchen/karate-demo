package eventsapi;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DBUtility {
    
    private final JdbcTemplate jdbc;

    public DBUtility(Map<String, Object> config) {
        String url = (String) config.get("url");
        String username = (String) config.get("username");
        String password = (String) config.get("password");
        String driver = (String) config.get("driverClassName");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        jdbc = new JdbcTemplate(dataSource);
    }
    
    
    
    public Map<String, Object> readRow(String query) {
        return jdbc.queryForMap(query);
    }
    
    public List<Map<String, Object>> readRows(String query) {
        return jdbc.queryForList(query);
    }   
    public int runCommand(String query) {
        return jdbc.update(query);
    }
     
    
}