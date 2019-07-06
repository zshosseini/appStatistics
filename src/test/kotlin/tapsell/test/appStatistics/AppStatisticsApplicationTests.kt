package tapsell.test.appStatistics

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import tapsell.test.appStatistics.controller.AppStatisticsController
import tapsell.test.appStatistics.services.AppStatisticsService
import tapsell.test.appStatistics.utils.DateTimeUtil
import java.sql.Timestamp
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest
@ActiveProfiles("dev")


class AppStatisticsApplicationTests(@Autowired val appStatisticsService: AppStatisticsService,
                                    @Autowired val appStatisticsController: AppStatisticsController) {


    @Test
    fun getAppStatistics() {
        var response = appStatisticsService.getStatistics(Timestamp.valueOf(LocalDateTime.parse("2016-03-10T02:02:02.696"))
                , Timestamp.valueOf(LocalDateTime.parse("2016-03-30T20:02:02.696")), 0)
        Assertions.assertEquals(1395, response.stats!![0].year)
        Assertions.assertEquals(1, response.stats!![0].weekNum)
        Assertions.assertEquals(18, response.stats!![0].requests)
        Assertions.assertEquals(14, response.stats!![0].clicks)
        Assertions.assertEquals(10, response.stats!![0].installs)
        Assertions.assertEquals(1395, response.stats!![1].year)
        Assertions.assertEquals(2, response.stats!![1].weekNum)
        Assertions.assertEquals(25, response.stats!![1].requests)
        Assertions.assertEquals(21, response.stats!![1].clicks)
        Assertions.assertEquals(17, response.stats!![1].installs)

        response = appStatisticsService.getStatistics(Timestamp.valueOf(LocalDateTime.parse("2017-03-15T21:02:02.696"))
                , Timestamp.valueOf(LocalDateTime.parse("2017-03-23T22:02:02.696")), 0)
        Assertions.assertEquals(1395, response.stats!![0].year)
        Assertions.assertEquals(52, response.stats!![0].weekNum)
        Assertions.assertEquals(11, response.stats!![0].requests)
        Assertions.assertEquals(9, response.stats!![0].clicks)
        Assertions.assertEquals(7, response.stats!![0].installs)
        Assertions.assertEquals(1395, response.stats!![1].year)
        Assertions.assertEquals(53, response.stats!![1].weekNum)
        Assertions.assertEquals(22, response.stats!![1].requests)
        Assertions.assertEquals(18, response.stats!![1].clicks)
        Assertions.assertEquals(14, response.stats!![1].installs)
        Assertions.assertEquals(1396, response.stats!![2].year)
        Assertions.assertEquals(1, response.stats!![2].weekNum)
        Assertions.assertEquals(11, response.stats!![2].requests)
        Assertions.assertEquals(9, response.stats!![2].clicks)
        Assertions.assertEquals(7, response.stats!![2].installs)

        response = appStatisticsController.getStats(Timestamp.valueOf(LocalDateTime.parse("2019-04-21T20:02:02.696"))
                , Timestamp.valueOf(LocalDateTime.parse("2019-04-29T20:02:02.696")), 2)!!
        Assertions.assertEquals(1398, response.stats!![0].year)
        Assertions.assertEquals(6, response.stats!![0].weekNum)
        Assertions.assertEquals(13, response.stats!![0].requests)
        Assertions.assertEquals(11, response.stats!![0].clicks)
        Assertions.assertEquals(9, response.stats!![0].installs)
        Assertions.assertEquals(1398, response.stats!![1].year)
        Assertions.assertEquals(7, response.stats!![1].weekNum)
        Assertions.assertEquals(10, response.stats!![1].requests)
        Assertions.assertEquals(8, response.stats!![1].clicks)
        Assertions.assertEquals(6, response.stats!![1].installs)
    }

}
