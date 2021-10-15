
enum class State {
    STARTED, RUNNING, STOPPED
}


fun main(args: Array<String>) {

    var currentState = State.STARTED

    if(currentState == State.STARTED){
        println("Started")
    }

}