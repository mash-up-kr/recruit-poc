package kr.mashup.branding.recruitpoc.domain.member

import kr.mashup.branding.recruitpoc.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member(
    @Id
    @GeneratedValue
    val memberId: Long,
    var name: String,
): BaseEntity()