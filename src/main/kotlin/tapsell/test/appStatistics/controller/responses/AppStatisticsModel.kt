package tapsell.test.appStatistics.controller.responses

import java.io.Serializable


class AppStatisticsModel(
        var weekNum: Int?,
        var year: Int?,
        var requests: Int = 0,
        var clicks: Int = 0,
        var installs: Int = 0
) : Serializable

