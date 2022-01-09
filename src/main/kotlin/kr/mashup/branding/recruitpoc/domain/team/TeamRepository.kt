package kr.mashup.branding.recruitpoc.domain.team

import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long> {
}