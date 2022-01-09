package kr.mashup.branding.recruitpoc.domain.application

data class AnswerVo(
    val answerId: Long,
    val questionId: Long,
    val content: String,
) {
    constructor(answer: Answer) : this(
        answerId = answer.answerId,
        questionId = answer.question.questionId,
        content = answer.content,
    )
}