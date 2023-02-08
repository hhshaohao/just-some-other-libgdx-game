package pama1234.gdx.game.state.state0001.game.entity.util;

import pama1234.gdx.game.state.state0001.game.entity.LivingEntity;
import pama1234.gdx.game.state.state0001.game.region.block.Block;
import pama1234.gdx.game.util.RectF;

public class MovementLimitBox extends OuterBox{
  public boolean inAir;
  public float floor,leftWall,rightWall,ceiling;
  public RectF rectConst;
  //---
  public boolean leftDown,leftUp,rightDown,rightUp;
  // public int desX1,desY1,desX2,desY2;
  public MovementLimitBox(LivingEntity p) {
    super(p);
    rectConst=new RectF(
      ()->p.type.w/2f,//left
      ()->p.type.h,//ceiling
      ()->p.pw.settings.blockWidth-p.type.w/2f,//right
      ()->p.pw.settings.blockHeight//floor
    );
  }
  public void constrain() {
    if(p.point.pos.y>floor) {
      if(p.point.vel.y>0) p.point.vel.y=0;
      p.point.pos.y=floor;
    }else if(p.point.pos.y<ceiling) {
      if(p.point.vel.y<0) p.point.vel.y=0;
      p.point.pos.y=ceiling;
    }
    if(p.point.pos.x<leftWall) {
      if(p.point.vel.x<0) p.point.vel.x=0;
      p.point.pos.x=leftWall;
    }else if(p.point.pos.x>rightWall) {
      if(p.point.vel.x>0) p.point.vel.x=0;
      p.point.pos.x=rightWall;
    }
  }
  public void doInAirTest() {
    inAir=p.point.pos.y<floor;
  }
  // public void updateDes() {
  // constrain();
  // float tx=p.x()+p.type.dx+p.point.dx(),
  //   ty=p.y()+p.type.dy+p.point.dy();
  // desX1=p.xToBlockCord(tx);
  // desY1=p.xToBlockCord(ty);
  // desX2=p.xToBlockCord(tx+p.type.w-0.01f);
  // desY2=p.xToBlockCord(ty+p.type.h-0.01f);
  // leftUp=x1!=desX1&&y1!=desY1;
  // leftDown=x1!=desX1&&y2!=desY2;
  // rightUp=x2!=desX2&&y1!=desY1;
  // rightDown=x2!=desX2&&y2!=desY2;
  // if(leftUp||leftDown||rightUp||rightDown) p.p.println(leftUp,leftDown,rightUp,rightDown);
  // }
  public void updateLimit() {
    // updateDes();
    int blockWidth=p.pw.settings.blockWidth,
      blockHeight=p.pw.settings.blockHeight;
    if(testCeiling(0,w)) doCeiling(blockHeight);
    else ceiling=(y1-4)*blockHeight;
    if(testFloor(0,w)) doFloor(blockHeight);
    else floor=(y2+4)*blockHeight;
    if(testLeft(0,h)) doLeft(blockWidth);
    else leftWall=(x1-4)*blockWidth;
    if(testRight(0,h)) doRight(blockWidth);
    else rightWall=(x2+4)*blockWidth;
    // System.out.println("MovementLimitBox.updateLimit()");
    // cornerFix();
  }
  public void cornerFix() {
    // int blockWidth=p.pw.settings.blockWidth;
    // int blockHeight=p.pw.settings.blockHeight;
    // if(leftUp&&!Block.isNotFullBlock(p.getBlock(desX1,desY1))) doLeft(blockWidth);
    // if(leftDown&&!Block.isNotFullBlock(p.getBlock(desX1,desY2))) doLeft(blockWidth);
    // if(rightUp&&!Block.isNotFullBlock(p.getBlock(desX2,desY1))) doRight(blockWidth);
    // if(rightDown&&!Block.isNotFullBlock(p.getBlock(desX2,desY2))) doRight(blockWidth);
  }
  public void doRight(int blockWidth) {
    rightWall=x2*blockWidth+rectConst.x2();
  }
  public void doLeft(int blockWidth) {
    leftWall=x1*blockWidth+rectConst.x1();
  }
  public void doFloor(int blockHeight) {
    floor=y2*blockHeight+rectConst.y2();
  }
  public void doCeiling(int blockHeight) {
    ceiling=y1*blockHeight+rectConst.y1();
  }
  public void bugFix() {
    if(inAir&&p.point.vel.y<0&&h>1) {
      h-=1;
      y2-=1;
    }
  }
  public boolean testRight(int a,int b) {
    for(int i=a;i<=b;i++) if(!Block.isNotFullBlock(p.getBlock(x2+1,y1+i))) return true;
    return false;
  }
  public boolean testLeft(int a,int b) {
    for(int i=a;i<=b;i++) if(!Block.isNotFullBlock(p.getBlock(x1-1,y1+i))) return true;
    return false;
  }
  public boolean testFloor(int a,int b) {
    for(int i=a;i<=b;i++) if(!Block.isNotFullBlock(p.getBlock(x1+i,y2+1))) return true;
    return false;
  }
  public boolean testCeiling(int a,int b) {
    for(int i=a;i<=b;i++) if(!Block.isNotFullBlock(p.getBlock(x1+i,y1-1))) return true;
    return false;
  }
}