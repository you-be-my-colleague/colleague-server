package youBeMyColleague.study.dto;

import lombok.Data;
import youBeMyColleague.study.domain.WishList;

@Data
public class WishResponseDto {
    private PostResponseDto  post;

    public WishResponseDto(WishList wishList){
        this.post = new PostResponseDto(wishList.getPost());
    }
}
