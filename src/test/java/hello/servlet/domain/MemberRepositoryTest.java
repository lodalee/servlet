package hello.servlet.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
class MemberRepositoryTest {

    /**
     * 싱글톤 사용으로 new 를 쓰지 않음. 하지만 spring 에서는
     * 기본적으로 싱글톤을 보장해주기 때문에 다음과 같이 쓸 일 없음
     */

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save()  {
        //given
        Member member = new Member("heelo", 20);

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(saveMember.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findAll()  {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }

}