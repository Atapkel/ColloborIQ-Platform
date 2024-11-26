package sdu.colloborIQ.colloborIQ.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sdu.colloborIQ.colloborIQ.dto.CommentDTO;
import sdu.colloborIQ.colloborIQ.dto.QuestionDTO;
import sdu.colloborIQ.colloborIQ.model.Comment;
import sdu.colloborIQ.colloborIQ.model.Question;
import sdu.colloborIQ.colloborIQ.model.Sdudent;
import sdu.colloborIQ.colloborIQ.repository.CommentsRepository;
import sdu.colloborIQ.colloborIQ.services.CommentsService;
import sdu.colloborIQ.colloborIQ.services.QuestionsService;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionsController {
    private final QuestionsService questionsService;
    private final CommentsService commentsService;

    public QuestionsController(QuestionsService questionsService, CommentsService commentsService) {
        this.questionsService = questionsService;
        this.commentsService = commentsService;
    }

    @GetMapping()
    public String questions(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("questions",
                    questionsService.searchWithKeyWord(keyword));
        } else
            model.addAttribute("questions", questionsService.findAll());
        return "question/questions";
    }

    @PostMapping("/{id}")
    public String addCommentById(@PathVariable("id") int questionId,
                                 @ModelAttribute("commentToAdd") CommentDTO commentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        commentDTO.setAuthor(userDetails.getUsername());
        commentsService.saveByQuestionId(questionId, commentDTO);
        return "redirect:/questions/" + questionId;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Question question = questionsService.findById(id).orElse(null);
        List<Comment> comments = commentsService.getCommentsByQuestionId(id);
        model.addAttribute("question", question);
        model.addAttribute("comments", comments);
        model.addAttribute("commentToAdd", new CommentDTO());
        return "question/question-detail";
    }


    @GetMapping("/new")
    public String askQuestion(Model model) {
        model.addAttribute("questionDTO", new QuestionDTO());
        return "question/post-question";
    }

    @PostMapping()
    public String postQuestion(@ModelAttribute("question") QuestionDTO questionDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        questionDTO.setAuthor(userDetails.getUsername());
        Question questionToSave = new Question();
        questionToSave.setQuestion(questionDTO.getQuestion());
        questionToSave.setAuthor(questionDTO.getAuthor());
        questionsService.save(questionToSave);
        return "redirect:/questions";
    }


}
