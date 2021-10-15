
fun main(args: Array<String>) {

    // generic array
    val ints: Array<Int> = arrayOf(8,9,14,12)
    val cities: Array<String> = arrayOf("berlin","london","amsterdam","new york")

    // display array
    for (v in ints) {
        println("$v ")
    }
    for (v in cities) {
        println("$v ")
    }

    // error
    //val ints2: Array<Int> = arrayOf(8,9,"london")


}