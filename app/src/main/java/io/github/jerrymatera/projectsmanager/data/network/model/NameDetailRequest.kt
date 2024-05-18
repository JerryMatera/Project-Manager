package io.github.jerrymatera.projectsmanager.data.network.model


import com.google.gson.annotations.SerializedName

data class NameDetailRequest(
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String
)