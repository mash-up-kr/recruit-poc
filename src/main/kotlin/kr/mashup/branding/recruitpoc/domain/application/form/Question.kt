package kr.mashup.branding.recruitpoc.domain.application.form

import kr.mashup.branding.recruitpoc.domain.BaseEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * 질문
 * TODO: 질문에 순서나 번호를 고정해야할지
 */
@Entity
class Question(
    @Id
    @GeneratedValue
    val questionId: Long = 0L,
    val content: String, // 질문 내용
    val description: String, // 몇자 이하, 이상 등의 조건 등 설명
) : BaseEntity() {
    constructor(createQuestionVo: CreateQuestionVo) : this(
        content = createQuestionVo.content,
        description = createQuestionVo.description,
    )
}