import java.util.Scanner
import kotlin.system.exitProcess

class Archive(private val name: String) : Type {

    val listOfNotes: ArrayList<Note> = ArrayList()

    override fun toString(): String {
        return name
    }
}

class ArchiveScreen {
    private val menu = Menu()

    fun start() {
        while (true) {
            println("1 - Выбрать архив\n2 - Создать архив\n3 - Завершить программу")

            val input: String = Scanner(System.`in`).nextLine()
            when (input) {
                "1" -> {
                    if (menu.showList<Archive>(null) == null) continue
                    else {
                        val choose: Archive? = menu.choose<Archive>(null)
                        if (choose != null) {
                            noteScreen.start(choose)
                        }
                    }
                }

                "2" -> menu.create<Archive>(null)
                "3" -> exitProcess(0)
                else -> println("Неверный ввод.")
            }
        }
    }
}