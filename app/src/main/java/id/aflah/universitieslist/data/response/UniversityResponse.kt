package id.aflah.universitieslist.data.response

import com.google.gson.annotations.SerializedName

data class UniversityResponse(
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("state-province")
    val stateProvince: String? = null,
    @SerializedName("alpha_two_code")
    val alphaTwoCode: String? = null,
    @SerializedName("domains")
    val domains: List<String>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("web_pages")
    val webPages: List<String>? = null,
)
