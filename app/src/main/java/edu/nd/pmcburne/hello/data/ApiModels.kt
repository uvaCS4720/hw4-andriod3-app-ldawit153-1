package edu.nd.pmcburne.hello

import com.google.gson.annotations.SerializedName

data class ApiLocation(
    val id: Int,
    val name: String,
    val description: String,
    @SerializedName("tag_list") val tagList: List<String>,
    @SerializedName("visual_center") val visualCenter: VisualCenter
)

data class VisualCenter(
    val latitude: Double,
    val longitude: Double
)

fun ApiLocation.toEntity() = LocationEntity(
    id = id,
    name = name,
    description = description,
    tags = tagList.joinToString(","),
    latitude = visualCenter.latitude,
    longitude = visualCenter.longitude
)