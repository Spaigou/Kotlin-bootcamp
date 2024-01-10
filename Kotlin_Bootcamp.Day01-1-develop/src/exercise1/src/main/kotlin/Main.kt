package org.example

import org.example.data.*
import org.example.utils.*

fun defineZone(params: List<Int>): Zone {
    return when (params.size) {
        3 -> CircleZone(params[0], params[1], params[2])
        6 -> TriangleZone(params[0], params[1], params[2], params[3], params[4], params[5])
        8 -> TetragonZone(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7])
        else -> Zone(0, 0)
    }
}

fun formIncident(params: List<Int>): Incident {
    val x = params[0]
    val y = params[1]
    val description = descriptions[descriptions.indices.random()]
    val phone = phones[phones.indices.random()]
    val descriptionLowerCase = description.lowercase()
    val type = when {
        descriptionLowerCase.contains("ufo") -> IncidentType.UFO
        descriptionLowerCase.contains("fire") -> IncidentType.FIRE
        descriptionLowerCase.contains("gas") -> IncidentType.GAS_LEAK
        descriptionLowerCase.contains("cat") -> IncidentType.CAT_ON_TREE
        else -> IncidentType.SILLY_THING
    }
    return Incident(x, y, description, type, phone)
}

fun main() {
    println("Enter zone parameters:")
    val zoneParams = readln().split(";").flatMap { it.split(" ") }
        .map { it.toIntOrNull() ?: throw NumberFormatException("Must be numbers only and ';' separator only") }
    if (zoneParams.size != 3 && zoneParams.size != 6 && zoneParams.size != 8) {
        throw IllegalArgumentException("Wrong parameters")
    }

    val zone = defineZone(zoneParams)
    println("\nThe zone info:")
    println("  The shape of zone: ${zone.name}")
    println("  Phone number: ${zone.phone}")

    println("\nEnter an incident coordinates:")
    val incidentZone = readln().split(";")
        .map { it.toIntOrNull() ?: throw NumberFormatException("Must be numbers only and ';' separator only") }
    if (incidentZone.size != 2) {
        throw IllegalArgumentException("Wrong coordinates")
    }

    val incidentInfo = formIncident(incidentZone)
    println("\nThe incident info:")
    println("Description: ${incidentInfo.description}")
    incidentInfo.phone?.let { println("Phone number: $it") }
    incidentInfo.type?.let { println("Type: ${it.incidentName}") }

    when (zone.isIncidentInside(incidentInfo.x, incidentInfo.y)) {
        true -> println("An incident is in the zone")
        false -> println("An incident is not in the zone\nSwitch the applicant to the common number: 88008473824")
    }
}