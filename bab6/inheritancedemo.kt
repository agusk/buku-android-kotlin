open class Shape{

    fun say(){
        println("this is a shape")
    }
    fun callShape(){
        println("this call from shape")
    }
}

class Rectangle: Shape(){

    fun sayRectangle(){
        println("this is a rectange")
    }
}

class Circle: Shape(){

    fun sayCircle(){
        println("this is a circle")
    }
}

fun main(args: Array<String>) {

    var sh = Shape()
    var rc = Rectangle()
    var cr = Circle()

    sh.say()
    sh.callShape()
    rc.sayRectangle()
    rc.callShape()
    cr.sayCircle()
    cr.callShape()
}