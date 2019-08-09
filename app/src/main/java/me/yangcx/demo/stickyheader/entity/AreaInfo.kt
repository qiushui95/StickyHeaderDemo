package me.yangcx.demo.stickyheader.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AreaInfo(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("parentId")
        val parentId: String,
        val isParent: Boolean = false
)