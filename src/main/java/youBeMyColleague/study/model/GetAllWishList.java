package youBeMyColleague.study.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youBeMyColleague.study.domain.WishList;
import youBeMyColleague.study.dto.WishResponseDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllWishList {
    private boolean success;
    private String msg;
    private List<WishResponseDto> data;
}
