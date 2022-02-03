package youBeMyColleague.study.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class TechStack {

    private String python;
    private String spring;
    private String react;
    private String node;

    protected TechStack() {
    }
}
