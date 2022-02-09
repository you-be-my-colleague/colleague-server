package youBeMyColleague.study.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import youBeMyColleague.study.domain.WishList;
import youBeMyColleague.study.dto.WishResponseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class GetAllWishList {
    private boolean success;
    private String msg;
    private List<WishResponseDto> data;
}
