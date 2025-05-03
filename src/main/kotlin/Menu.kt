import java.util.Scanner

class Menu {


    inline fun <reified S : Type> create(choose: Archive?) {
        when {
            S::class == Archive::class -> {
                while (true) {
                    println("Введите название архива: ")
                    val archiveName = Scanner(System.`in`).nextLine()
                    if (archiveName.isNotEmpty()) {
                        val archive = Archive(archiveName)
                        listOfArchives.add(archive)
                        return
                    } else println("Название архива не может быть пустым!")
                }

            }

            S::class == Note::class -> {
                while (true) {
                    println("Введите название заметки: ")
                    val noteName = Scanner(System.`in`).nextLine()
                    println("Введите текст заметки: ")
                    val noteText = Scanner(System.`in`).nextLine()

                    if (noteName.isNotEmpty() && noteText.isNotEmpty()) {
                        val note = Note(noteName, noteText)
                        choose?.listOfNotes?.add(note)
                        return
                    } else {
                        println("Название или текст заметки не могут быть пустыми.")
                    }
                }
            }
        }
    }

    inline fun <reified E> choose(choose: Archive?): Archive? {
        val scanner = Scanner(System.`in`)

        while (true) {
            if (E::class == Archive::class) println("Выберите номер архива:")
            else println("Выберите номер заметки:")

            try {
                val choice = scanner.nextInt()
                if (E::class == Archive::class) {
                    if (choice in 1..listOfArchives.size) return listOfArchives[choice - 1]
                    else println("Неверный выбор.")
                }
                if (E::class == Note::class) if (choose != null) {
                    if (choice in 1..choose.listOfNotes.size) {
                        choose.listOfNotes[choice - 1].getText()
                        return null
                    } else println("Неверный выбор.")
                }

            } catch (e: Exception) {
                println("Неверный ввод.")
                scanner.nextLine()
            }
        }
    }

    inline fun <reified E> showList(choose: Archive?): Int? {
        if (E::class == Archive::class) println("Список архивов:")
        else println("Список заметок:")

        if (listOfArchives.isEmpty()) {
            println("Пусто")
            return null
        }
        if (E::class == Archive::class) {
            for (item in listOfArchives) {
                print(listOfArchives.indexOf(item) + 1)
                print(" - " + item.toString() + "\n")
            }
        } else {
            if (choose != null) {
                if (choose.listOfNotes.isEmpty()) {
                    println("Пусто")
                    return null
                }
                for (item in choose.listOfNotes) {
                    print(choose.listOfNotes.indexOf(item) + 1)
                    print(" - " + item.toString() + "\n")
                }
            }
        }
        return -1
    }
}

interface Type