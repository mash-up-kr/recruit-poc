package kr.mashup.branding.recruitpoc.domain.application.form

import kr.mashup.branding.recruitpoc.domain.team.TeamVo

data class ApplicationFormVo(
    val applicationFormId: Long,
    val team: TeamVo,
    val questions: List<QuestionVo>
) {
    constructor(applicationForm: ApplicationForm) : this(
        applicationFormId = applicationForm.applicationFormId,
        team = TeamVo(applicationForm.team),
        questions = applicationForm.questions.map { QuestionVo(it) },
    )
}