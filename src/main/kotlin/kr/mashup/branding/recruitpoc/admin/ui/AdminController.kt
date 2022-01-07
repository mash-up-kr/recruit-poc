package kr.mashup.branding.recruitpoc.admin.ui

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/admin")
@Controller
class InternalController {
    @GetMapping
    fun index() = "admin/home"
}