package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 이 클래스에서
 * Memory 에서 가져오든지 DB에서 가져오든지를 설정해준다.
 * 따라서 Service단에서는 인터페이스만 의존한다. (실행에만 집중하게됨)
 */
@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoeryMemberRepository()
    // Bean orSErvice -> new MemoeryMemberRepository()


    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        /*return new FixDiscountPolicy();*/
        return new RateDiscountPolicy();
    }


}
