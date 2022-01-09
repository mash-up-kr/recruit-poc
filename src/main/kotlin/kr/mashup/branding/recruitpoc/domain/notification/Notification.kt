package kr.mashup.branding.recruitpoc.domain.notification

import kr.mashup.branding.recruitpoc.domain.BaseEntity
import javax.persistence.*

/**
 * TODO: template 관리 필요할듯
 */
@Entity
class Notification(
    @Id
    @GeneratedValue
    val notificationId: Long,
    val sender: String,
    val recipient: String,
    @Enumerated(EnumType.STRING)
    var type: NotificationType,
    @Enumerated(EnumType.STRING)
    var status: NotificationStatus = NotificationStatus.READY,
    var retryCount: Int = 0,
) : BaseEntity()