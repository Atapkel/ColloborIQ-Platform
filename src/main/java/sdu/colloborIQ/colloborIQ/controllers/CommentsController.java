package sdu.colloborIQ.colloborIQ.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sdu.colloborIQ.colloborIQ.model.Comment;
import sdu.colloborIQ.colloborIQ.services.CommentsService;
@Controller
@RequestMapping("/comments")
public class CommentsController {
    private CommentsService commentsService;
    public CommentsController(CommentsService commentsService){
        this.commentsService = commentsService;
    }

    @PostMapping("/{id}/upvote")
    public String upVoteComment(@PathVariable int id) {
        commentsService.upVote(id);
        Comment comment = commentsService.findById(id).orElse(null);
        int questionId = comment.getQuestion().getId();
        return "redirect:/questions/"+questionId;
    }

    @PostMapping("/{id}/downvote")
    public String downVoteComment(@PathVariable int id) {
        commentsService.downVote(id);
        Comment comment = commentsService.findById(id).orElse(null);
        int questionId = comment.getQuestion().getId();
        return "redirect:/questions/"+questionId;
    }
}
