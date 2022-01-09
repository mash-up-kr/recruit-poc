package kr.mashup.branding.recruitpoc.domain.application.form

data class QuestionVo(
    val questionId: Long,
    val content: String,
    val description: String,
) {
    constructor(question: Question) : this(
        questionId = question.questionId,
        content = question.content,
        description = question.description,
    )
}