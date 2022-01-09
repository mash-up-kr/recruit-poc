package kr.mashup.branding.recruitpoc.domain.application

import kr.mashup.branding.recruitpoc.domain.application.form.ApplicationFormService
import kr.mashup.branding.recruitpoc.domain.member.MemberService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface ApplicationService {
    fun createApplication(createApplicationVo: CreateApplicationVo): Application
    fun getAllApplication(): List<Application>
    fun getApplications(memberId: Long): List<Application>
    fun getApplication(memberId: Long, applicationId: Long): Application
    fun getApplication(applicationId: Long): Application
}

@Service
@Transactional(readOnly = true)
class ApplicationServiceImpl(
    private val applicationRepository: ApplicationRepository,
    private val applicationFormService: ApplicationFormService,
    private val memberService: MemberService,
) : ApplicationService {
    @Transactional
    override fun createApplication(createApplicationVo: CreateApplicationVo): Application {
        val member = memberService.getMember(memberId = createApplicationVo.memberId)
        val applicationForm = applicationFormService.getApplicationFormById(
            applicationFormId = createApplicationVo.applicationFormId
        )
        val questionMap = applicationForm.questions.associateBy { it.questionId }
        val application = Application(
            applicationForm = applicationForm,
            member = member,
            answers = createApplicationVo.createAnswerVoList.map {
                Answer(
                    content = it.content,
                    question = questionMap.getValue(it.questionId), // question map 에 결과 없으면 잘못된 요청임.
                )
            },
        )
        return applicationRepository.save(application)
    }

    override fun getAllApplication(): List<Application> {
        return applicationRepository.findAll()
    }

    /**
     * 특정 회원의 지원서 목록
     */
    override fun getApplications(memberId: Long): List<Application> {
        return applicationRepository.findByMember_memberId(memberId)
    }

    override fun getApplication(memberId: Long, applicationId: Long): Application {
        return applicationRepository.findByMember_memberIdAndApplicationId(
            memberId = memberId,
            applicationId = applicationId
        ) ?: throw ApplicationNotFoundException()
    }

    override fun getApplication(applicationId: Long): Application {
        return applicationRepository.findByIdOrNull(applicationId) ?: throw ApplicationNotFoundException()
    }
}