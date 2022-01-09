package kr.mashup.branding.recruitpoc.domain.application

import kr.mashup.branding.recruitpoc.domain.application.form.ApplicationFormService
import kr.mashup.branding.recruitpoc.domain.member.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface ApplicationService {
    fun createApplication(createApplicationVo: CreateApplicationVo): Application
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
}