package kr.mashup.branding.recruitpoc.ui

import kr.mashup.branding.recruitpoc.domain.team.Team
import org.springframework.security.core.AuthenticatedPrincipal
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.web.method.annotation.OAuth2AuthorizedClientArgumentResolver
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.security.Principal
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest

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
        principal: Principal,
        model: Model,
    ): String {
        val kakaoMemberId = principal.name
        println(kakaoMemberId)
        model.addAttribute("team", Team.getTeams().first { it.teamId == teamId })
        return "web/team/detail"
    }
}