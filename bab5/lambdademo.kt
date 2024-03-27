


fun main(args: Array<String>) {

    // simple lambda functions
    val helloLambda = {
        println("Hello, Lambda!")
    }
    helloLambda()
    helloLambda.invoke()

    // lambda functions with parameters
    val calculate = { num: Int, num2: Int -> 
        val n = num * num2
        println("num: $num. num2: $num2. n: $n") 
    }
    calculate(10, 5)

    // lambda functions with return value
    val sum = { num: Int, num2: Int -> 
        num + num2
    }


}