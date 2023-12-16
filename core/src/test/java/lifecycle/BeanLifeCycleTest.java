package lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        /**
         * @Bean 의 destoryMethod 는 디폴트가 '추론' 상태로 되어있어서
         * close 나 shutdown 의 메서드가 있을 경우
         * 해당 메서드를 자동으로 호출해준다.
         * 따라서 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 동작한다.
         *
         * 이걸 사용하기 싫으면 destroyMethod=""  형태로 공백으로 지정해주면된다.
         *
         */
        //@Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
