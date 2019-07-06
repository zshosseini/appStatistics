package tapsell.test.appStatistics.domain.repo

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import tapsell.test.appStatistics.domain.entity.AppStatistics
import java.util.*

interface AppStatisticsRepo : MongoRepository<AppStatistics, ObjectId> {

    fun findByTypeAndReportTimeBetweenOrderByReportTimeDesc(type: Int,startDate: Date?, endDate: Date? ):List<AppStatistics>?

}