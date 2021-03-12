

fun main(args: Array<String>) {
    // iteration - for
    println("demo - iteration for")
    for (i in 1..3) {
        print("$i ")
    }
    println("")    


    // nested - for
    println("demo - nested for")
    for (i in 1..3)
        for (j in 5..8)
            print("$i - $j. ")
   println("")
    
    //iteration - while
    println("demo - iteration while")
    var i = 0
    while (i < 5) {
        println(i)
        i++
    }

    //iteration - do..while
    println("demo - iteration do..while")
    var j = 0
    do {
        println(j)
        j++
    }while(j<5)
        
}