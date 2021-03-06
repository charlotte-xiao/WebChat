package Server.Controller;

import Common.Msg;
import Common.MsgMIME;
import Common.User;
import Common.UserSocket;
import Server.Utils.TencentRobot;
import Server.View.ServerView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author charlottexiao
 */
public class ReceiveController extends Thread{
    private UserSocket userSocket;
    private Map<String,UserSocket> socketMap;
    private ServerView serverView;
    public ReceiveController(UserSocket userSocket,Map<String,UserSocket> socketMap,ServerView serverView){
        this.userSocket=userSocket;
        this.socketMap=socketMap;
        this.serverView=serverView;
    }
    @Override
    public void run() {
        System.out.println(userSocket.getUser().getName()+"上线...");
        serverView.getTextArea1().append("[系统消息]:"+userSocket.getUser().getName()+"上线啦"+"\n");
        flashUserList();
        while(!userSocket.getSocket().isClosed()){
            try{
                Msg msg=(Msg) userSocket.getIn().readObject();
                //收件人判断
                switch (msg.getToUser()){
                    //下线消息,刷新列表
                    case "-1":
                        serverView.getTextArea1().append("[系统消息]:"+userSocket.getUser().getName()+"下线..."+"\n");
                        socketMap.remove(userSocket.getUser().getId());
                        flashUserList();
                        break;
                    //管理员
                    case "0":
                        serverView.getTextArea1().append("["+userSocket.getUser().getName()+"->管理员]:"+msg.getMsg().toString()+"\n");
                        break;
                    case "1":
                        serverView.getTextArea1().append("["+userSocket.getUser().getName()+"->潇潇机器人]:"+msg.getMsg().toString()+"\n");
                        String reply = TencentRobot.getTencentRobot().robotChat(msg.getMsg().toString());
                        Msg _robot = new Msg("1", msg.getFromUser(), MsgMIME._text, reply);
                        userSocket.getOut().writeObject(_robot);
                        userSocket.getOut().flush();
                        break;
                    //一般用户
                    default:
                        if(socketMap.get(msg.getToUser())!=null){
                            socketMap.get(msg.getToUser()).getOut().writeObject(msg);
                            socketMap.get(msg.getToUser()).getOut().flush();
                        }else {
                            Msg _default = new Msg("0", msg.getFromUser(), MsgMIME._text, "对方用方已经下线...");
                            userSocket.getOut().writeObject(_default);
                            userSocket.getOut().flush();
                        }
                }
            }catch (Exception e){
                System.out.println(userSocket.getUser().getName()+"下线...");
                socketMap.remove(userSocket.getUser().getId());
                flashUserList();
                this.stop();
            }
        }
    }
    /**
     * 1.向客服端发送在线列表
     * 2.刷新服务器列表
     */
    public void flashUserList() {
        serverView.getComboBox1().removeAllItems();
        serverView.getComboBox1().addItem("系统消息");
        serverView.getComboBox1().addItem("潇潇机器人");
        Map<String, User> map=new HashMap<String, User>();
        map.put("0",new User("0","管理员",null));
        map.put("1",new User("1","潇潇机器人",null));
        for (String key : socketMap.keySet()) {
            map.put(key,socketMap.get(key).getUser());
            serverView.getComboBox1().addItem(socketMap.get(key).getUser().getName());
        }
        try {
            for (String key : socketMap.keySet()) {
                Msg _flashUserList =new Msg("-1",key,MsgMIME._object,map);
                socketMap.get(key).getOut().writeObject(_flashUserList);
                socketMap.get(key).getOut().flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
