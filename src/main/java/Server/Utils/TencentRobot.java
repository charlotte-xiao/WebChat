package Server.Utils;

import cn.xsshome.taip.nlp.TAipNlp;

import java.util.Date;

/**
 * @author charlottexiao
 */
public class TencentRobot {
    //单例模式
    private TencentRobot(){}
    private static TencentRobot tencentRobot=new TencentRobot();
    public static TencentRobot getTencentRobot(){
        return tencentRobot;
    }
    public String robotChat(String str) throws Exception{
        //这里将app_id和app_key修改为自己申请
        TAipNlp client = new TAipNlp("XXXXXXXXXX", "XXXXXXXXXXXXXXXX");
        String session = new Date().getTime()/1000+"";//会话标识（应用内唯一）

        String result = client.nlpTextchat(session,str);//基础闲聊
        String answer="";

        answer=result.substring(result.indexOf("\"answer\":")+11,result.lastIndexOf("\""));
        return answer;
    }
}
