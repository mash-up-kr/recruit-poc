package kr.mashup.branding.recruitpoc.ui

import kr.mashup.branding.recruitpoc.domain.application.ApplicationService
import kr.mashup.branding.recruitpoc.domain.application.CreateAnswerVo
import kr.mashup.branding.recruitpoc.domain.application.CreateApplicationVo
import kr.mashup.branding.recruitpoc.domain.application.form.ApplicationFormService
import kr.mashup.branding.recruitpoc.domain.application.form.ApplicationFormVo
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
        val kakaoMemberId = principal.name
        val member = memberService.getOrCreate(kakaoMemberId)
        applicationService.createApplication(
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
        return "web/home" // TODO: 내 지원서 페이지
    }
}