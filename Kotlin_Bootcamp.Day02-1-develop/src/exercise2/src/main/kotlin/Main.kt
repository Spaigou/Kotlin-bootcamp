package org.example

import com.google.gson.Gson
import org.example.data.Resume
import java.io.File

fun main() {
    val jsonContent = File("src/files/resume.json").readText()
    val resume = Gson().fromJson(jsonContent, Resume::class.java)
    println("Block 1\n${resume.candidateInfo}\n...\n")
    println("Block 2\n${resume.education}\n...\n")
    println("Block 3\n${resume.jobExperience}\n...\n")
    println("Block 4\n${resume.freeForm}\n...\n")
}