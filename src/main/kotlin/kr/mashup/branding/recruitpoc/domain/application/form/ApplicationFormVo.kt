package kr.mashup.branding.recruitpoc.domain.application.form

import kr.mashup.branding.recruitpoc.domain.team.TeamVo

data class ApplicationFormVo(
    val applicationFormId: Long,
    val teamVo: TeamVo,
    val questionVoList: List<QuestionVo>
) {
    constructor(applicationForm: ApplicationForm) : this(
        applicationFormId = applicationForm.applicationFormId,
        teamVo = TeamVo(applicationForm.team),
        questionVoList = applicationForm.questions.map { QuestionVo(it) },
    )
}