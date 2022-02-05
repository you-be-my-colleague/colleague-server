package youBeMyColleague.study.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.domain.RecruitmentStatus;
import youBeMyColleague.study.dto.PostRequestDto;
import youBeMyColleague.study.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;

    @Transactional
    public Post createPost(PostRequestDto postRequestDto, Member member) {
        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .stack(postRequestDto.getStack())
                .gitAddress(postRequestDto.getGitAddress())
                .postDate(LocalDateTime.now())
                .postStatus(RecruitmentStatus.CONTINUE)
                .member(member)
                .build();
        return postRepository.save(post);
    }
    //게시글 상세
    public Post findPost(Long postId) {
        return postRepository.findById(postId).get();
    }
    //전체 게시글 불러오기
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    //게시글 수정
    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto) {
        Optional<Post> post = postRepository.findById(postId);
        post.get().updatePost(postRequestDto);
    }

    //게시글 상태 수정
    @Transactional
    public void updatePostStatus(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        post.get().updatePostStatus();
    }




}












