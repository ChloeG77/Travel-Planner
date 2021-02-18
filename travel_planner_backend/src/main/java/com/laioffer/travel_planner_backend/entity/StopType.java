package com.laioffer.travel_planner_backend.entity;


import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StopType implements Serializable {

    private static final long serialVersionUID = -6793718607058900042L;

    private Boolean isStart = Boolean.FALSE;

    private Boolean isEnd = Boolean.FALSE;

    private Boolean isPriority = Boolean.FALSE;

    public Boolean getStart() {
        return isStart;
    }

    public void setStart(Boolean start) {
        isStart = start;
    }

    public Boolean getEnd() {
        return isEnd;
    }

    public void setEnd(Boolean end) {
        isEnd = end;
    }

    public Boolean getPriority() {
        return isPriority;
    }

    public void setPriority(Boolean priority) {
        isPriority = priority;
    }
}
