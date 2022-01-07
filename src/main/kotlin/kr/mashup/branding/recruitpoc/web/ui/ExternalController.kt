package kr.mashup.branding.recruitpoc.web.ui

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/web")
@Controller
class ExternalController {
    @GetMapping
    fun index() = "web/home"
}