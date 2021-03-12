

fun main(args: Array<String>) {
    
    println("demo - break, continue and pass")
    for (i in 1..10){
        if(i == 4)
            continue

        if(i == 7)
            break
        
        println(i)
    } 
    println("This is the end of program")

}