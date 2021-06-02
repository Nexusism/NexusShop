package nexusbook.nexusshop.Service;

import nexusbook.nexusshop.domain.Member;
import nexusbook.nexusshop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given 주어졌을때
        Member member = new Member();
        member.setName("kim");

        // when 이렇게하면
        Long saveId = memberService.join(member);

        // then 이렇게된다
        assertEquals(member, memberRepository.findOne(saveId));
     }

     @Test
     public void 중복_회원_예외() throws Exception {
         // given

         // when

         // then
      }



}