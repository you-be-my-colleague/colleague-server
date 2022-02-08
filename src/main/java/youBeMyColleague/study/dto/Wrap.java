package youBeMyColleague.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Wrap<T>{

//    private String result;
//    private int code;
//    private String msg;
    private T data;

}
