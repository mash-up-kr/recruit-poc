package kr.mashup.branding.recruitpoc.domain.application

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
    val questionId: String,
    val teamId: String,
    val content: String, // 질문 내용
    val description: String, // 몇자 이하, 이상 등의 조건 등 설명
) : BaseEntity() {
}