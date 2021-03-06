package Client.Controller;

import Client.View.ClientView;
import Client.View.Login;
import Common.Msg;
import Common.MsgMIME;
import Common.User;
import Common.UserSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author charlottexiao
 */
public class ClientController {
    private Login login;
    public ClientView clientView;
    private UserSocket userSocket;
    private Map<String,User> onlineList;
    private User user;
    public void init(Login login){
        this.login=login;
        clientView=new ClientView();
        clientView.init(this);
        clientView.setVisible(false);
    }

    public boolean login(String url,String id,String pwd) {
        String ip=url.substring(0,url.indexOf(":"));
        String port=url.substring(url.indexOf(":")+1);
        //url连接
        try {
            userSocket=new UserSocket();
            userSocket.setSocket(new Socket(ip, Integer.parseInt(port)));
            userSocket.setOut(new ObjectOutputStream(userSocket.getSocket().getOutputStream()));
            userSocket.setIn(new ObjectInputStream(userSocket.getSocket().getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
           return false;
        }
        //登录验证
        try{
            user=new User();
            user.setId(id);
            user.setPwd(pwd);
            userSocket.getOut().writeObject(user);
            userSocket.getOut().flush();
            if((boolean)userSocket.getIn().readObject()){
                user=(User)userSocket.getIn().readObject();
                userSocket.setUser(user);
                onlineList=new HashMap<String,User>();
                online();
                return true;
            }else{
                userSocket.getSocket().close();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void online() {
        clientView.setVisible(true);
        new ListenController(userSocket, clientView, onlineList).start();
        login.setVisible(false);
    }

    public void offline(){
        try {
            Msg msg1=new Msg(userSocket.getUser().getId(),"-1", MsgMIME._text,null);
            userSocket.getOut().writeObject(msg1);
            userSocket.getOut().flush();
            clientView.getTextArea1().setText("");
            userSocket.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        login.setVisible(true);
        clientView.setVisible(false);
    }
    public void sendMsg(){
        try {
            String toUser=clientView.getComboBox1().getSelectedItem().toString();
            String msgString=clientView.getTextField1().getText();
            clientView.getTextField1().setText("");
            switch (toUser){
                case "管理员":
                    Msg msg1=new Msg(userSocket.getUser().getId(),"0", MsgMIME._text,msgString);
                    userSocket.getOut().writeObject(msg1);
                    userSocket.getOut().flush();
                    clientView.getTextArea1().append("[I->管理员]----------------------------------:"+msgString+"\n");
                    break;
                case "潇潇机器人":
                    Msg msg2=new Msg(userSocket.getUser().getId(),"1", MsgMIME._text,msgString);
                    userSocket.getOut().writeObject(msg2);
                    userSocket.getOut().flush();
                    clientView.getTextArea1().append("[I->潇潇机器人]<----------------------------------:"+msgString+"\n");
                    break;
                default:
                    for(String key : onlineList.keySet()){
                        if(onlineList.get(key).getName().equals(toUser)){
                            Msg msg=new Msg(userSocket.getUser().getId(),key, MsgMIME._text,msgString);
                            userSocket.getOut().writeObject(msg);
                            userSocket.getOut().flush();
                        }
                    }
                    clientView.getTextArea1().append("[I->"+toUser+"]<----------------------------------:"+msgString+"\n");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
