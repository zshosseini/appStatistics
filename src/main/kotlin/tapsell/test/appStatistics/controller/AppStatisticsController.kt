package tapsell.test.appStatistics.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import tapsell.test.appStatistics.controller.responses.AppStatisticsListResponse
import tapsell.test.appStatistics.services.AppStatisticsService
import java.util.*

@RestController
class AppStatisticsController  (@Autowired val appStatisticsService : AppStatisticsService){

    @GetMapping("/getStats")
    fun  getStats(@RequestParam(value = "startDate") startDate: Date, @RequestParam(value = "endDate") endDate: Date,
                  @RequestParam(value = "type") type: Int) : AppStatisticsListResponse? {
        return appStatisticsService.getStatistics(startDate, endDate, type)
    }
}