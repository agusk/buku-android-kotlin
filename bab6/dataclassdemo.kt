
data class Employee(var id: Int, var name: String, var email: String)

fun main(args: Array<String>) {

    var emp = Employee(2, "mr. a", "a@email.com")
    val (id, name, email) = emp

    println("Employee. id: $id, name: $name, email: $email")        

}