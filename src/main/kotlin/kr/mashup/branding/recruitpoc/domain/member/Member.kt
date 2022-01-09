package kr.mashup.branding.recruitpoc.domain.member

import kr.mashup.branding.recruitpoc.domain.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member(
    @Id
    @GeneratedValue
    val memberId: Long = 0L,
    var name: String,
    @Column(unique = true)
    val kakaoMemberId: String,
) : BaseEntity()