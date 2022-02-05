package youBeMyColleague.study.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class TechStack {

    private Boolean python;
    private Boolean spring;
    private Boolean react;
    private Boolean node;

    protected TechStack() {
    }
}