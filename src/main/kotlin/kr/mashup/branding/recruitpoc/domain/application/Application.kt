package kr.mashup.branding.recruitpoc.domain.application

import kr.mashup.branding.recruitpoc.domain.BaseEntity
import kr.mashup.branding.recruitpoc.domain.application.form.ApplicationForm
import kr.mashup.branding.recruitpoc.domain.member.Member
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.*

/**
 * 지원서
 */
@Entity
@EntityListeners(AuditingEntityListener::class)
class Application(
    @Id
    @GeneratedValue
    val applicationId: Long,
    @ManyToOne
    val applicationForm: ApplicationForm,
    @ManyToOne
    val member: Member,
    @OneToMany
    val answers: List<Answer> = mutableListOf(),
    var status: ApplicationStatus,
) : BaseEntity() {

}