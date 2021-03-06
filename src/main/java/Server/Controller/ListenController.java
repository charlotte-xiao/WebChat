package Server.Controller;


import Common.User;
import Common.UserSocket;
import Server.Service.BaseService;
import Server.View.ServerView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Map;

/**
 * @author charlottexiao
 */
public class ListenController extends Thread {
    private ServerSocket serverSocket;
    private Map<String,UserSocket> socketMap;
    private ServerView serverView;
    ListenController(ServerSocket serverSocket,Map<String,UserSocket> socketMap,ServerView serverView){
        this.serverSocket=serverSocket;
        this.socketMap=socketMap;
        this.serverView=serverView;

    }
    @Override
    public void run() {
        System.out.println("服务器websocket开启...");
        serverView.getTextArea1().append("[系统消息]:服务器已开启...\n");
        while(!serverSocket.isClosed()){
            try {
                //登录验证
                UserSocket userSocket =new UserSocket();
                userSocket.setSocket(serverSocket.accept());
                userSocket.setOut(new ObjectOutputStream(userSocket.getSocket().getOutputStream()));
                userSocket.setIn(new ObjectInputStream(userSocket.getSocket().getInputStream()));
                User user= (User)userSocket.getIn().readObject();
                user=new BaseService().login(user.getId(),user.getPwd());
                if(user!=null){
                    //服务器端对用户信息录入
                    userSocket.setUser(user);
                    userSocket.getOut().writeObject(true);
                    userSocket.getOut().flush();
                    userSocket.getOut().writeObject(user);
                    userSocket.getOut().flush();
                    socketMap.put(userSocket.getUser().getId(),userSocket);
                    new ReceiveController(userSocket,socketMap,serverView).start();
                }else{
                    userSocket.getOut().writeObject(false);
                    userSocket.getSocket().close();
                }
            } catch (Exception e) {
                System.out.println("服务器websocket关闭...");
                serverView.getTextArea1().append("[系统消息]:服务器已关闭...\n");
                this.stop();
            }
        }
    }
}
