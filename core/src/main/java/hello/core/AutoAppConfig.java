package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


/**
 * @ComponentScan 을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에
 * AppConfig, TestConfig 등 앞에서 만든 설정정보들이 함께 등록되고 실행되어 버린다.
 * 따라서 excludeFilters 를 이용해 해당 설정정보들은 제외한다.
 */
@Configuration
@ComponentScan(
        /*basePackages = "hello.core.member",*/
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {



}
