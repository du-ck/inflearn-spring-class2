package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        /*MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("final order :: " + order.calculatePrice());*/

        //Appconfig를 이용하여 실행
        /*AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();*/
        /**
         * ApplicationContext : 스프링 컨테이너라고 한다.
         * @Configuration 이 붙은 AppConfig class 를 구성정보로 사용한다.
         * 여기서 @Bean 이 적힌 메서드를 모두 스프링 컨테이너에 등록한다.
         * 이렇게 등록된 객체를 스프링 빈 이라고 한다.
         *
         * Appconfig 를 사용해서 직접 조회가 아닌
         * 이제 빈에 등록된 객체를 찾을때는 applicationContext.getBean() 을 통해 찾아야 한다.
         *
         * 이렇게 사용하면 장점 >>
         *
         */
        ApplicationContext act = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = act.getBean("memberService", MemberService.class);
        OrderService orderService = act.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("final order :: " + order.calculatePrice());

    }
}
