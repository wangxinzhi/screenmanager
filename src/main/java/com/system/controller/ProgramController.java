package com.system.controller;

import com.system.Utils.Log4jUtil;
import com.system.Utils.UploadFileUtil;
import com.system.pojo.*;
import com.system.service.ProgramService;
import com.system.service.ScreenService;
import com.system.service.UserLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/program")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private ScreenService screenService;

    @RequestMapping("/{principal}/show")
    @RequiresPermissions("program:view")
    public String showProgram(@PathVariable(value = "principal") String username, Model model)throws Exception{
        List<ProgramCustom> programCustoms = new ArrayList<>();
        for (Program p:programService.findProgramBySender(username)) {
            ProgramCustom programCustom = new ProgramCustom(p);
            programCustoms.add(programCustom);
        }
        model.addAttribute("programCustoms",programCustoms);
        return "program/showprogram";
    }

    /**
     * 修改节目信息
     * @param pid
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/{pid}/edit")
    @RequiresPermissions("program:update")
    public String editProgram(@PathVariable(value = "pid") Integer pid, Model model)throws Exception{
        Program program = programService.getProgramByPID(pid);
        model.addAttribute("program",program);
        model.addAttribute("feedback",programService.getFeedback(pid));
        model.addAttribute("screenslist",screenService.getScreensByStr(program.getScreensList()));
        model.addAttribute("time",programService.getTimeSchedule());
        return "program/editprogram";
    }

    @RequestMapping("/{pid}/edit.do")
    @RequiresPermissions("program:update")
    public String editProgramdo(@PathVariable(value = "pid") Integer pid, UploadProgram program, HttpServletRequest request)throws Exception{
        programService.updateProgramOfFailedChecking(request,program);
        return "redirect:/program/"+ SecurityUtils.getSubject().getSession().getAttribute("principal")+"/show";
    }

    /**
     * 删除节目信息
     * @param pid
     * @return
     * @throws Exception
     */
    @RequestMapping("/{pid}/delete.do")
    @RequiresPermissions("program:delete")
    public String deleteProgramdo(@PathVariable(value = "pid") Integer pid)throws Exception{
        programService.deleteProgram(pid);
        return "redirect:/program/"+ SecurityUtils.getSubject().getSession().getAttribute("principal")+"/show";
    }

    /**
     * 发送节目
     * @return
     * @throws Exception
     */
    @RequestMapping("/{username}/create")
    @RequiresPermissions("program:create")
    public String createProgram(@PathVariable(value = "username")String username, Model model)throws Exception{
        model.addAttribute("username",username);
        model.addAttribute("screenslist",screenService.getScreensByOrganizationId(userLoginService.findByName(username).getOrganization_id()));
        model.addAttribute("time",programService.getTimeSchedule());
        return "program/createprogram";
    }

    @RequestMapping("/create.do")
    @RequiresPermissions("program:create")
    public String createProgramdo(HttpServletRequest request, UploadProgram program)throws Exception{
        String path = UploadFileUtil.saveFile(request,program);
        program.setPUrl(path);
        String beginTimeStr = request.getParameter("BeginTime")+":00";
        String endTimeStr = request.getParameter("EndTime")+":00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime = simpleDateFormat.parse(beginTimeStr);
        Date endTime = simpleDateFormat.parse(endTimeStr);
        program.setPBeginTime(beginTime);
        program.setPEndTime(endTime);
        programService.saveProgram(program);

        Log4jUtil.loggerInfo(program.getPBeginTime().toString());
        Log4jUtil.loggerInfo(program.getPEndTime().toString());
        return "redirect:"+ SecurityUtils.getSubject().getSession().getAttribute("principal")+"/show";
    }

    /**
     * 节目审核
     * @param username
     * @return
     * @throws Exception
     */
    @RequestMapping("/{username}/checklist")
    @RequiresPermissions("program:check")
    public String checkList(@PathVariable(value = "username") String username,Model model)throws Exception{
        List<Program> programs = programService.findProgramByUsersNotPassing(userLoginService.findByOrganization(username));
        List<ProgramCustom> programCustoms = programService.programsConvertToProgramCustom(programs);
        model.addAttribute("checkprograms",programCustoms);
        return "program/checklist";
    }

    @RequestMapping("/{pid}/check")
    @RequiresPermissions("program:check")
    public String checkProgram(@PathVariable(value = "pid") Integer pid,Model model)throws Exception{
        Program checkedProgram = programService.getProgramByPID(pid);
        List<String> urls = programService.getFilePath(checkedProgram.getPUrl(),checkedProgram.getPType());
        model.addAttribute("programbycheck",checkedProgram);
        String locations = screenService.getLocationsByStr(checkedProgram.getScreensList());
        model.addAttribute("locations",locations);
        if (checkedProgram.getPType() == 0){//图片
            model.addAttribute("image",urls);
        }else if (checkedProgram.getPType() == 1){//视频
            model.addAttribute("video",urls);
        }else if (checkedProgram.getPType() == 2){//文档
            model.addAttribute("doc",urls);
        }
        model.addAttribute("time",programService.getTimeSchedule());
        return "program/check";
    }

    @RequestMapping("/check.do")
    @RequiresPermissions("program:check")
    public String checkProgramdo(ProgramByCheck programByCheck)throws Exception{
        programService.updateByCheck(programByCheck);
        return "redirect:"+SecurityUtils.getSubject().getSession().getAttribute("principal")+"/checklist";
    }

}
