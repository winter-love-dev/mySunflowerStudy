package com.example.mysunflowerstudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mysunflowerstudy.data.plant.Plant
import com.example.mysunflowerstudy.data.plant.PlantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for [PlantListFragment].
 */
@HiltViewModel
class PlantListViewModel @Inject internal constructor(
    plantRepository: PlantRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val growZone: MutableStateFlow<Int> = MutableStateFlow(
        savedStateHandle[GROW_ZONE_SAVED_STATE_KEY] ?: NO_GROW_ZONE
    )

    val plants: LiveData<List<Plant>> = growZone.flatMapLatest { zone ->
        if (zone == NO_GROW_ZONE) {
            plantRepository.getPlants()
        } else {
            plantRepository.getPlantsWithGrowZoneNumber(zone)
        }
    }.asLiveData()

    init {

        /**
         * When `growZone` changes, store the new value in `savedStateHandle`.
         *
         * There are a few ways to write this; all of these are equivalent. (This info is from
         * https://github.com/android/sunflower/pull/671#pullrequestreview-548900174)
         *
         * 1) A verbose version:
         *
         *    viewModelScope.launch {
         *        growZone.onEach { newGrowZone ->
         *            savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY, newGrowZone)
         *        }
         *    }.collect()
         *
         * 2) 1)의 간단한 버전입니다.
         *    'collect'를 호출하기 때문에 'on Each' 연산자를 사용하는 대신 'collect'의 람다 블록에 있는 요소를 사용할 수 있습니다.
         *    아래 라이브 코드에서 사용되는 버전입니다.
         *
         * 3) 'LaunchIn' 터미널 연산자를 사용하여 새로운 코루틴을 만드는 것을 피할 수 있습니다.
         *     이 경우 'onEach'는 흐름의 새 요소를 사용하기 위해 람다를 사용하지 않으므로 'onEach'가 필요합니다.
         *     내부적으로 코루틴을 만드는데 사용되는 '코루틴 범위'가 필요합니다.
         *
         *    growZone.onEach { newGrowZone ->
         *        savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY, newGrowZone)
         *    }.launchIn(viewModelScope)
         */
        viewModelScope.launch {
            growZone.collect { newGrowZone ->
                savedStateHandle[GROW_ZONE_SAVED_STATE_KEY] = newGrowZone
            }
        }
    }

    fun setGrowZoneNumber(num: Int) {
        growZone.value = num
    }

    fun clearGrowZoneNumber() {
        growZone.value = NO_GROW_ZONE
    }

    fun isFiltered() = growZone.value != NO_GROW_ZONE

    companion object {
        private const val NO_GROW_ZONE = -1
        private const val GROW_ZONE_SAVED_STATE_KEY = "GROW_ZONE_SAVED_STATE_KEY"
    }
}
