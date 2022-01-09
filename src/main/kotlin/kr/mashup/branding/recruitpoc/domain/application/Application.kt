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
    val applicationId: Long = 0L,
    @ManyToOne
    @JoinColumn(name = "applicationFormId")
    val applicationForm: ApplicationForm,
    @ManyToOne
    @JoinColumn(name = "memberId")
    val member: Member,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "applicationId")
    val answers: List<Answer> = mutableListOf(),
    // TODO: 임시저장 없으면 상태 필요없음
    @Enumerated(EnumType.STRING)
    var status: ApplicationStatus = ApplicationStatus.COMPLETED,
) : BaseEntity() {

}