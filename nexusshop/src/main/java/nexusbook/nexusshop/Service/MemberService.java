package nexusbook.nexusshop.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nexusbook.nexusshop.domain.Member;
import nexusbook.nexusshop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 조회문은 readOnly로하는게 최적화
@RequiredArgsConstructor
public class MemberService {

     // 필드인젝션  // 테스트할때 바꿀수있는 방법이 없음
    private final MemberRepository memberRepository;

    //@Autowired 생성자가 하나일때는 스프링에서 자동으로 Autowired 해줌
    //public MemberService(MemberRepository memberRepository) {
    //    this.memberRepository = memberRepository;
    //}


    @Transactional // 저장은 readOnly하면 변경이안됨
    //회원 가입
    public Long join(Member member) {

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId(); // id를 돌려줘야 뭐가 저장됬는지 알수있음
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
