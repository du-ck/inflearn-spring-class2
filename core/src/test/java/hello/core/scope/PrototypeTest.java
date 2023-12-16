package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    /**
     * 싱글톤 빈은 스프링 컨테이너 생성 시점에 초기화 메서드가 실행되지만
     * 프로토타입 빈은 스프링 컨테이너에서 빈을 조회할 때 생성되고 초기화 메서드도 같이 실행된다.
     *
     * 아래 예제에서 2번 조회했으므로 완전히 다른 빈이 생성되고 초기화도 2번 된것을 알 수 있다.
     *
     * 싱글톤 빈은 스프링컨테이너가 관리하기 때문에 종료될 때 빈의 종료메서드가 실행되지만
     * 프로토타입 빈은 스프링 컨테이너가 생성과 의존관계 주입까지만 관여하고 더 관리하지 않는다.
     * 따라서 프로토타입 빈은 스프링 컨테이너가 종료될 때 @PreDestroy 같은 종료 메서드가 실행되지 않는다.
     */


    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("PrototypeTest.prototypeBeanFind1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("PrototypeTest.prototypeBeanFind2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
