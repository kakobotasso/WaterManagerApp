package br.com.watermager.watermanagerapp.Models

class Consumption {
    lateinit var id: Integer
    lateinit var liter: String
    lateinit var month: String
    lateinit var year: String
    lateinit var serial: String

    override fun toString(): String {
        if (liter.contains("R$"))
            return "${convertMonth()}: $liter"

        return "${convertMonth()}: $liter Litros de água"

    }

    private fun convertMonth(): String {
        val monthNumber = month.toInt()

        when (monthNumber) {
            1 -> return "Jan/$year"
            2 -> return "Fev/$year"
            3 -> return "Mar/$year"
            4 -> return "Abri/$year"
            5 -> return "Mai/$year"
            6 -> return "Jun/$year"
            7 -> return "Jul/$year"
            8 -> return "Ago/$year"
            9 -> return "Set/$year"
            10 -> return "Out/$year"
            11 -> return "Nov/$year"
            12 -> return "Dez/$year"
        }

        return ""
    }
}