package hello.core.member

class Member(private var id: Long, private var name: String, private var grade: Grade) {
    fun getId(): Long{
        return id
    }
    fun getName(): String{
        return name
    }
    fun getGrade(): Grade {
        return grade
    }
}