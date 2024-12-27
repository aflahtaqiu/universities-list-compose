package id.aflah.universitieslist.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class University(
    var name: String = "",
    var country: String = "",
    var stateProvince: String = "",
    var alphaTwoCodes: String = "",
    var domains: List<String> = emptyList(),
    var webPages: List<String> = emptyList(),
): Parcelable
