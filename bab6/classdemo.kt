
// simple class
class Foo{

    fun show(){
        println("Hello")
    }

}

// class with constructor
class City(id: Int = 0,name: String = "") { // constructor
    // class fields
    var id: Int = 0 
    var nm: String = ""

    // getter and setter   
    var name: String = ""
        get() = this.nm        
    
    var counter = 0 
        set(value) {
            if (value >= 0) field = value
        }

    // constructor
    init {
        this.id = id    
        this.nm = name
    }    

    // methods/functions
    fun show(){
        println("id $id. name $nm")
    }

    fun calculate(n: Int): Int {
        return n * this.counter
    }
}


fun main(args: Array<String>) {

    var f = Foo()
    f.show()


    var depok = City(name="Depok")
    var bogor = City(10,"Boogor")

    depok.show()
    bogor.show()
    println(depok.name)
    println(bogor.name)

    depok.counter = 5
    bogor.counter = 3

    println(depok.calculate(7))
    println(bogor.calculate(7))
}