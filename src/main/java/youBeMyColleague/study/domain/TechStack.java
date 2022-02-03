package youBeMyColleague.study.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class TechStack {

    private boolean python;
    private boolean spring;
    private boolean react;
    private boolean node;

    protected TechStack() {
    }
}
