package kr.mashup.branding.recruitpoc.domain.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByKakaoMemberId(kakaoMemberId: String): Member?
}