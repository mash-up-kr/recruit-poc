package kr.mashup.branding.recruitpoc.domain.application

import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationRepository: JpaRepository<Application, Long> {
    fun findByMember_memberId(memberId: Long): List<Application>
    fun findByMember_memberIdAndApplicationId(memberId: Long, applicationId: Long): Application?
}