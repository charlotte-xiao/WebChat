package Client.Controller;

import Client.View.ClientView;
import Common.Msg;
import Common.User;
import Common.UserSocket;

import java.util.Map;

/**
 * @author charlottexiao
 */
public class ListenController extends Thread {
    private UserSocket userSocket;
    private ClientView clientView;
    private Map<String,User> onlineList;
    public  ListenController(UserSocket userSocket, ClientView clientView, Map<String,User> onlineList){
        this.userSocket=userSocket;
        this.clientView=clientView;
        this.onlineList=onlineList;
    }
    @Override
    public void run() {
        System.out.println("客户端socket开启...");
        while(!userSocket.getSocket().isClosed()) {
            try{
                Msg msg= (Msg) userSocket.getIn().readObject();
                if(msg.getToUser().equals(userSocket.getUser().getId())){
                    switch (msg.getType()){
                        case _text:
                            if(msg.getFromUser().equals("0")){
                                clientView.getTextArea1().append("[系统管理员]:"+msg.getMsg().toString()+"\n");
                            }else if(msg.getFromUser().equals("1")){
                                clientView.getTextArea1().append("[潇潇机器人]:"+msg.getMsg().toString()+"\n");
                            }else{
                                for(String key: onlineList.keySet()){
                                    if(onlineList.get(key).getId().equals(msg.getFromUser())){
                                        clientView.getTextArea1().append("["+onlineList.get(key).getName()+"]:"+msg.getMsg().toString()+"\n");
                                    }
                                }
                            }
                            break;
                        case _object:
                           if(msg.getFromUser().equals("-1")){
                               flashUserList(msg.getMsg());
                           }
                           break;
                        default:
                            break;
                    }
                }
            }catch (Exception e){
                System.out.println("客户端socket关闭...");
                this.stop();
            }
        }
    }

    /**
     * 刷新在线用户列表
     */
    public void flashUserList(Object List){
        this.onlineList.clear();
        this.onlineList.putAll((Map<String, User>) List);
        clientView.getComboBox1().removeAllItems();
        for (String key : onlineList.keySet()) {
            if(!onlineList.get(key).getId().equals(userSocket.getUser().getId())){
                clientView.getComboBox1().addItem(onlineList.get(key).getName());
            }
        }
    }
}
