package hello.core.singleton

class SingletonService private constructor(){

    companion object{
        val instance = SingletonService()
    }

    fun getInstance(): SingletonService {
        return instance
    }

}