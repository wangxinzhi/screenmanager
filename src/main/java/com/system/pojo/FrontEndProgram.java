package com.system.pojo;

public class FrontEndProgram extends Program {

    private String PTypeStr = null;//节目类型 string  0 => 图片; 1 => 视频; 2 => 文档
    private String PJudgeStr = null;//节目状态 string  0 => 节目未审核; 1 => 审核通过; 2 => 审核未通过; 3 => 发布成功;

    public FrontEndProgram(){
        super();
    }

    public String getPTypeStr() {
        return PTypeStr;
    }

    public void setPTypeStr() {
        if (this.getPType() == 0){
            this.PTypeStr = "图片";
        }else if (this.getPType() == 1){
            this.PTypeStr = "视频";
        }else if (this.getPType() == 2){
            this.PTypeStr = "文档";
        }else {
            this.PTypeStr = "类型未知错误";
        }
    }

    public String getPJudgeStr() {
        return PJudgeStr;
    }

    public void setPJudgeStr() {
        if (this.getPJudge() == 0){
            this.PJudgeStr = "节目未审核";
        }else if (this.getPJudge() == 1){
            this.PJudgeStr = "审核通过";
        }else if (this.getPJudge() == 2){
            this.PJudgeStr = "审核未通过";
        }else if (this.getPJudge() == 3){
            this.PJudgeStr = "发布成功";
        }else {
            this.PJudgeStr = "审核未知错误";
        }
    }
}
