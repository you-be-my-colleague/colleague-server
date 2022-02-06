package youBeMyColleague.study.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class CommentRequestDto {

    //private Long postId; 주소창에서 데이터값 안보이게 할수있으면 없어져도됨
    private String content;
}
