package kr.mashup.branding.recruitpoc.domain.application.form

import kr.mashup.branding.recruitpoc.domain.BaseEntity
import kr.mashup.branding.recruitpoc.domain.team.Team
import javax.persistence.*

/**
 * 지원서 form
 */
@Entity
class ApplicationForm(
    @Id
    @GeneratedValue
    val applicationFormId: Long = 0L,
    @ManyToOne
    @JoinColumn(name = "teamId")
    val team: Team,
    @OneToMany
    @JoinColumn(name = "applicationFormId")
    val questions: List<Question> = mutableListOf(),
) : BaseEntity()