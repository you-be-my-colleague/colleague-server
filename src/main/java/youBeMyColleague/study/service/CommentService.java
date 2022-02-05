package youBeMyColleague.study.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.domain.Comment;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.dto.CommentRequestDto;
import youBeMyColleague.study.repository.CommentRepository;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createComment(CommentRequestDto commentRequestDto, Long postId, Long memberId) {
        Optional<Post> findPost = postRepository.findById(postId);
        Optional<Member> findMember = memberRepository.findById(memberId);
        Comment comment = Comment.builder()
                .content(commentRequestDto.getContent())
                .commentDate(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
        findPost.get().addComment(comment);
        //findMember.get().addComment(comment);
    }
    @Transactional
    public void deleteComment(Long commentId,Long postId) {
        Optional<Comment> findComment = commentRepository.findById(commentId);
        Optional<Post> findPost = postRepository.findById(postId);
        findPost.get().deleteComment(findComment.get());
    }

    @Transactional
    public void updateComment(Long commentId,CommentRequestDto commentRequestDto) {
        commentRepository.findById(commentId).get().updateComment(commentRequestDto);
    }






}














