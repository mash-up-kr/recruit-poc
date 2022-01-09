package kr.mashup.branding.recruitpoc.ui.admin

import kr.mashup.branding.recruitpoc.domain.admin.staff.StaffService
import kr.mashup.branding.recruitpoc.domain.application.AnswerVo
import kr.mashup.branding.recruitpoc.domain.application.Application
import kr.mashup.branding.recruitpoc.domain.application.ApplicationService
import kr.mashup.branding.recruitpoc.domain.application.form.QuestionVo
import kr.mashup.branding.recruitpoc.domain.team.TeamVo
import kr.mashup.branding.recruitpoc.ui.web.ApplicationResponseDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal
import javax.servlet.http.HttpServletResponse

@RequestMapping("/admin")
@Controller
class InternalController(
    private val staffService: StaffService,
    private val applicationService: ApplicationService,
) {
    @GetMapping
    fun index() = "admin/home"

    /**
     * 지원서 목록 보기
     */
    @GetMapping("/applications")
    fun getApplications(
        principal: Principal,
        model: Model
    ): String {
        val staff = staffService.getOrCreate(kakaoMemberId = principal.name)
        val applications = applicationService.getAllApplication()
        val applicationResponseDtoList = applications.map { toApplicationResponseDto(it) }
        model.addAttribute("applicationResponseDtoList", applicationResponseDtoList)
        return "admin/application/list"
    }

    /**
     * 지원서 상세 보기
     */
    @GetMapping("/applications/{applicationId}")
    fun getApplication(
        @PathVariable applicationId: Long,
        principal: Principal,
        model: Model,
    ): String {
        val staff = staffService.getOrCreate(kakaoMemberId = principal.name)
        val application = applicationService.getApplication(applicationId = applicationId)
        val applicationResponseDto = toApplicationResponseDto(application)
        model.addAttribute("applicationResponseDto", applicationResponseDto)
        return "admin/application/detail"
    }

    // FIXME: 중복코드제거
    private fun toApplicationResponseDto(application: Application): ApplicationResponseDto = ApplicationResponseDto(
        applicationId = application.applicationId,
        team = TeamVo(application.applicationForm.team),
        questions = application.applicationForm.questions.map { QuestionVo(it) },
        answers = application.answers.map { AnswerVo(it) },
        createdAt = application.createdAt,
    )

    /**
     * 엑셀로 내보내기
     */
    @PostMapping("/applications/export")
    fun exportApplications(response: HttpServletResponse) {

    }

    /**
     * notification history 목록
     */
    @GetMapping("/notifications")
    fun getNotifications(): String {
        return ""
    }

    @GetMapping("/notifications/{notificationId}")
    fun getNotifications(
        @PathVariable notificationId: Long,
    ): String {
        return ""
    }

//    /**
//     * 문자 보내기
//     */
//    @GetMapping("/notification/sms/form")
//    fun sendSMS() {
//
//    }
//
//    /**
//     * 메일 보내기 화면
//     */
//    @GetMapping("/notification/email")
//    fun notificationEmail(): String {
//        return ""
//    }
//
//    /**
//     * 메일 보내기
//     */
//    @PostMapping("/notification/email")
//    fun sendEmail() {
//
//    }
//
//    /**
//     * 카카오톡 보내기 화면
//     */
//    @GetMapping("/notification/kakao-talk")
//    fun notificationKakaoTalk(): String {
//        return ""
//    }

}