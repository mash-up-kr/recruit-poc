package kr.mashup.branding.recruitpoc.ui

import kr.mashup.branding.recruitpoc.domain.team.Team
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/web")
@Controller
class ExternalController {
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
        model.addAttribute("teams", Team.getTeams())
        return "web/team/list"
    }

    @GetMapping("/teams/{teamId}")
    fun team(
        @PathVariable teamId: Long,
        model: Model,
    ): String {
        model.addAttribute("team", Team.getTeams().first { it.teamId == teamId })
        return "web/team/detail"
    }
}