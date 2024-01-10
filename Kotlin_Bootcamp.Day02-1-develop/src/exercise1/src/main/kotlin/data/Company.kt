package org.example.data

import com.google.gson.annotations.SerializedName


data class Vacancy(
    val profession: String,
    val level: String,
    val salary: Int
)

data class Company(
    val name: String,
    @SerializedName("field_of_activity") val fieldOfActivity: String,
    val vacancies: List<Vacancy>,
    val contacts: String
)

data class CompanyList(val listOfCompanies: List<Company>)
