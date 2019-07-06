package tapsell.test.appStatistics.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Document(collection="appStatistics")
data class  AppStatistics (
        var reportTime: Date ,
        var type: Int
){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    lateinit var id: String
    var videoRequests: Int = 0
    var webViewRequests: Int = 0
    var videoClicks: Int = 0
    var webviewClicks: Int = 0
    var videoInstalls: Int = 0
    var webviewInstalls: Int = 0
}
