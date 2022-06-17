package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1l,"천재현", Grade.VIP);

        int discountprice = discountService.discount(member, 10000, "fixDiscountPolicy");
        Assertions.assertThat(discountprice).isEqualTo(1000);

        int ratediscountprice = discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertThat(ratediscountprice).isEqualTo(2000);

    }

    static class DiscountService{
        public DiscountService(Map<String, DiscountPolicy> policyMap) {
            this.policyMap = policyMap;
        }

        @Autowired
        private final Map<String, DiscountPolicy> policyMap;

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }
    }
}
