package kr.mashup.branding.recruitpoc.domain.application.form

import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationFormRepository : JpaRepository<ApplicationForm, Long> {
    fun findByTeam_teamId(teamId: Long): ApplicationForm?
}