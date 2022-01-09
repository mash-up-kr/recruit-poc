package kr.mashup.branding.recruitpoc.infra.toast

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ToastSmsConfig {
    @Bean
    fun toastSmsRestTemplate(): RestTemplate = RestTemplate()
}