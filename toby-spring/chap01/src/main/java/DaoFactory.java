import connection.ConnectionMaker;
import connection.NConnectionMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        NConnectionMaker nConnectionMaker = new NConnectionMaker();
        return new UserDao(nConnectionMaker);
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new NConnectionMaker();
    }
}
