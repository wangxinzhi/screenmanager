package com.system.service.impl;

import com.sun.star.util.DateTime;
import com.system.Utils.Log4jUtil;
import com.system.Utils.OpenOfficeUtil;
import com.system.mapper.ProgramMapper;
import com.system.pojo.*;
import com.system.service.ProgramService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService{

    @Autowired
    ProgramMapper programMapper;

    @Override
    public void saveProgram(Program program)throws Exception{
        programMapper.save(program);
    }

    @Override
    public void updateProgram(Program program)throws Exception{
        programMapper.update(program);
    }

    @Override
    public void deleteProgram(Integer pid)throws Exception{
        programMapper.delete(pid);
    }

    @Override
    public Integer getProgramCount() throws Exception {
        //System.out.println("Count="+programMapper.getCount());
        return programMapper.getCount();
    }

    @Override
    public List<Program> getProgramsByPaging(Integer toPageNo) throws Exception {
        System.out.println("toPage="+toPageNo);//
        Page page=new Page();
        page.setToPageNo(toPageNo);
        List<Program> list=programMapper.getAllByPaging(page);
        return list;
    }

    @Override
    public Program getProgramByPID(Integer pid) throws Exception {
        return programMapper.getProgramByPID(pid);
    }

    @Override
    public List<Program> findProgramBySender(String username) throws Exception {
        List<Program> result=programMapper.findBySender(username);
        return result;
    }

    @Override
    public void updateByCheck(ProgramByCheck programByCheck) throws Exception {
        programMapper.updateByCheck(programByCheck);
        programMapper.updateFeedback(programByCheck);
    }

    @Override
    public List<Program> findProgramByUsers(List<UserLogin> userLogins) throws Exception {
        List<Program> result=new ArrayList<>();
        for (UserLogin user:userLogins) {
            result.addAll(programMapper.findBySender(user.getUsername()));
        }
        return result;
    }

    @Override
    public List<Program> findProgramByUsersNotPassing(List<UserLogin> userLogins) throws Exception {
        List<Program> result=new ArrayList<>();
        for (Program p:findProgramByUsers(userLogins)) {
            if (p.getPJudge()==0||p.getPJudge()==2){
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public List<String> readAllFiles(String path) throws Exception {
        List<String> results=new ArrayList<>();
        File file=new File(path);
        if (!file.isDirectory()){
            //不是文件夹，抛出异常

        }else if (file.isDirectory()){
            //是文件夹，读取文件名
            for (String p:file.list()) {
                File temp=new File(path+"\\"+"p");
                if (!temp.isDirectory()){
                    results.add(path+"\\"+p);
                }else if (temp.isDirectory()){
                    results.addAll(readAllFiles(path+"\\"+p));
                }
            }
        }
        return results;
    }

    @Override
    public List<String> getFilePath(String path,Integer type) throws Exception {
        String filepath=null;
        if (type==0){
            filepath=path.substring(path.indexOf("zutimg"));
        }else if (type==1){
            filepath=path.substring(path.indexOf("zutav"));
        }else if (type==2){
            filepath=path.substring(path.indexOf("zutdoc"));
        }
        Log4jUtil.loggerInfo("||==============================||"+filepath);
        List<String> temp=readAllFiles(path);
        List<String> filesname=new ArrayList<>();
        for (String filename:temp) {
            //filesname.add(filepath+"\\"+filename);
            if (type==0){
                filename=filename.substring(filename.indexOf("zutimg"));
            }else if (type==1){
                filename=filename.substring(filename.indexOf("zutav"));
            }else if (type==2){
                filename=filename.substring(filename.indexOf("zutdoc"));
            }
            filesname.add(filename);
            Log4jUtil.loggerInfo("||==============================||"+filename);
        }
        if (type==2){//如果是文档类型,调用OpenOfficeUtil工具类中的docConvertToHtml方法,将doc文件转化为html文件
            List<String> filesname2=new ArrayList<>();
            for (String filename:filesname) {
                filesname2.add(OpenOfficeUtil.docConvertToHtml(filename));
            }
            return filesname2;
        }
        return filesname;
    }

    @Override
    public String getFeedback(Integer pid) throws Exception {
        if (programMapper.getFeedback(pid)==null||programMapper.getFeedback(pid).equals("")){
            Log4jUtil.loggerInfo("为空");
            return "还未审核,请耐心等待.";
        }
        else {
            String result=programMapper.getFeedback(pid);
            Log4jUtil.loggerInfo(result);
            return result;
        }
    }

    @Override
    public List<Program> getTimeSchedule() throws Exception {
        Date now=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.format(now);
        List<Program> temp=programMapper.getAll();
        List<Program> result=new ArrayList<>();
        for (Program program:
             temp) {
            if (program.getPJudge()==1||program.getPJudge()==3){
                if (now.compareTo(program.getPEndTime())<0){
                    result.add(program);
                    Log4jUtil.loggerInfo("[  getTimeSchedule()  节目id="+program.getID()+"  开始时间="+program.formatBeginTime()+"  结束时间="+program.formatEndTime()+"]");
                }
            }
        }
        result.sort(new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                if (o1.getPBeginTime().compareTo(o2.getPBeginTime())>=0){
                    return 1;
                }
                else {
                    return -1;
                }
            }
        });
        return result;
    }

    @Override
    public void updateProgramOfFailedChecking(HttpServletRequest request, UploadProgram program) throws Exception {
        if (program.getFiles()!=null&&program.getFiles().length>0){//重新上传文件
            Log4jUtil.loggerInfo("重新上传文件.");
            //1.先删除原先的文件
            String filedir=programMapper.getProgramByPID(program.getID()).getPUrl();
            Log4jUtil.loggerInfo("路径="+filedir);
            File file=new File(filedir);
            if (file.isDirectory()&&file.exists()){
                Log4jUtil.loggerInfo("路径存在");
                String[] filenames=file.list();
                for (String filename:
                        filenames) {
                    Log4jUtil.loggerInfo("执行删除"+filename);
                    File file1=new File(filedir+"\\"+filename);
                    file1.delete();
                }
            }
            //2.保存现在的文件
            Log4jUtil.loggerInfo("保存文件");
            MultipartFile[] files= program.getFiles();
            String[] suffix= {".jpg",".mp4",".doc"};//保存的文件后缀
            for (int i=0;i<files.length;i++){
                File file2=new File(filedir+ "\\"+RandomStringUtils.randomAlphabetic(5)+suffix[programMapper.getProgramByPID(program.getID()).getPType()]);
                file2.getParentFile().mkdirs();
                files[i].transferTo(file2);
            }
        }
        String beginTimeStr = request.getParameter("BeginTime")+":00";
        String endTimeStr = request.getParameter("EndTime")+":00";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime=simpleDateFormat.parse(beginTimeStr);
        Date endTime=simpleDateFormat.parse(endTimeStr);
        program.setPBeginTime(beginTime);
        program.setPEndTime(endTime);
        programMapper.updateOfFailedChecking(program);
    }

}
