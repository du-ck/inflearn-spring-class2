package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderServiceImpl implements OrderService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 이렇게하면 OCP 위반 (인터페이스와 구체클래스를 함께 의존해버림)
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * 필드에다가 @Autowired 는 좋은 방법이 X
     * 요즘은 생성자에다가 씀
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
