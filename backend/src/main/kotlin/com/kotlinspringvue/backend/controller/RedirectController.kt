package com.kotlinspringvue.backend.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
class RedirectController {
    @GetMapping("/doc")
    fun redirectWithUsingRedirectView(
            attributes: RedirectAttributes): RedirectView {
        return RedirectView("https://docs.google.com/document/d/1KnjO1qWMbv4pG6HNB68EacgB2ZdPl0GoP9xhfubTvE4/edit?usp=sharing")
    }
}