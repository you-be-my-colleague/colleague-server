package youBeMyColleague.study.setvice;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.domain.RecruitmentStatus;
import youBeMyColleague.study.dto.PostRequestDto;
import youBeMyColleague.study.repository.PostRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final EntityManager em;

    private final PostRepository postRepository;

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



}












