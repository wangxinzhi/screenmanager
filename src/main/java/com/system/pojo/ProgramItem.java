package com.system.pojo;

import java.util.Comparator;
import java.util.Date;

/**
 * 节目项，存贮于系统自定义的优先级队列
 */
public class ProgramItem implements Comparable<ProgramItem>{

    private int level;//优先级
    private Program program;//节目

    public ProgramItem(int level, Program program){
        this.level = level;
        this.program = program;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ProgramItem.class -- toString.method -- Pid = ");
        builder.append(this.program.getID());
        builder.append(" level = ");
        builder.append(this.level);
        builder.append(" BeginTime = ");
        builder.append(this.program.getPBeginTime().toString());
        builder.append(" EndTime = ");
        builder.append(this.program.getPEndTime().toString());
        builder.append(" ]");
        return builder.toString();
    }

    @Override
    public int compareTo(ProgramItem o) {
        int var = 0;
        if (this.level < o.level){
            var = -1;
        }
        else if (this.level == o.level){
            if (this.program.getPBeginTime().compareTo(o.program.getPBeginTime()) < 0){
                var = -1;
            }
            else var = 1;
        }
        else var = 1;
        return var;
    }
}
