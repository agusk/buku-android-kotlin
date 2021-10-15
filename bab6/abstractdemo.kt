
// simple abstract
abstract class  Animal(name: String) {
    var nm: String

    // Abstract Property
    abstract var age: Int

    // constructor
    init {        
        this.nm = name
    }  

    // Abstract Methods
    abstract fun say()

    fun show(){
        println("Name: $nm. Age: $age")
    }    
}

class Dog(name: String) : Animal(name) {
    
    override var age: Int = 0
    set(value) {
        if (value >= 0) field = value
    }

    override fun say() {
        println("Gukk!!")
    }
}

fun main(args: Array<String>) {

    var f = Dog("black-dog")
    f.age = 3
    f.say()
    f.show()    

}