package kr.mashup.branding.recruitpoc.domain.application

import kr.mashup.branding.recruitpoc.domain.BaseEntity
import kr.mashup.branding.recruitpoc.domain.application.form.Question
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

/**
 * 답변
 */
@Entity
class Answer(
    @Id
    @GeneratedValue
    val answerId: Long = 0L,
    // TODO: 글자수 늘려야함 (10000 자? )
    // TODO: XSS
    val content: String,
    @ManyToOne
    val question: Question,
) : BaseEntity()