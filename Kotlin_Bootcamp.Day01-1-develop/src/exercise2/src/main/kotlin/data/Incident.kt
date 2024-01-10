package org.example.data

data class Incident(
    val x: Int,
    val y: Int,
    val description: String,
    val type: IncidentType?,
    val phone: String?
)

enum class IncidentType(val incidentName: String) {
    FIRE("Fire"),
    GAS_LEAK("Gas leak"),
    CAT_ON_TREE("Cat on tree"),
    UFO("UFO kidnapping"),
    SILLY_THING("Шутняры занимают линию")
}