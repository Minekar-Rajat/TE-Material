/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4;


import java.net.Socket;

/**
 *
 * @author hp
 */
public class MyThread extends Thread
{
    Thread t;
    Socket socket;
    int port;
    MyThread(Socket soc , int p)
    {
        socket = soc;
        port =p;
        t= new Thread(this);
        t.start();
    }
    
    public void run()
    {
        Server.runServer(socket, port);
    }
    
}

