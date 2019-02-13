package com.system.pojo;

/**
 * Program前端转换类
 */
public class ProgramCustom{

    private Program program = null;
    private String PTypeStr = null;
    private String PJudgeStr = null;

    public ProgramCustom(Program program){
        this.program = program;
        if (this.program.getPType() == 0){
            this.PTypeStr = "图片";
        }else if (this.program.getPType() == 1){
            this.PTypeStr = "视频";
        }else if (this.program.getPType() == 2){
            this.PTypeStr = "文档";
        }else {
            this.PTypeStr = "类型未知错误";
        }

        if (this.program.getPJudge() == 0){
            this.PJudgeStr = "节目未审核";
        }else if (this.program.getPJudge() == 1){
            this.PJudgeStr = "审核通过";
        }else if (this.program.getPJudge() == 2){
            this.PJudgeStr = "审核未通过";
        }else if (this.program.getPJudge() == 3){
            this.PJudgeStr = "发布成功";
        }else {
            this.PJudgeStr = "审核未知错误";
        }
    }

    public Program getProgram() {
        return program;
    }

    public String getPTypeStr(){
        return this.PTypeStr;
    }

    public String getPJudgeStr(){
        return this.PJudgeStr;
    }

    public void setProgram(Program program){
        this.program=program;
    }

    public void setPTypeStr(String PTypeStr){
        this.PTypeStr=PTypeStr;
    }

    public void setPJudgeStr(String PJudgeStr){
        this.PJudgeStr=PJudgeStr;
    }

}
