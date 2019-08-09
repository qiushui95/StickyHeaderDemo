package me.yangcx.demo.stickyheader.repository

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import me.yangcx.demo.stickyheader.R
import me.yangcx.demo.stickyheader.base.BaseRepository
import me.yangcx.demo.stickyheader.entity.AreaInfo

class AreaRepository(private val application: Application, private val gson: Gson) : BaseRepository() {
    private fun getAllAreaList(): List<AreaInfo> = JsonReader(application.resources.openRawResource(R.raw.area_json).reader()).use {
        gson.fromJson(it, object : TypeToken<List<AreaInfo>>() {}.type)
    }

    suspend fun getProvinceAndCity() = mutableListOf<AreaInfo>().apply {
        val allList = getAllAreaList()
        allList.filter {
            it.parentId == "0"
        }.mapTo(this) {
            it.copy(isParent = true)
        }
        val parentIdList = this.map {
            it.id
        }
        addAll(allList.filter {
            it.parentId in parentIdList
        })
    }.sortedBy {
        it.id
    }
}