package kr.mashup.branding.recruitpoc.ui

import kr.mashup.branding.recruitpoc.domain.application.AnswerVo
import kr.mashup.branding.recruitpoc.domain.application.form.QuestionVo
import kr.mashup.branding.recruitpoc.domain.team.TeamVo
import java.time.LocalDateTime

// TODO: vo 들 dto 로 변경
data class ApplicationResponseDto(
    val applicationId: Long,
    val team: TeamVo,
    val questions: List<QuestionVo>,
    val answers: List<AnswerVo>,
    val createdAt: LocalDateTime,
)