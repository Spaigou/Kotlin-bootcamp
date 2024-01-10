package org.example

import com.google.gson.Gson
import java.io.File
import org.example.data.*

fun getField(i: Int): String {
    return when (i) {
        1 -> "IT"
        2 -> "Banking"
        3 -> "Public services"
        4 -> "All"
        else -> ""
    }
}

fun getProfession(i: Int): String {
    return when (i) {
        1 -> "Developer"
        2 -> "QA"
        3 -> "Project Manager"
        4 -> "Analyst"
        5 -> "Designer"
        6 -> "All"
        else -> ""
    }
}

fun getLevel(i: Int): String {
    return when (i) {
        1 -> "Junior"
        2 -> "Middle"
        3 -> "Senior"
        4 -> "All"
        else -> ""
    }
}

fun getSalary(i: Int): String {
    return when (i) {
        1 -> "< 100000"
        2 -> "100000 - 150000"
        3 -> "> 150000"
        4 -> "All"
        else -> ""
    }
}

fun compareSalary(salary: Int, salaryInterval: Int): Boolean {
    return when (salaryInterval) {
        1 -> salary < 100000
        2 -> salary in 100000..149999
        3 -> salary >= 150000
        4 -> true
        else -> false
    }
}

fun compareField(num: Int, field: String): Boolean {
    return if (num == 4) true
    else field.lowercase() == getField(num).lowercase()
}

fun compareProfession(num: Int, profession: String): Boolean {
    return when {
        num == 6 -> {
            true
        }

        num in 1..2 || num in 4..5 -> {
            profession.lowercase() == getProfession(num).lowercase()
        }

        num == 3 && profession.lowercase() == "pm" && getProfession(num) == "Project Manager" -> {
            true
        }

        else -> false
    }
}

fun compareLevel(num: Int, level: String): Boolean {
    return if (num == 4) true
    else level.lowercase() == getLevel(num).lowercase()
}

fun printVacancies(fieldOfActivity: Int, profession: Int, level: Int, salary: Int) {
    val jsonContent = File("src/main/resources/listOfCompanies.json").readText()
    val companies = Gson().fromJson(jsonContent, CompanyList::class.java)
    var counter = 1
    companies.listOfCompanies.forEach { company ->
        company.vacancies.forEach { vacancy ->
            if (compareField(fieldOfActivity, company.fieldOfActivity) &&
                compareProfession(profession, vacancy.profession) &&
                compareLevel(level, vacancy.level) &&
                compareSalary(vacancy.salary, salary)
            ) {
                println("$counter.")
                println("${vacancy.level.replaceFirstChar { it.uppercaseChar() }} " +
                        "${vacancy.profession.replaceFirstChar { it.uppercaseChar() }}     ---      ${vacancy.salary}"
                )
                println("  ${company.name}")
                println("  ${company.fieldOfActivity}")
                println("---------------------------------------")
                counter++
            }
        }

    }
}

fun main() {
    println(
        "Select a field of activity:\n" +
                "1. IT\n" +
                "2. Banking\n" +
                "3. Public services\n" +
                "4. All\n"
    )
    var fieldOfActivity = readln().toIntOrNull()
    while (fieldOfActivity == null || fieldOfActivity !in 1..4) {
        println("It doesn't look like a correct input. Try again.")
        fieldOfActivity = readln().toIntOrNull()
    }

    println(
        "${getField(fieldOfActivity)}. Select a profession:\n" +
                "1. Developer\n" +
                "2. QA\n" +
                "3. Project Manager\n" +
                "4. Analyst\n" +
                "5. Designer\n" +
                "6. All\n"
    )

    var profession = readln().toIntOrNull()
    while (profession == null || profession !in 1..6) {
        println("It doesn't look like a correct input. Try again.")
        profession = readln().toIntOrNull()
    }

    println(
        "${getField(fieldOfActivity)}. ${getProfession(profession)}. Select the level of a candidate:\n" +
                "1. Junior \n" +
                "2. Middle\n" +
                "3. Senior\n" +
                "4. All\n"
    )
    var candidateLevel = readln().toIntOrNull()
    while (candidateLevel == null || candidateLevel !in 1..4) {
        println("It doesn't look like a correct input. Try again.")
        candidateLevel = readln().toIntOrNull()
    }

    println(
        "${getField(fieldOfActivity)}. ${getProfession(profession)}. ${getLevel(candidateLevel)}. Select a salary level:\n" +
                "1. < 100000\n" +
                "2. 100000 - 150000\n" +
                "3. > 150000\n" +
                "4. All\n"
    )
    var salary = readln().toIntOrNull()
    while (salary == null || salary !in 1..4) {
        println("It doesn't look like a correct input. Try again.")
        salary = readln().toIntOrNull()
    }

    println("${getField(fieldOfActivity)}. ${getProfession(profession)}. ${getLevel(candidateLevel)}. ${getSalary(salary)}")
    println("The list of suitable vacancies:")
    printVacancies(fieldOfActivity, profession, candidateLevel, salary)
}
