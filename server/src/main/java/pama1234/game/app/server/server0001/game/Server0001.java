package pama1234.game.app.server.server0001.game;

import pama1234.game.app.server.server0001.game.net.SocketData;
import pama1234.game.app.server.server0001.game.net.data.Server0001Core;
import pama1234.game.app.server.server0001.game.net.io.ServerRead;
import pama1234.game.app.server.server0001.game.net.io.ServerWrite;
import pama1234.game.app.server.server0001.game.particle.CellGroup3D;
import pama1234.game.app.server.server0001.game.particle.CellGroupGenerator3D;
import pama1234.util.UtilServer;
import pama1234.util.net.ServerInfo;
import pama1234.util.net.ServerSocketData;
import pama1234.util.net.SocketWrapper;
import pama1234.util.wrapper.Center;

public class Server0001 extends UtilServer{//particle server 3d
  public CellGroup3D group;
  public boolean doUpdate=true;
  public Thread updateCell;
  //---
  public ServerPlayerCenter3D playerCenter;
  //---
  public ServerInfo serverInfo;
  public ServerSocketData serverSocket;
  public Center<SocketData> socketCenter;
  public Center<ServerRead> serverReadPool;
  public Center<ServerWrite> serverWritePool;
  public Thread acceptSocket;
  public Server0001Core serverCore;
  //---
  // public Scanner scanner;
  public ScannerThread scannerThread;
  @Override
  public void init() {
    CellGroupGenerator3D gen=new CellGroupGenerator3D(0,0);
    // group=gen.randomGenerate();
    group=gen.GenerateFromMiniCore();
    //---
    updateCell=new Thread("UpdateCell") {
      @Override
      public void run() {
        while(!stop) {
          if(doUpdate) group.update();
          else try {
            sleep(1000);
          }catch(InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };
    updateCell.start();
    //---
    playerCenter=new ServerPlayerCenter3D();
    //---
    serverInfo=new ServerInfo("192.168.2.105",12347);
    serverSocket=new ServerSocketData(serverInfo);
    socketCenter=new Center<>();
    serverReadPool=new Center<>();
    serverWritePool=new Center<>();
    serverCore=new Server0001Core(socketCenter,serverReadPool,serverWritePool,group,playerCenter);
    acceptSocket=new Thread(()-> {
      while(!stop) {
        // synchronized(centerSocket.add) {
        SocketData socketData=new SocketData(new SocketWrapper(serverSocket.accept()));
        // System.out.println(socketData.s.getRemoteAddress());
        socketCenter.add.add(socketData);
        //---
        ServerWrite serverWrite=new ServerWrite(serverCore,socketData);
        serverWrite.start();
        serverWritePool.add.add(serverWrite);
        //---
        ServerRead serverRead=new ServerRead(serverCore,socketData);
        serverRead.start();
        serverReadPool.add.add(serverRead);
        // }
      }
    },"AcceptSocket");
    acceptSocket.start();
    //---
    scannerThread=new ScannerThread(this);
    scannerThread.start();
  }
  @Override
  public void update() {
    // group.update();
    // if(doUpdate) group.update();
    playerCenter.update();
  }
  @Override
  public void dispose() {
    group.dispose();
    playerCenter.dispose();
  }
  public static void main(String[] args) {
    new Server0001().run();
  }
}
