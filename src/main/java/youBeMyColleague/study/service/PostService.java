package youBeMyColleague.study.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.advice.exception.PostNotFoundException;
import youBeMyColleague.study.advice.exception.UserNotFoundException;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.domain.RecruitmentStatus;
import youBeMyColleague.study.dto.PostRequestDto;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createPost(PostRequestDto postRequestDto, Long createrId) {
        Member member = memberRepository.findById(createrId).orElseThrow(UserNotFoundException::new);
        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .stack(postRequestDto.getStack())
                .gitAddress(postRequestDto.getGitAddress())
                .postDate(LocalDateTime.now())
                .postStatus(RecruitmentStatus.CONTINUE)
                .member(member)
                .build();
        return postRepository.save(post).getId();
    }
    //게시글 상세
    public Optional<List<Post>> findPost(Long postId) {
        List<Post> postWithAllComment = postRepository.findPostWithAllComment(postId).orElseThrow(PostNotFoundException::new);
        postWithAllComment.get(0).addViewsCount(); //조회수
        return Optional.of(postWithAllComment);
    }
    //전체 게시글 불러오기
    public Optional<List<Post>> findAllPost() {
        List<Post> all = Optional.of(postRepository.findAll()).orElseThrow(PostNotFoundException::new);
        return Optional.of(all);
    }
    //게시글 수정
    @Transactional
    public Optional<Post> updatePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        return Optional.of(post);
    }

    //게시글 상태 수정
    @Transactional
    public Optional<Post> updatePostStatus(Long postId,PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);;
        post.updatePostStatus(postRequestDto);
        return Optional.of(post);
    }

    //게시글 삭제
    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }





}












