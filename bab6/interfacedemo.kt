
// simple interface
interface MyInterface {
    val nm: Int 

    val name: String
        get() = "content: $nm"

    fun show()
    fun perform() {
        println("hello MyInterface")
    }
}

class SimpleClass : MyInterface {
    override val nm: Int = 29

    override fun show() {
        println("hello SimpleClass")
    }
}
// error since the class does not implement interface property and method
// class MySimpleClass : MyInterface {
    
//     fun display() {
//         println("hello MySimpleClass")
//     }
// }


fun main(args: Array<String>) {

    var f = SimpleClass()
    f.show()
    println(f.name)

}