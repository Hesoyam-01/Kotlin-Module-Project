import java.util.Scanner

class Note (private val name: String, private val noteText: String) : Type {

    override fun toString(): String {
        return name
    }

    fun getText() {
        println(noteText)
    }
}

class NoteScreen {
    private val menu = Menu()

    fun start(choose: Archive) {
        while (true) {
            println("1 - Выбрать заметку\n2 - Создать заметку\n3 - Вернуться к архивам")

            val input: String = Scanner(System.`in`).nextLine()
            when (input) {
                "1" -> {
                    if (menu.showList<Note>(choose) == null) continue
                    else {
                        menu.choose<Note>(choose)
                        noteScreen.start(choose)
                        return
                    }
                }

                "2" -> menu.create<Note>(choose)
                "3" -> break
                else -> println("Неверный ввод.")
            }
        }
        archiveScreen.start()
    }
}