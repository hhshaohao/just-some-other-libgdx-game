package pama1234.gdx.game.state.state0001.game.entity.entity0001;

import java.util.Iterator;

import pama1234.gdx.game.app.Screen0011;
import pama1234.gdx.game.state.state0001.game.entity.LivingEntity;
import pama1234.gdx.game.state.state0001.game.entity.MultiGameEntityCenter;
import pama1234.gdx.game.state.state0001.game.entity.MultiGameEntityCenter.GameEntityCenter;
import pama1234.gdx.game.state.state0001.game.entity.util.MovementLimitBox;
import pama1234.gdx.game.state.state0001.game.item.Item;
import pama1234.gdx.game.state.state0001.game.metainfo.MetaCreature;
import pama1234.gdx.game.state.state0001.game.metainfo.MetaItem.ItemCountType;
import pama1234.gdx.game.state.state0001.game.metainfo.info0001.center.MetaCreatureCenter0001;
import pama1234.gdx.game.state.state0001.game.world.World0001;
import pama1234.math.UtilMath;
import pama1234.math.physics.MassPoint;

public class DroppedItem extends LivingEntity{
  public DroppedItemCenter pc;
  public Item data;
  public MovementLimitBox limitBox;
  public DroppedItem(Screen0011 p,World0001 pw,float x,float y,float xVel,float yVel,DroppedItemType type,Item data) {
    super(p,pw,new MassPoint(x,y,xVel,yVel),type);
    this.data=data;
    pc=data.type.pc.pw.entities.items;
    limitBox=new MovementLimitBox(this);
  }
  @Override
  public void update() {
    limitBox.update();
    limitBox.updateLimit();
    limitBox.doInAirTest();
    if(limitBox.inAir) point.vel.y+=pw.settings.g;
    super.update();
    limitBox.constrain();
    itemAttract();
  }
  public void itemAttract() {
    Iterator<DroppedItem> di=pc.list.descendingIterator();
    while(di.hasNext()) {
      DroppedItem e=di.next();
      if(e==this) break;
      if(e.data.type.countType==ItemCountType.UNCOUNTABLE) continue;
      if(e.data.type!=data.type) continue;
      float td=UtilMath.dist(e.x(),e.y(),x(),y());
      if(td<pc.itemMergeDist) {
        data.count+=e.data.count;
        e.data.count=0;
        pc.remove.add(e);
      }else if(td<pc.itemMergeMoveDist) {
        // e.point.vel.add((x()-e.x())*p.random(0.1f,0.2f),(y()-e.y())*p.random(0.1f,0.2f));
        float minVel=0.1f,maxVel=0.2f;
        // float minVel=0.05f,maxVel=0.1f;
        e.point.vel.set((x()-e.x())*p.random(minVel,maxVel),(y()-e.y())*p.random(minVel,maxVel));
      }
    }
  }
  @Override
  public void display() {
    p.image(data.type.tiles[0],x()+type.dx,y()+type.dy,type.w,type.h);
  }
  public static class DroppedItemType extends MetaCreature<DroppedItem>{
    {
      w=12;
      h=12;
      dx=-6;
      dy=-12;
    }
    public DroppedItemType(MetaCreatureCenter0001 pc,int id) {
      super(pc,"dropped item",id,4,0,0);
      immortal=true;
    }
    @Override
    public void init() {}
  }
  public static class DroppedItemCenter extends GameEntityCenter<Screen0011,DroppedItem>{
    // public int w=12,h=12,
    //   dx=-6,dy=-12;
    public float itemMergeMoveDist=54,itemMergeDist=18;
    public DroppedItemCenter(Screen0011 p,MultiGameEntityCenter pc) {
      super(p,pc);
    }
    @Override
    public void update() {
      super.update();
    }
  }
}