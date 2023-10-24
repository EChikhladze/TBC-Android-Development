import java.util.*

fun main() {
    val reader = Scanner(System.`in`)

    app@ while(true) {
        var x: Int
        var y: Int
        print("Enter value of X: ")
        try {
            x = reader.nextInt()
        } catch(e: InputMismatchException) {
            println("Invalid input. Enter only integers")
            reader.nextLine()
            continue@app
        }

        inputY@ while(true) {
            print("Enter value of Y: ")
            try {
                y = reader.nextInt()
                break@inputY
            } catch(e: InputMismatchException) {
                println("Invalid input. Enter only integers")
                reader.nextLine()
                continue
            }
        }

        println()
        val z = x + y
        println("X + Y = $z")

        startAgain@ while (true) {
            print("Would you like to start again? <Y/N>: ")
            val input = readLine()
            val answer: Char? = input?.get(0)
            when (answer) {
                'Y' -> {
                    println()
                    continue@app
                }
                'N' -> break@app
                else -> {
                    println("Please enter a valid character")
                    continue@startAgain
                }
            }
        }
    }
}