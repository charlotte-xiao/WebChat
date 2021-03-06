package Server.Controller;

import Common.Msg;
import Common.MsgMIME;
import Common.UserSocket;
import Server.Utils.TencentRobot;
import Server.View.ServerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author charlottexiao
 */
public class BaseController {
    //单例模式
    private ServerView serverView;
    private ServerSocket serverSocket;
    private Map<String, UserSocket> socketMap=new HashMap<String, UserSocket>();
    private BaseController(){}
    private static BaseController baseController=new BaseController();
    public static BaseController get(){
        return baseController;
    }
    public void init(ServerView serverView){
        this.serverView=serverView;
    }
    public boolean start() {
        Properties prop = new Properties();
        try( InputStream in= ClassLoader.getSystemResourceAsStream("config.properties")) {
            prop.load(in);
            String port  = prop.getProperty("port");
            serverSocket=new ServerSocket(Integer.parseInt(port));
            new ListenController(serverSocket,socketMap,serverView).start();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean stop() {
        try {
            for(String key : socketMap.keySet()){
                socketMap.get(key).getOut().writeObject(new Msg("0",key,MsgMIME._text,"服务器将关闭..."));
                socketMap.get(key).getOut().flush();
                socketMap.get(key).getSocket().close();
                socketMap.remove(key);
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void sendMsg(){
        try {
            String toUser=serverView.getComboBox1().getSelectedItem().toString();
            String msgString=serverView.getTextField1().getText();
            serverView.getTextField1().setText("");
            switch (toUser){
                case "系统消息":
                    for(String key : socketMap.keySet()){
                        Msg msg=new Msg("0",key,MsgMIME._text,msgString);
                        socketMap.get(key).getOut().writeObject(msg);
                        socketMap.get(key).getOut().flush();
                    }
                    serverView.getTextArea1().append("[系统消息]----------------------------------:"+msgString+"\n");
                    break;
                case "潇潇机器人":
                    serverView.getTextArea1().append("[管理员->潇潇机器人]----------------------------------:"+msgString+"\n");
                    String reply = TencentRobot.getTencentRobot().robotChat(msgString);
                    serverView.getTextArea1().append("[潇潇机器人]:"+reply+"\n");
                    break;
                default:
                    for(String key : socketMap.keySet()){
                        if(socketMap.get(key).getUser().getName().equals(toUser)){
                            Msg msg=new Msg("0",key, MsgMIME._text,msgString);
                            socketMap.get(key).getOut().writeObject(msg);
                            socketMap.get(key).getOut().flush();
                        }
                    }
                    serverView.getTextArea1().append("[管理员->"+toUser+"]----------------------------------:"+msgString+"\n");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
