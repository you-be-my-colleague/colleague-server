package youBeMyColleague.study.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class TechStack {

    private String statck1;
    private String statck2;
    private String statck3;
    private String statck4;

    protected TechStack() {
    }
}
