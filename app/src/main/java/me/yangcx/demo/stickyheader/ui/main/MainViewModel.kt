package me.yangcx.demo.stickyheader.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.itemBindingOf
import me.yangcx.demo.stickyheader.BR
import me.yangcx.demo.stickyheader.R
import me.yangcx.demo.stickyheader.base.BaseViewModel
import me.yangcx.demo.stickyheader.entity.AreaInfo
import me.yangcx.demo.stickyheader.repository.AreaRepository

class MainViewModel(private val areaRepository: AreaRepository) : BaseViewModel() {
    private val provinceAndCityList by lazy {
        MutableLiveData<List<AreaInfo>>()
    }

    fun provinceAndCityList(): LiveData<List<AreaInfo>> = provinceAndCityList

    fun getCurrentParent(position: Int): AreaInfo? = provinceAndCityList.value?.let { list ->
        list.getOrNull(position)?.let { current ->
            current.takeIf {
                it.isParent
            } ?: list.firstOrNull {
                it.id == current.parentId
            }
        }
    }

    fun getPositionById(id: String) = provinceAndCityList.value?.run {
        indexOfFirst {
            it.id == id
        }.takeIf {
            it >= 0
        }
    }?: 0

    init {
        viewModelScope.launch(Dispatchers.Default) {
            provinceAndCityList.postValue(areaRepository.getProvinceAndCity())
        }
    }

    val itemBinding by lazy {
        itemBindingOf<AreaInfo> { itemBinding, _, item ->
            if (item.isParent) {
                itemBinding.set(BR.data, R.layout.item_area_parent)
            } else {
                itemBinding.set(BR.data, R.layout.item_area_child)
            }
        }
    }
}