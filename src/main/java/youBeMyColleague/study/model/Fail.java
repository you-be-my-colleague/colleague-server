package youBeMyColleague.study.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fail {

    private boolean success;
    private String msg;

    public Fail(String msg) {
        this.success = false;
        this.msg = msg;
    }

}

