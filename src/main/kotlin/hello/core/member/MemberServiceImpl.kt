package hello.core.member

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MemberServiceImpl(
        @Autowired
        val memberRepository: MemberRepository
) : MemberService {

    override fun join(member: Member) {
        memberRepository.save(member)
    }

    override fun findMember(id: Long): Member? {
        return memberRepository.findById(id)
    }
}