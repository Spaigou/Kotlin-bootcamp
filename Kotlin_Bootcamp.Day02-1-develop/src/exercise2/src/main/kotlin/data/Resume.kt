package org.example.data

import com.google.gson.annotations.SerializedName

data class Contacts(
    val phone: String,
    val email: String
)

data class CandidateInfo(
    val name: String,
    val profession: String,
    val sex: String,
    val birthDate: String,
    val contacts: Contacts,
    val relocation: String
)

data class Education(
    val type: String,
    @SerializedName("year_start") val yearStart: String,
    @SerializedName("year_end") val yearEnd: String,
    val description: String
)

data class JobExperience(
    @SerializedName("date_start") val dateStart: String,
    @SerializedName("date_end") val dateEnd: String,
    @SerializedName("company_name") val companyName: String,
    val description: String
)

data class Resume(
    @SerializedName("candidate_info") val candidateInfo: CandidateInfo,
    val education: List<Education>,
    @SerializedName("job_experience") val jobExperience: List<JobExperience>,
    @SerializedName("free_form") val freeForm: String
)