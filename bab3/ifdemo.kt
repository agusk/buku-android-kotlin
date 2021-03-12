

fun main(args: Array<String>) {

    // if-else
    val a = 10
    val b = 30

    println("demo if-else")
    if ((a > 10) || (b > 10)){
        // do something
        println("(a > 10) or (b > 10)")
    }else{
        //do something
        println("else")
    }
           
    // nested if
     println("demo nested if")
    if((a == 0) || (b > 20)){
        if(b < 50)
            println("nested-if")
        else
            println("else-nested-if")
    }        
    else
        println("if-else")
        
}