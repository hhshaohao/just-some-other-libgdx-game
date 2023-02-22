package pama1234.gdx.game.state.state0001.game.world;

import pama1234.gdx.game.state.state0001.game.metainfo.MetaBlock;
import pama1234.gdx.game.state.state0001.game.player.BlockPointer;
import pama1234.gdx.game.state.state0001.game.player.MainPlayer;
import pama1234.gdx.game.state.state0001.game.region.block.Block;

public class RegionWrapper{
  public World0001 pw;
  public RegionWrapper(World0001 pw) {
    this.pw=pw;
  }
  public void destroyBlock(MainPlayer player,Block block,int x,int y) {
    placeBlock(player,block,pw.metaBlocks.air,x,y);
  }
  public void destroyBlock(BlockPointer bp,Block block,int x,int y) {
    placeBlock(bp,block,pw.metaBlocks.air,x,y);
  }
  public void destroyBlock(Block block,int x,int y) {
    placeBlock(block,pw.metaBlocks.air,x,y);
  }
  public void placeBlock(MainPlayer player,Block block,MetaBlock in,int x,int y) {
    placeBlock(block,in,x,y);
  }
  public void placeBlock(BlockPointer bp,Block block,MetaBlock in,int x,int y) {
    placeBlock(block,in,x,y);
  }
  public void placeBlock(Block block,MetaBlock in,int x,int y) {
    if(block.xOff!=0||block.yOff!=0) placeBlock(
      block.origin==null?pw.getBlock(x-block.xOff,y-block.yOff):block.origin,pw.metaBlocks.air,x-block.xOff,y-block.yOff);
    block.doItemDrop(pw.p,x,y,in.empty);
    removeIfOffBlock(block,block.type,x,y);
    putIfOffBlock(block,in,x,y);
    block.type(in);
  }
  public void removeIfOffBlock(Block block,MetaBlock type,int x,int y) {
    if(type!=null&&(type.width>1||type.height>1)) {
      for(int i=0;i<type.width;i++) for(int j=0;j<type.height;j++) {
        int tx=x+i,
          ty=y+j;
        Block blockOff=pw.getBlock(tx,ty);
        // if(block.xOff!=0||block.yOff!=0) removeIfOffBlock(blockOff,blockOff.type,tx-block.xOff,ty-block.yOff);
        blockOff.type(pw.metaBlocks.air);
        blockOff.clearOrigin();
      }
    }
  }
  public void putIfOffBlock(Block block,MetaBlock type,int x,int y) {
    if(type!=null&&(type.width>1||type.height>1)) {
      for(int i=0;i<type.width;i++) for(int j=0;j<type.height;j++) {
        int tx=x+i,
          ty=y+j;
        Block blockOff=pw.getBlock(tx,ty);
        // if(block.xOff!=0||block.yOff!=0) removeIfOffBlock(
        //   blockOff.origin==null?getBlock(x-blockOff.xOff,y-blockOff.yOff):blockOff.origin,
        //   blockOff.type,tx-block.xOff,ty-block.yOff);
        // else 
        blockOff.doItemDrop(pw.p,tx,ty,type.empty);
        blockOff.type(type);
        blockOff.origin(block,i,j);
      }
    }
  }
  public void setBlock(Block block,MetaBlock in,int x,int y) {
    block.type(in);
  }
  public void setBlock(MetaBlock in,int x,int y) {
    setBlock(pw.getBlock(x,y),in,x,y);
  }
}