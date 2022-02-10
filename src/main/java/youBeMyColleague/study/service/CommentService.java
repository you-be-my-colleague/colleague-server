package youBeMyColleague.study.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import youBeMyColleague.study.advice.exception.CommentNotFoundException;

import youBeMyColleague.study.advice.exception.PostNotFoundException;
import youBeMyColleague.study.advice.exception.UserNotFoundException;
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

    //코멘트 만들기
    @Transactional
    public Comment createComment(CommentRequestDto commentRequestDto, Long postId, Long memberId) {
        Optional<Post> findPost = Optional.ofNullable(postRepository.findById(postId).orElseThrow(PostNotFoundException::new));
        Optional<Member> findMember = Optional.ofNullable(memberRepository.findById(memberId).orElseThrow(UserNotFoundException::new));
        Comment comment = Comment.builder()
                .content(commentRequestDto.getContent())
                .commentDate(LocalDateTime.now())
                .build();
        commentRepository.save(comment);
        findPost.addComment(comment);
        findMember.addComment(comment);
        return comment.getId();
    }
    //코멘트 삭제
    @Transactional
    public void deleteComment(Long commentId,Long postId) {
        Optional<Comment> findComment = Optional.ofNullable(commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new));
        Optional<Post> findPost = Optional.ofNullable(postRepository.findById(postId).orElseThrow(PostNotFoundException::new));
        findPost.get().deleteComment(findComment.get());
    }

    //코멘트 수정
    @Transactional
    public Comment updateComment(Long commentId,CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        return commentRepository.findById(commentId).get().updateComment(commentRequestDto);
    }

}














