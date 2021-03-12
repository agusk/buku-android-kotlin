
import java.util.Scanner

fun main(args: Array<String>) {

    print("What is your name? ")
    val name = readLine()

    val input = Scanner(System.`in`)
    println("Hello,$name. Please entry two numbers")
    print("Number A = ")
    var a = input.nextInt()
    print("Number B = ")
    var b = input.nextInt()
    
    val c = a + b
    println("value $a + $b is $c")
    
}