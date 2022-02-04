package youBeMyColleague.study.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class TechStack {

    private String stack1;
    private String stack2;
    private String stack3;
    private String stack4;

    protected TechStack() {
    }
}