package com.example.mysunflowerstudy.data.plant

import javax.inject.Inject
import javax.inject.Singleton

/**
 * 데이터 작업을 처리하기 위한 리포지토리 모듈입니다.
 *
 * [PlantDao]의 흐름에서 수집하는 것은 기본적으로 안전합니다.
 * Room은 코루틴을 지원하고 쿼리 실행을 주 스레드 외부로 이동합니다.
 */
@Singleton
class PlantRepository @Inject constructor(private val plantDao: PlantDao) {

    fun getPlants() = plantDao.getPlants()

    fun getPlant(plantId: String) = plantDao.getPlant(plantId)

    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int) =
        plantDao.getPlantsWithGrowZoneNumber(growZoneNumber)
}
