package hello.core.member

import org.springframework.stereotype.Component

@Component
class MemoryMemberRepository : MemberRepository {

    companion object{
        private val store = HashMap<Long, Member>()
    }

    override fun save(member: Member) {
        store.put(member.getId(), member)
    }

    override fun findById(memberId: Long): Member? {
        return store.get(memberId)
    }
}