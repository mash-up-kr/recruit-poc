package kr.mashup.branding.recruitpoc.domain.admin.staff

import org.springframework.data.jpa.repository.JpaRepository

interface StaffRepository: JpaRepository<Staff, Long> {
    fun findByKakaoMemberId(kakaoMemberId: String): Staff?
}