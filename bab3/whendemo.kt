import java.util.Scanner

fun main(args: Array<String>) {

    val input = Scanner(System.`in`)
    print("Your score (0-100): ")
    val score = input.nextInt()

    if(score>=0 && score<=100){
        when(score){
            0 -> println("Oh, it's very bad")
            in 1..55 -> println("It's still bad")
            in 56..70 -> println("It's enough")
            in 71..85 -> println("It's good")
            in 86..99 -> println("It's very good")
            100 -> println("Excellent")
        }
    }    


}