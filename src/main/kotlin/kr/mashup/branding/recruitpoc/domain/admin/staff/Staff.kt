package kr.mashup.branding.recruitpoc.domain.admin.staff

import kr.mashup.branding.recruitpoc.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Staff (
    @Id
    @GeneratedValue
    val staffId: Long = 0L,
    val kakaoMemberId: String,
): BaseEntity()