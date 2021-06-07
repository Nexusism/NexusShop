package nexusbook.nexusshop.Service;

import nexusbook.nexusshop.domain.Member;
import nexusbook.nexusshop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;


    @Test
    public void 회원가입() throws Exception {
        // given 주어졌을때
        Member member = new Member();
        member.setName("kim");

        // when 이렇게하면
        Long saveId = memberService.join(member);

        // then 이렇게된다
        em.flush(); // 인서트 쿼리를 보고싶으면,
       assertEquals(member, memberRepository.findOne(saveId));
     }

     @Test(expected = IllegalStateException.class)
     public void 중복_회원_예외() throws Exception {
         // given
         Member member1 = new Member();
         member1.setName("kim");

         Member member2 = new Member();
         member2.setName("kim");

         // when
         memberService.join(member1);
         memberService.join(member2);
//         try{
//             memberService.join(member2);
//         } catch (IllegalStateException e){
//             return;
//         }

         // then 이 없으면 그냥 밖으로 나감
         fail("예외가 발생해야 한다.");

      }



}