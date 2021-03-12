
fun main(args: Array<String>) {
    val a = 3
    val b = 8

    // comparison operators
    println(a==b)
    println(a!=b)
    println(a>b)
    println(a<b)
    println(a>=b)
    println(a<=b)

    // logical operators
    println((a == b) and (a != b))
    println((a <= b) or (a > b))
    println(!(a >= b))


    // bitwise operators
    // declare binary variables
    // 12 = 00001100 (In Binary)
    // 25 = 00011001 (In Binary)
    val m : Int = 12
    val n : Int = 25

    println(m)
    println(n)
    println(m and n)
    println(m or n)
    println(m xor n)
    println(n.inv())
    println(m shl 3)
    println(m shr 2)

}