package com.example.mysunflowerstudy.data.appDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.mysunflowerstudy.data.garden.GardenPlanting
import com.example.mysunflowerstudy.data.garden.GardenPlantingDao
import com.example.mysunflowerstudy.data.plant.Plant
import com.example.mysunflowerstudy.data.plant.PlantDao
import com.example.mysunflowerstudy.util.DATABASE_NAME
import com.example.mysunflowerstudy.util.PLANT_DATA_FILENAME
import com.example.mysunflowerstudy.workers.SeedDatabaseWorker
import com.example.mysunflowerstudy.workers.SeedDatabaseWorker.Companion.KEY_FILENAME

@Database(
    entities = [
        GardenPlanting::class,
        Plant::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gardenPlantingDao(): GardenPlantingDao

    abstract fun plantDao(): PlantDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // 데이터베이스를 만들고 미리 채웁니다. 자세한 내용은 이 문서를 참조하세요.
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, DATABASE_NAME
            ).addCallback(
                object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request =
                            OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to PLANT_DATA_FILENAME))
                                .build()
                        WorkManager
                            .getInstance(context)
                            .enqueue(request)
                    }
                }
            ).build()
        }
    }
}