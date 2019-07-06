package tapsell.test.appStatistics.config

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tapsell.test.appStatistics.domain.data.AppTypes
import tapsell.test.appStatistics.domain.entity.AppStatistics
import tapsell.test.appStatistics.domain.repo.AppStatisticsRepo
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

@Configuration
class DBInitializer {

    @Bean
    fun appStatisticsInitializer(appRepo : AppStatisticsRepo) = ApplicationRunner {

        appRepo.deleteAll()
        appRepo.save(makeApp(4,5, AppTypes.Game, Timestamp.valueOf(LocalDateTime.parse("2016-03-20T02:02:02.696"))))  // یکشبنه یک فروردین 95
        appRepo.save(makeApp(4,5, AppTypes.Game, Timestamp.valueOf(LocalDateTime.parse("2016-03-25T23:59:02.696"))))  // جمعه 6 فروردین 95
        appRepo.save(makeApp(5,5, AppTypes.Game, Timestamp.valueOf(LocalDateTime.parse("2016-03-26T20:02:02.696"))))  // شنبه 7 فروردین 95
        appRepo.save(makeApp(10,5, AppTypes.Game, Timestamp.valueOf(LocalDateTime.parse("2016-03-27T20:02:02.696"))))  // یکشبنه 8 فروردین 95
        appRepo.save(makeApp(6,5, AppTypes.Game, Timestamp.valueOf(LocalDateTime.parse("2017-03-16T21:02:02.696"))))   // پنجشنبه 26 اسفند 95
        appRepo.save(makeApp(6,5, AppTypes.Game, Timestamp.valueOf(LocalDateTime.parse("2017-03-19T21:02:02.696"))))   // یکشبنه 29 اسفند 95
        appRepo.save(makeApp(6,5, AppTypes.Game, Timestamp.valueOf(LocalDateTime.parse("2017-03-20T21:02:02.696"))))   // دوشنبه 30 اسفند 95
        appRepo.save(makeApp(6,5, AppTypes.Game, Timestamp.valueOf(LocalDateTime.parse("2017-03-21T22:02:02.696"))))   // سه شنبه 1 فروردین 96
        appRepo.save(makeApp(6,7, AppTypes.Payment, Timestamp.valueOf(LocalDateTime.parse("2019-04-24T20:02:02.696"))))  // 4 اردیبهشت 98 هفته 6ام
        appRepo.save(makeApp(7,3, AppTypes.Payment, Timestamp.valueOf(LocalDateTime.parse("2019-04-27T20:02:02.696"))))  //7 اردیبهشت 98 هفته 7 ام
}
    private fun makeApp(video: Int, web:Int, type:AppTypes, reportTime:Date): AppStatistics{
        var app = AppStatistics(reportTime, type.ordinal)
        app.videoRequests=video
        app.videoClicks=video-1
        app.videoInstalls=video-2
        app.webViewRequests=web
        app.webviewClicks=web-1
        app.webviewInstalls=web-2
        return app
    }
}