package tapsell.test.appStatistics.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import tapsell.test.appStatistics.controller.responses.AppStatisticsListResponse
import tapsell.test.appStatistics.controller.responses.AppStatisticsModel
import tapsell.test.appStatistics.domain.entity.AppStatistics
import tapsell.test.appStatistics.domain.repo.AppStatisticsRepo
import tapsell.test.appStatistics.utils.DateTimeUtil
import java.util.*

@Component
class AppStatisticsService(@Autowired val appStatisticsRepo: AppStatisticsRepo,
                           @Autowired var dateTimeUtil: DateTimeUtil) {


    @Cacheable(cacheNames = ["AppStats"], key = "'startDate: ' + #a0 + ' endDate: ' + #a1 + ' type: ' + #a2", unless = "#result == null")
    fun getStatistics(startDate: Date?, endDate: Date?, type: Int): AppStatisticsListResponse {
        var appStatistics: List<AppStatistics>? = appStatisticsRepo.findByTypeAndReportTimeBetweenOrderByReportTimeDesc(type, startDate, endDate)
        return getWeeklyStats(appStatistics)
    }

    fun getWeeklyStats(appStatistics: List<AppStatistics>?): AppStatisticsListResponse {
        var appStatisticsListResponse = AppStatisticsListResponse(ArrayList())
        if (appStatistics != null) {
            appStatistics.forEach {
                var week = dateTimeUtil.getPersianWeek(it.reportTime)
                var year = dateTimeUtil.getPersianYear(it.reportTime)
                var appModel: AppStatisticsModel
                appModel = AppStatisticsModel(week, year, it.webViewRequests + it.videoRequests,
                        it.webviewClicks + it.videoClicks, it.webviewInstalls + it.videoInstalls)
                appStatisticsListResponse.stats += appModel
            }
        }
        appStatisticsListResponse.stats = appStatisticsListResponse.stats.groupBy { Key(it.weekNum, it.year) }.values.map {
            it.reduce { a1, app -> AppStatisticsModel(app.weekNum, app.year, a1.requests + app.requests, a1.clicks + app.clicks, a1.installs + app.installs) }
        }.sortedWith(compareBy(AppStatisticsModel::year, AppStatisticsModel::weekNum))

        return appStatisticsListResponse
    }
}

data class Key(val weekNum: Int?, val year: Int?)