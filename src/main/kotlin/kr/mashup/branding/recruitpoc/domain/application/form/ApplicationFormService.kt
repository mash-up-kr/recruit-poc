package kr.mashup.branding.recruitpoc.domain.application.form

import kr.mashup.branding.recruitpoc.domain.team.TeamService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface ApplicationFormService {
    fun createApplicationForm(createApplicationFormVo: CreateApplicationFormVo): ApplicationForm
    fun getApplicationFormById(applicationFormId: Long): ApplicationForm
    fun getApplicationFormByTeamId(teamId: Long): ApplicationForm
}

@Service
@Transactional(readOnly = true)
class ApplicationFormServiceImpl(
    private val applicationFormRepository: ApplicationFormRepository,
    private val teamService: TeamService,
    private val questionService: QuestionService,
) : ApplicationFormService {

    @Transactional
    override fun createApplicationForm(createApplicationFormVo: CreateApplicationFormVo): ApplicationForm {
        val team = teamService.getTeam(teamId = createApplicationFormVo.teamId)
        val applicationForm = ApplicationForm(
            team = team,
            questions = createApplicationFormVo.createQuestionVoList.map {
                questionService.createQuestion(it)
            }
        )
        return applicationFormRepository.save(applicationForm)
    }

    override fun getApplicationFormById(applicationFormId: Long): ApplicationForm {
        return applicationFormRepository.findByIdOrNull(applicationFormId) ?: throw ApplicationFormNotFoundException()
    }

    override fun getApplicationFormByTeamId(teamId: Long): ApplicationForm {
        return applicationFormRepository.findByTeam_teamId(teamId) ?: throw ApplicationFormNotFoundException()
    }
}