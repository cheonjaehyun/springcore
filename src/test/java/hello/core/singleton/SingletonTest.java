package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void  pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1.  조회: 호출할 떄마다 객채를 생성
        MemberService memberService = appConfig.memberService();

        //2.  조회: 호출할 떄마다 객채를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른것을 확인

        System.out.println("memberService1 = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        //memberservice1 != memberService2
        //테스트 추가
        Assertions.assertThat(memberService).isNotSameAs(memberService2);
    }
}
