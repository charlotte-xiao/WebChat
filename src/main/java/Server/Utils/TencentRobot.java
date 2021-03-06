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
        TAipNlp client = new TAipNlp("2160840907", "XXxWPFgVn0SVntUH");
        String session = new Date().getTime()/1000+"";//会话标识（应用内唯一）

        String result = client.nlpTextchat(session,str);//基础闲聊
        String answer="";

        answer=result.substring(result.indexOf("\"answer\":")+11,result.lastIndexOf("\""));
        return answer;
    }
}
