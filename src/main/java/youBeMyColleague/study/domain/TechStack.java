package youBeMyColleague.study.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TechStack {

    private Boolean python = false;
    private Boolean spring = false;
    private Boolean react = false;
    private Boolean node = false;
}