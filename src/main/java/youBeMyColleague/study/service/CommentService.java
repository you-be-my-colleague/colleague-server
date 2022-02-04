package youBeMyColleague.study.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.dto.CommentRequestDto;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private CommentService commentService;

    @Transactional
    public void createComment(CommentRequestDto commentRequestDto, Long postId, Long memberId) {

    }
}
