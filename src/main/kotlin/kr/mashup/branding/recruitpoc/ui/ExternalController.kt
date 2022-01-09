package kr.mashup.branding.recruitpoc.ui

import kr.mashup.branding.recruitpoc.domain.application.*
import kr.mashup.branding.recruitpoc.domain.application.form.ApplicationFormService
import kr.mashup.branding.recruitpoc.domain.application.form.ApplicationFormVo
import kr.mashup.branding.recruitpoc.domain.application.form.QuestionVo
import kr.mashup.branding.recruitpoc.domain.member.MemberService
import kr.mashup.branding.recruitpoc.domain.team.TeamService
import kr.mashup.branding.recruitpoc.domain.team.TeamVo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RequestMapping("/web")
@Controller
class ExternalController(
    private val memberService: MemberService,
    private val teamService: TeamService,
    private val applicationFormService: ApplicationFormService,
    private val applicationService: ApplicationService,
) {
    @GetMapping
    fun index(): String {
        return "web/home"
    }

    @GetMapping("/login")
    fun login(): String = "web/login"

    @GetMapping("/teams")
    fun teams(
        model: Model,
    ): String {
        val teamVoList = teamService.findAllTeams().map { TeamVo(it) }
        model.addAttribute("teams", teamVoList)
        return "web/team/list"
    }

    @GetMapping("/teams/{teamId}")
    fun team(
        @PathVariable teamId: Long,
        principal: Principal,
        model: Model,
    ): String {
        val kakaoMemberId = principal.name
        println(kakaoMemberId)
        val teamVo = TeamVo(teamService.getTeam(teamId))
        val applicationFormVo = ApplicationFormVo(applicationFormService.getApplicationFormByTeamId(teamVo.teamId))
        model.addAttribute("team", teamVo)
        model.addAttribute("applicationForm", applicationFormVo)
        // create empty applicationRequestDto
        model.addAttribute("applicationRequestDto", ApplicationRequestDto(
            answers = applicationFormVo.questions.map {
                CreateAnswerDto(questionId = it.questionId, content = "")
            }.toMutableList(),
        ))
        return "web/team/detail"
    }

    @PostMapping("/application-forms/{applicationFormId}/applications")
    fun submit(
        @PathVariable applicationFormId: Long,
        @ModelAttribute applicationRequestDto: ApplicationRequestDto,
        principal: Principal,
    ): String {
        val member = memberService.getOrCreate(kakaoMemberId = principal.name)
        val application = applicationService.createApplication(
            CreateApplicationVo(
                memberId = member.memberId,
                applicationFormId = applicationFormId,
                createAnswerVoList = applicationRequestDto.answers!!.map {
                    CreateAnswerVo(
                        content = it.content!!,
                        questionId = it.questionId!!,
                    )
                }
            )
        )
        return "redirect:/web/applications/${application.applicationId}"
    }

    @GetMapping("/applications")
    fun getApplications(
        principal: Principal,
        model: Model,
    ): String {
        val member = memberService.getOrCreate(kakaoMemberId = principal.name)
        val applications = applicationService.getApplications(memberId = member.memberId)
        val applicationResponseDtoList = applications.map { toApplicationResponseDto(it) }
        model.addAttribute("applicationResponseDtoList", applicationResponseDtoList)
        return "web/application/list"
    }

    @GetMapping("/applications/{applicationId}")
    fun getApplication(
        @PathVariable applicationId: Long,
        principal: Principal,
        model: Model,
    ): String {
        val member = memberService.getOrCreate(kakaoMemberId = principal.name)
        val application = applicationService.getApplication(
            memberId = member.memberId,
            applicationId = applicationId
        )
        model.addAttribute("applicationResponseDto", toApplicationResponseDto(application))
        return "web/application/detail"
    }

    private fun toApplicationResponseDto(application: Application): ApplicationResponseDto = ApplicationResponseDto(
        applicationId = application.applicationId,
        team = TeamVo(application.applicationForm.team),
        questions = application.applicationForm.questions.map { QuestionVo(it) },
        answers = application.answers.map { AnswerVo(it) },
        createdAt = application.createdAt,
    )
}