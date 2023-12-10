package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


/**
 * @Qualifier 는 구분자를 붙여주는 방법이다. 주입 시 추가적인 방법을 제공 할뿐 빈 이름을 변경하는 것은 아니다.
 * @Primary 는 중복된 이름의 빈이 있을때 @Primary 를 붙은것을 우선으로 가져온다.
 *
 * 스프링은 자동보다는 수동을 우선하기때문에
 * 두개 다 있을 경우 우선순위는 @Qualifier 가 가져온다
 */
@Component
@Qualifier("rateDiscountPolicy")
//@Primary
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
