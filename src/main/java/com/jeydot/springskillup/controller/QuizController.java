package com.jeydot.springskillup.controller;

import com.jeydot.springskillup.model.Question;
import com.jeydot.springskillup.service.QuestionsService;
import com.jeydot.springskillup.service.QuizUserDetailsService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuizController {
    private final QuestionsService questionsService;
    private final QuizUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    public QuizController(QuestionsService questionsService1, QuizUserDetailsService userDetailsService1, AuthenticationManager authenticationManager1) {
        this.questionsService = questionsService1;
        this.userDetailsService = userDetailsService1;
        this.authenticationManager = authenticationManager1;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("/registration")
    public String registerUserAccount() {
        return "redirect:/home";
    }
    @GetMapping("/add-quiz")
    public String addQuiz(Model model) {
        model.addAttribute("question", new Question());
        return "add-quiz";
    }
    @PostMapping("/add-quiz")
    public String saveQuiz(@Valid @ModelAttribute Question question, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-quiz";
        }
        questionsService.addQuiz(question);
        return "redirect:/home";
    }
    @DeleteMapping("/delete-quiz")
    public String deleteQuiz(Model model) {
        model.addAttribute("Message", "Quiz Deleted");
        return "redirect:/home";
    }
    @GetMapping("/edit-quiz")
    public String editQuiz(Model model) {
        return "edit-quiz";
    }
    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }
    @PostMapping("/submit-answer")
    @GetMapping("/results")
}
