package pama1234.gdx.game.state.state0001.game.region.block.block0001;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pama1234.gdx.game.asset.ImageAsset;
import pama1234.gdx.game.state.state0001.game.metainfo.MetaBlock;
import pama1234.gdx.game.state.state0001.game.metainfo.info0001.center.MetaBlockCenter0001;
import pama1234.gdx.game.state.state0001.game.region.block.Block;
import pama1234.math.UtilMath;

public class Stone extends MetaBlock{
  public Stone(MetaBlockCenter0001 pc,int id) {
    super(pc,"stone",id,new TextureRegion[20],2,(in,type)-> {//change to stone
      in.lighting=16;
    },(in,type)-> {//change from stone
    });
    initLambda();
  }
  public void initLambda() {
    updater=(in,x,y)-> {
      // displayUpdater.update(in,x,y);
    };
    displayUpdater=(in,x,y)-> {
      int typeCache=0;
      if(Block.isEmpty(pc.pw.regions.getBlock(x,y-1))) typeCache+=1;// up
      if(Block.isEmpty(pc.pw.regions.getBlock(x,y+1))) typeCache+=2;// down
      if(Block.isEmpty(pc.pw.regions.getBlock(x-1,y))) typeCache+=4;// left
      if(Block.isEmpty(pc.pw.regions.getBlock(x+1,y))) typeCache+=8;// right
      in.displayType[0]=typeCache;
      typeCache=0;
      if(Block.isEmpty(pc.pw.regions.getBlock(x-1,y-1))) typeCache+=1;
      if(Block.isEmpty(pc.pw.regions.getBlock(x-1,y+1))) typeCache+=2;
      if(Block.isEmpty(pc.pw.regions.getBlock(x+1,y+1))) typeCache+=4;
      if(Block.isEmpty(pc.pw.regions.getBlock(x+1,y-1))) typeCache+=8;
      in.displayType[1]=typeCache;
      //---
      if(in.updateLighting) {
        int tc=0;
        for(int i=-pc.pw.lightDist;i<=pc.pw.lightDist;i++) for(int j=-pc.pw.lightDist;j<=pc.pw.lightDist;j++) if(Block.isEmpty(pc.pw.regions.getBlock(x+i,y+j))) tc+=1;
        in.lighting=UtilMath.constrain(UtilMath.floor(UtilMath.map(tc*2,0,pc.pw.lightCount,0,16)),0,16);
      }
    };
    displayer=(p,in,x,y)-> {
      p.tint(getLighting(in.lighting));
      int tp_0=in.displayType[0];
      p.image(pc.stone.tiles[tp_0],x,y,pc.pw.blockWidth+0.01f,pc.pw.blockHeight+0.01f);
      int tp_1=in.displayType[1];
      if(tp_1!=0) {
        if((tp_0&2)+(tp_0&8)==0&&(tp_1&4)!=0) p.image(pc.stone.tiles[16],x,y,pc.pw.blockWidth+0.01f,pc.pw.blockHeight+0.01f);
        if((tp_0&2)+(tp_0&4)==0&&(tp_1&2)!=0) p.image(pc.stone.tiles[17],x,y,pc.pw.blockWidth+0.01f,pc.pw.blockHeight+0.01f);
        if((tp_0&1)+(tp_0&8)==0&&(tp_1&8)!=0) p.image(pc.stone.tiles[18],x,y,pc.pw.blockWidth+0.01f,pc.pw.blockHeight+0.01f);
        if((tp_0&1)+(tp_0&4)==0&&(tp_1&1)!=0) p.image(pc.stone.tiles[19],x,y,pc.pw.blockWidth+0.01f,pc.pw.blockHeight+0.01f);
      }
    };
  }
  @Override
  public void init() {
    // if(tiles[0]!=null) return;
    TextureRegion[][] tsrc=ImageAsset.tiles;
    //-----------------------------------------------------
    tiles[15]=tsrc[0][8];
    tiles[7]=tsrc[1][8];
    tiles[3]=tsrc[2][8];
    tiles[11]=tsrc[3][8];
    //-----------------------------------------------------
    tiles[13]=tsrc[0][9];
    tiles[5]=tsrc[1][9];
    tiles[1]=tsrc[2][9];
    tiles[9]=tsrc[3][9];
    //-----------------------------------------------------
    tiles[12]=tsrc[0][10];
    tiles[4]=tsrc[1][10];
    tiles[0]=tsrc[2][10];
    tiles[8]=tsrc[3][10];
    //-----------------------------------------------------
    tiles[14]=tsrc[0][11];
    tiles[6]=tsrc[1][11];
    tiles[2]=tsrc[2][11];
    tiles[10]=tsrc[3][11];
    //----------------------------------------------------- TODO
    tiles[16]=tsrc[4][0];
    tiles[17]=tsrc[5][0];
    tiles[18]=tsrc[4][1];
    tiles[19]=tsrc[5][1];
  }
}
