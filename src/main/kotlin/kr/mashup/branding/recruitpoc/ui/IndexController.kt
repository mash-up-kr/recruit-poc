package kr.mashup.branding.recruitpoc.ui

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {
    @GetMapping
    fun index() = "index"
}