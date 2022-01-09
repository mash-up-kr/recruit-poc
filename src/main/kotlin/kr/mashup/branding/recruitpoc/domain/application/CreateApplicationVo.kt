package kr.mashup.branding.recruitpoc.domain.application

data class CreateApplicationVo(
    val memberId: Long,
    val applicationFormId: Long,
    val createAnswerVoList: List<CreateAnswerVo>,
)
