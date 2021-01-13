package hello.core.member

interface MemberRepository {

    fun save(mebmer: Member)
    fun findById(memberId: Long) : Member?
}