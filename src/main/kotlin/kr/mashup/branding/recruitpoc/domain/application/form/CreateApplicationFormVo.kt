package kr.mashup.branding.recruitpoc.domain.application.form

data class CreateApplicationFormVo (
    val teamId: Long,
    val createQuestionVoList: List<CreateQuestionVo>,
)