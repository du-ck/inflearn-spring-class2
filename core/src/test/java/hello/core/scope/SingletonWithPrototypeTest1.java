package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    /**
     * 싱글톤 빈은 생성시점에만 의존관계 주입을 받기 때문에
     * 프로토타입 빈이 새로 생성되기는 하지만 싱글톤 빈과 함께 계속 유지되는 것이 문제다.
     */

    /**
     * 프로토타입 빈 사용할 때 (굉장히 드뭄)
     * 매번 사용할 때 마다 의존관계 주입이 완료된 새로운 객체가 필요할 때 사용
     */

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }


    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int cnt1 = clientBean1.logic();
        Assertions.assertThat(cnt1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int cnt2 = clientBean2.logic();
        Assertions.assertThat(cnt2).isEqualTo(1);
    }

    @Scope("singleton") //default
    @RequiredArgsConstructor
    static class ClientBean {
        //private final PrototypeBean prototypeBean;  //생성 시점에 주입

        /*@Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;*/ // 이 방법도 스프링에 의존

        /**
         * javax.inject:javax.inject:1  에 있는 Provider 사용
         * provider 의 get() 을 호출하면 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다.
         *
         */
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;


        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();

            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
