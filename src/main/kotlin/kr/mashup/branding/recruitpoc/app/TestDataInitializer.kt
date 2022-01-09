package kr.mashup.branding.recruitpoc.app

import kr.mashup.branding.recruitpoc.domain.application.form.*
import kr.mashup.branding.recruitpoc.domain.team.CreateTeamVo
import kr.mashup.branding.recruitpoc.domain.team.Team
import kr.mashup.branding.recruitpoc.domain.team.TeamService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TestDataInitializer(
    private val teamService: TeamService,
    private val applicationFormService: ApplicationFormService,
) {
    @EventListener(ApplicationReadyEvent::class)
    fun handleApplicationReadyEvent() {
        val teams = createTeams()
        createApplicationForms(teams = teams)
    }

    private fun createTeams(): List<Team> {
        return listOf(
            teamService.createTeam(CreateTeamVo(name = "디자인", description = "Product Design")),
            teamService.createTeam(CreateTeamVo(name = "아오스", description = "iOS")),
            teamService.createTeam(CreateTeamVo(name = "안드", description = "Android")),
            teamService.createTeam(CreateTeamVo(name = "노드", description = "Node")),
            teamService.createTeam(CreateTeamVo(name = "웹", description = "Web")),
            teamService.createTeam(CreateTeamVo(name = "스프링", description = "Spring")),
        )
    }

    private fun createApplicationForms(teams: List<Team>): List<ApplicationForm> {
        return teams.map {
            applicationFormService.createApplicationForm(
                CreateApplicationFormVo(
                    teamId = it.teamId,
                    createQuestionVoList = listOf(
                        CreateQuestionVo(
                            content = "이름을 알려주세요.",
                            description = "별명 금지",
                        ),
                        CreateQuestionVo(
                            content = "하는 일을 알려주세요.",
                            description = "직장 학교 등",
                        ),
                        CreateQuestionVo(
                            content = "포트폴리오 링크 제출이 필요하다면 알려주세요",
                            description = "블로그, 깃헙 등",
                        ),
                    ),
                )
            )
        }
    }
}