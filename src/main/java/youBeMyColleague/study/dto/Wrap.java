package youBeMyColleague.study.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Wrap<T>{
    private T data;

}
