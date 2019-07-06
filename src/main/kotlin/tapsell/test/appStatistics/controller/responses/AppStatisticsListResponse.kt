package tapsell.test.appStatistics.controller.responses

import java.io.Serializable

class AppStatisticsListResponse (
    var stats: List<AppStatisticsModel>
) : Serializable