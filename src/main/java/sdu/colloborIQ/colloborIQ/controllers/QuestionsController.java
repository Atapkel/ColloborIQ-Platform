package sdu.colloborIQ.colloborIQ.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sdu.colloborIQ.colloborIQ.dao.CommentDAO;
import sdu.colloborIQ.colloborIQ.dao.QuestionDAO;
import sdu.colloborIQ.colloborIQ.model.Comment;
import sdu.colloborIQ.colloborIQ.model.Question;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionsController {
    private final QuestionDAO questionDAO;
    private final CommentDAO commentDAO;

    public QuestionsController(QuestionDAO questionDAO, CommentDAO commentDAO) {
        this.questionDAO = questionDAO;
        this.commentDAO = commentDAO;
    }

    @GetMapping()
    public String questions(Model model) {
        model.addAttribute("questions", questionDAO.index());
        return "question/questions";
    }

    @PostMapping("/{id}")
    public String addCommentById(@PathVariable("id") int questionId,
                                 @ModelAttribute("commentToAdd") Comment comment,
                                 @ModelAttribute("question") Question question) {
        commentDAO.saveByQuestionId(questionId, comment);
        return "redirect:/questions/"+question.getId();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Question question = questionDAO.show(id);
        List<Comment> comments = commentDAO.getCommentsByQuestionId(id);
        model.addAttribute("question", question);
        model.addAttribute("comments", comments);
        model.addAttribute("commentToAdd", new Comment());
        return "question/question-detail";
    }

    @GetMapping("/new")
    public String askQuestion(Model model) {
        model.addAttribute("question", new Question());
        return "question/post-question";
    }

    @PostMapping()
    public String postQuestion(@ModelAttribute("question") Question question) {
        questionDAO.save(question);
        return "redirect:/questions";
    }


}
