open class Shape{

    // to be override properties
    open var id: Int = 0
    set(value){
            if (value > 0) field = value
        }

    // to be override methods
    open fun say(){
        println("this is a shape")
    }    

    fun displayId(){
        println("id: $id")
    }
}

class Rectangle: Shape(){
    override var id: Int = 0
        set(value){
            if (value > 0) field = value + 10
        }

    override fun say(){
        println("this is a rectange")
    }
}

class Circle: Shape(){
     override var id: Int = 0
        set(value){
            if (value > 0) field = value + 15
        }

    override fun say(){
        println("this is a circle")
    }
}

fun main(args: Array<String>) {

    var sh = Shape()
    var rc = Rectangle()
    var cr = Circle()

    sh.say()
    sh.id = 5
    sh.displayId()

    rc.say()
    rc.id = 5
    rc.displayId()
    
    cr.say()
    cr.id = 5
    cr.displayId()
    
}