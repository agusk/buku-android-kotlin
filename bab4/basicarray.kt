
fun main(args: Array<String>) {

    // declare array
    val nums = arrayOf(5,10,7,9,12,15)

    // display array
    for (v in nums) {
        print("$v ")
    }
    println()

    nums.forEach({ 
        v -> print("$v ") 
    })
    println()

    // display with index
    nums.forEachIndexed({i, v -> 
        println("nums[$i] = $v")
    })

    // iterator
    val it: Iterator<Int> = nums.iterator()
    while (it.hasNext()) {
        
        val v = it.next()
        print("$v ")
    }
    println()

    // get  a length of array
    val length = nums.count()
    println("length of nums[] = $length")

    // add item
    println("adding item into array")
    var num2 = arrayOf(4,8,9,10)
    for (v in num2) {
        print("$v ")
    }
    println()
    num2 += 5
    for (v in num2) {
        print("$v ")
    }
    println()

    // editing array
    println("Editing array")
    var items = arrayOf(4,8,9,10)
    items.forEach({ 
        v -> print("$v ") 
    })
    println()
    // edit
    items[0] = 11
    items[2] = 7
    items.forEach({ 
        v -> print("$v ") 
    })
    println()    
    
    // sorting
    println("sorting")
    var numbers = arrayOf(5,10,7,9,12,15)
    val nums_sorted = numbers.sorted()
    for (v in numbers) {
        print("$v ")
    }   
    println()
    for (v in nums_sorted) {
        print("$v ")
    }    
    println()

    // deleting
    println("deleting")
    var items2 = arrayOf(4,8,9,10)
    for (v in items2) {
        print("$v ")
    }
    println()
    val del_item2 = items2.drop(3)
    for (v in del_item2) {
        print("$v ")
    }
    println()

}