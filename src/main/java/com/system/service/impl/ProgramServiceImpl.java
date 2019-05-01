package com.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.onbonbx.yqsdk.YQException;
import com.onbonbx.yqsdk.bean.*;
import com.onbonbx.yqsdk.manager.YQCmdListener;
import com.onbonbx.yqsdk.manager.YQScreenClient;
import com.sun.star.util.DateTime;
import com.system.Utils.Log4jUtil;
import com.system.Utils.OpenOfficeUtil;
import com.system.mapper.ProgramMapper;
import com.system.mapper.RoleMapper;
import com.system.mapper.ScreenMapper;
import com.system.pojo.*;
import com.system.service.ProgramService;
import org.apache.commons.lang.RandomStringUtils;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProgramServiceImpl implements ProgramService{

    @Autowired
    ProgramMapper programMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MyPriorityQueue myPriorityQueue;
    @Autowired
    ScreenMapper screenMapper;

    /**
     * 保存节目信息
     * 1.每当用户要发布一个节目，系统保存节目信息要完成三个任务
     *  1.1 保存节目信息到 programlist 表  1.2 保存节目id到 programbycheck 表  1.3 加入优先级队列
     * @param program
     * @throws Exception
     */
    @Override
    public void saveProgram(Program program)throws Exception{
        programMapper.save(program);//1.1
        programMapper.insertProgramBC(program.getID());//1.2
        joinMyPriorityQueue(program);//1.3
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
            if (p.getPJudge() == 0 || p.getPJudge() == 2){
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

    @Override
    public List<ProgramCustom> programsConvertToProgramCustom(List<Program> programs) throws Exception {
        List<ProgramCustom> programCustoms = new ArrayList<>();
        for (int i = 0; i < programs.size(); ++i){
            ProgramCustom tmp = new ProgramCustom(programs.get(i));
            programCustoms.add(tmp);
        }
        return programCustoms;
    }

    @Override
    public void joinMyPriorityQueue(Program program) throws Exception {
        int level = roleMapper.findPriorityByRole(program.getID());
        ProgramItem programItem = new ProgramItem(level, program);
        myPriorityQueue.add(programItem);
    }

    @Override
    public boolean judgeProgramSchedule(Program program) throws Exception {
        Iterator<ProgramItem> iterator = myPriorityQueue.iterator();
        String[] screens = program.getScreensList().split(",");
        return false;
    }

    @Override
    public void addTaskForScheduler(Program program) throws Exception {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        SchedulerFactoryBean stdScheduler =(SchedulerFactoryBean)webApplicationContext.getBean("stdScheduler");
        Scheduler scheduler = stdScheduler.getScheduler();
    }

    /**
     * 暂时不用
     * 发送节目信息到LED电子大屏
     * @param program
     * @throws Exception
     */
    @Override
    public void sendProgram(Program program) throws Exception {
        Log4jUtil.loggerInfo("[ ProgramService: Send program ]");
        /*YQScreenClient yqScreenClient = new YQScreenClient("",5000);
        String scrennsStr = program.getScreensList();//大屏列表id(String) eg: 1,2,3,4
        ArrayList<Screen> screenList = new ArrayList<>();// Screen.class 列表
        String[] idList = scrennsStr.split(",");
        int taskCount = idList.length;*/
        /*for (int i = 0; i < idList.length; ++i){
            screenList.add(screenMapper.getScreen(Integer.parseInt(idList[i])));
        }
        for (int i = 0; i < taskCount; ++i){
            YQProList list = new YQProList();//创建一个节目列表
            yqScreenClient.setHostIp(screenList.get(i).getIp());//设置要发送的ip地址
            YQProgram yqProgram = new YQProgram(384,384);//需要修改  w.x.z 2019/2/18
            yqProgram.setAgingStartDate(program.formateBeginDate())//设置节目的开始时间，默认为一直播放
                    .setAgingStopDate(program.formateEndDate())//设置节目的结束日期，默认为一直播放
                    .setPeriodOnTime(program.formateShortBeginTime())//设置节目的开始时间，默认全天播放
                    .setPeriodOffTime(program.formateShortEndTime())//设置节目的结束时间，默认全天播放
                    .setPlayMode(1)//播放模式 0-按时长播放， 1-按次数播放，默认1
                    .setPlayTime(1);//播放时长 s/次 默认播放1次

            switch (program.getPType()){
                case 0://图片类型
                    YQPicture yqPicture = new YQPicture(0,0,384,384);
                    YQPicUnit yqPicUnit = new YQPicUnit(program.getPUrl());
                    yqPicture.add(yqPicUnit);

                    yqProgram.add(yqPicture);//添加图片分区到节目
                    break;
                case 1://视频类型
                    YQVideo yqVideo = new YQVideo(0,0,384,384);
                    yqVideo.setVideoType(0);//播放类型  0-播放本地视频  1-播放外部输入视频  2-混合播放
                    YQVideoUnit yqVideoUnit = new YQVideoUnit(program.getPUrl());//创建视频文件，添加到视频分区
                    yqVideoUnit.setScaleMode(1);//设置窗口缩放模式，默认窗口按比例缩放 0-按原始比例进行缩放  1-按窗口比例进行缩放
                    yqVideo.add(yqVideoUnit);

                    yqProgram.add(yqVideo);//添加视频分区到节目
                    break;
                case 2://文字类型
                    YQText yqText = new YQText(0,0,384,384);
                    YQTextUnit yqTextUnit = new YQTextUnit("");
                    yqTextUnit.setSingleLine(false)//设置为多行显示
                            .setFontName("楷体")//设置字体名称
                            .setFontSize(16)//设置字体大小
                            .setIsBold(false)//字体是否加粗
                            .setFontColor(0xff0000)//设置字体颜色
                            .setBackColor(0xff000000)//设置背景颜色，黑色
                            .setDisplayEffects(YQDisplayEffect.NONE);//设置显示特技
                    yqText.add(yqTextUnit);

                    yqProgram.add(yqText);//添加文本分区到节目
                    break;
                default:
                    //日志记录显示错误
                    break;
            }
            String dirPath = "D:/test";//发送节目会生成节目文件，指定节目文件的存放路径
            */
            /*yqScreenClient.sendProgram(list, dirPath, new YQCmdListener() {
                @Override
                public void onFinish(YQException e) {
                    //添加节目发送成功的日志
                }

                @Override
                public void onProgress(int i, long l, long l1) {
                    //跟踪发送的进度
                }
            });*/
        }

    @Override
    public List<ProgramByFrontFormat> findProgramsForFrontDesk(String username) throws Exception {
        List<Program> programs = programMapper.findBySender(username);
        List<ProgramByFrontFormat> result = new ArrayList<>();
        for (Program p: programs) {
            ProgramByFrontFormat programByFrontFormat = new ProgramByFrontFormat();
            programByFrontFormat.setId(p.getID());
            programByFrontFormat.setPname(p.getPName());
            programByFrontFormat.setPcontent(p.getPContent());
            programByFrontFormat.setScreenlist(p.getScreensList());
            programByFrontFormat.setStarttime(p.getPBeginTime());
            programByFrontFormat.setEndtime(p.getPEndTime());
            programByFrontFormat.setPurl(p.getPUrl());
            programByFrontFormat.setPublisherId(p.getPSendPersonId());
            programByFrontFormat.setPublisher(p.getPSendPerson());
            programByFrontFormat.setPtype(p.getPType());
            programByFrontFormat.setState(p.getPJudge());
            result.add(programByFrontFormat);
        }
        return result;
    }

    @Override
    public void addProgram(JSONObject programObject) throws Exception {
        int id = programMapper.findMax() + 1;
        String pname = programObject.getString("pname");
        String pcontent = programObject.getString("pcontent");
        Integer ptype = programObject.getInteger("ptype");
        List<Date> rangeTime = programObject.getJSONArray("rangetime").toJavaList(Date.class);
        Date startTime = rangeTime.get(0);
        Date endTime = rangeTime.get(rangeTime.size() - 1);
        List<String> screensTemp = programObject.getJSONArray("screenlist").toJavaList(String.class);
        String screens = "";
        for (String s: screensTemp) {
            screens += s + ","; // 添加 ',' 分隔符
        }
        List<String> filesTemp = programObject.getJSONArray("fileList").toJavaList(String.class);
        String purl = "";
        for (String file: filesTemp) {
            purl += file + "&"; // 添加 '&' 分隔符
        }
        String publisher = programObject.getString("publisher");

        Program program = new Program();
        program.setID(id);
        program.setPName(pname);
        program.setPContent(pcontent);
        program.setScreensList(screens);
        program.setPUrl(purl);
        program.setPBeginTime(startTime);
        program.setPEndTime(endTime);
        program.setPSendPerson(publisher);
        program.setPType(ptype);
        programMapper.save(program);
    }

    @Override
    public void editProgram(JSONObject editObject) throws Exception {
        int id = editObject.getInteger("pid");
        String pname = editObject.getString("pname");
        String pcontent = editObject.getString("pcontent");
        Integer ptype = editObject.getInteger("ptype");
        List<Date> rangeTime = editObject.getJSONArray("rangetime").toJavaList(Date.class);
        Date startTime = rangeTime.get(0);
        Date endTime = rangeTime.get(rangeTime.size() - 1);
        List<String> screensTemp = editObject.getJSONArray("selectedscreens").toJavaList(String.class);
        String screens = "";
        for (String s: screensTemp) {
            screens += s + ",";
        }
        String publisher = editObject.getString("publisher");

        Program program = new Program();
        program.setID(id);
        program.setPName(pname);
        program.setPContent(pcontent);
        program.setScreensList(screens);
        program.setPBeginTime(startTime);
        program.setPEndTime(endTime);
        program.setPSendPerson(publisher);
        program.setPType(ptype);
        programMapper.updateFrontProgram(program);
    }

    @Override
    public List<ProgramByFrontFormat> getVerifyProgramsForFrontDesk(String username) throws Exception {
        List<Program> programs = programMapper.getAll();
        List<ProgramByFrontFormat> result = new ArrayList<>();
        for (Program p: programs) {
            if (p.getPJudge() == 0 || p.getPJudge() == 2) { // 0-》未审核，1-》审核通过，2-》审核未通过，3-》成功发布
                ProgramByFrontFormat programByFrontFormat = new ProgramByFrontFormat();
                programByFrontFormat.setId(p.getID());
                programByFrontFormat.setPname(p.getPName());
                programByFrontFormat.setPcontent(p.getPContent());
                programByFrontFormat.setScreenlist(p.getScreensList());
                programByFrontFormat.setStarttime(p.getPBeginTime());
                programByFrontFormat.setEndtime(p.getPEndTime());
                programByFrontFormat.setPurl(p.getPUrl());
                programByFrontFormat.setFiles(p.getPUrl().split("&"));
                programByFrontFormat.setPublisherId(p.getPSendPersonId());
                programByFrontFormat.setPublisher(p.getPSendPerson());
                programByFrontFormat.setPtype(p.getPType());
                programByFrontFormat.setState(p.getPJudge());
                result.add(programByFrontFormat);
            }
        }
        return result;
    }

    @Override
    public void verifyProgram(JSONObject verifyObject) throws Exception {
        int pid = verifyObject.getInteger("pid");
        int pjudge = verifyObject.getInteger("examinevalue");
        String feedback = verifyObject.getString("feedback");
        ProgramByCheck programByCheck = new ProgramByCheck();
        programByCheck.setPid(pid);
        programByCheck.setPjudge(pjudge);
        programByCheck.setFeedback(feedback);
        programMapper.updateByCheck(programByCheck);
    }
}
