
// simple function
fun foo(){
    println("foo() was called")
}
// function with parameter and returning value
fun calculate(a: Int,b: Int): Int{
    return a + b
}
// function with default value on parameter
fun calculate2(a: Int = 0,b: Int): Int{
    return a + b
}
// nested-function
fun perform(a: Int,b: Int): Int{
    val m = a + b
    fun compute(c: Int): Int{
        return c + 10
    }

    val ret = m * compute(5)
    return ret
}
// recursion function
fun fibonacci(n: Int): Int{
    if(n == 0)
        return 0
    else if(n == 1)
        return 1
    else
        return fibonacci(n-1) + fibonacci(n-2)
}
    


fun main(args: Array<String>) {

    foo()
    val a = calculate(10,5)
    println(a)
    val n = calculate2(b=3)
    println(n)
    val m = perform(2,3)
    println(m)
    val o = fibonacci(10)
    println(o)

}