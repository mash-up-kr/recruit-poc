package kr.mashup.branding.recruitpoc.domain.application

import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationRepository: JpaRepository<Application, Long> {
}