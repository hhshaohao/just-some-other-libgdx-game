package pama1234.gdx.game.ui.generator;

import com.badlogic.gdx.Input;

import pama1234.gdx.game.app.Screen0012;
import pama1234.gdx.game.asset.ImageAsset;
import pama1234.gdx.game.state.state0002.Game;
import pama1234.gdx.game.state.state0002.Settings;
import pama1234.gdx.game.state.state0002.State0002;
import pama1234.gdx.game.ui.util.Button;
import pama1234.gdx.game.ui.util.Slider;
import pama1234.gdx.game.ui.util.TextButton;
import pama1234.gdx.game.ui.util.TextButtonCam;
import pama1234.gdx.game.ui.util.TextureButton;

public class ButtonGenerator0002{
  public static <T extends Screen0012> TextButton<?>[] genButtons_0007(T p,Game pg) {
    return new TextButton[] {
      new TextButton<T>(p,true,()->true,()-> {},()-> {},()-> {
        pg.androidRightMouseButton=!pg.androidRightMouseButton;
        pg.ctrlButtons[0].text=pg.androidRightMouseButton?"mR":"mL";
      },"mL",p::getButtonUnitLength,()->p.width-p.bu*4f,()->p.height-p.bu*1.5f,()->p.bu-p.pus,false),
      new TextButton<T>(p,true,()->true,()-> {},()-> {
        // p.inputProcessor.keyDown(Input.Keys.SHIFT_LEFT);
        // pg.world.yourself.ctrl.shift(!pg.world.yourself.ctrl.shift);
        // pg.ctrlButtons[1].text=pg.world.yourself.ctrl.shift?"S":"s";
      },()-> {
        // p.inputProcessor.keyUp(Input.Keys.SHIFT_LEFT);
      },"s",p::getButtonUnitLength,()->p.width-p.bu*2.5f,()->p.height-p.bu*1.5f,()->p.bu-p.pus,true),
      new TextButton<T>(p,true,()->true,()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.A);
      },()-> {
        p.inputProcessor.keyUp(Input.Keys.A);
      },"← ",p::getButtonUnitLength,()->p.bu*1.5f,()->p.height-p.bu*1.5f,()->p.bu-p.pus,false),
      new TextButton<T>(p,true,()->true,()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.D);
      },()-> {
        p.inputProcessor.keyUp(Input.Keys.D);
      }," →",p::getButtonUnitLength,()->p.bu*3f,()->p.height-p.bu*1.5f,()->p.bu-p.pus,false),
      //--------------------------------------------------------------------
      new TextButton<T>(p,true,()->true,()-> {},()-> {
        p.inputProcessor.keyDown(Input.Keys.SPACE);
      },()-> {
        p.inputProcessor.keyUp(Input.Keys.SPACE);
      },"↑",p::getButtonUnitLength,()->p.width-p.bu*2.5f,()->p.height-p.bu*2.5f,()->p.bu-p.pus,false),
    };
  }
  public static <T extends Screen0012> TextButtonCam<?>[] genButtons_0006(T p,Settings ps) {
    return new TextButtonCam[] {
      new TextButtonCam<T>(p,true,()->true,()-> {},()-> {},()-> {
        p.mute=!p.mute;
        if(p.mute) ps.buttonsCam[0].text="静音：是";
        else ps.buttonsCam[0].text="静音：否";
      },"静音：否",()->18,()->0,()->0),
      ps.volumeSlider=new Slider<T>(p,true,()->true,()-> {
        p.volume=ps.volumeSlider.pos;
        // ps.volumeSlider.text="音量 "+Tools.cutToLastDigit(p.volume*100);
        ps.volumeSlider.text="音量 "+String.format("%6.2f",p.volume*100);
      },()-> {},()-> {},"音量 100.00",()->18,()->0,()->20,1),
      new TextButtonCam<T>(p,true,()->true,()-> {},()-> {},()-> {
        p.debugInfo=!p.debugInfo;
        Game game=(Game)State0002.Game.entity;
        // boolean flag=game.debug;
        game.debug=p.debugInfo;
        if(game.debug=p.debugInfo) game.createDebugDisplay();
        // if(game.debug) {
        //   game.createDebugDisplay();
        //   if(!flag) p.centerCam.add.add(game.displayCamTop);
        // }else if(flag) p.centerCam.remove.add(game.displayCamTop);
        if(p.debugInfo) ps.buttonsCam[2].text="显示调试信息：是";
        else ps.buttonsCam[2].text="显示调试信息：否";
      },"显示调试信息：否",()->18,()->0,()->40),
      new TextButtonCam<T>(p,true,()->true,()-> {},()-> {},()-> {
        System.gc();
        Runtime.getRuntime().runFinalization();
      },"清理内存垃圾",()->18,()->0,()->60),
    };
  }
  public static <T extends Screen0012> Button<?>[] genButtons_0005(T p) {
    return new Button[] {
      new TextureButton<T>(p,true,()->true,()-> {},()-> {},()-> {
        p.state(State0002.StartMenu);
      },()->ImageAsset.exit,p::getButtonUnitLength,()->p.bu*0.2f,()->p.bu*0.2f,()->p.bu,()->p.bu),
    };
  }
  public static <T extends Screen0012> Button<?>[] genButtons_0004(T p) {
    return new Button[] {
      new TextButton<T>(p,true,()->true,()-> {},()-> {},()-> {
        p.state(State0002.StartMenu);
      },"返回",p::getButtonUnitLength,()->p.bu*0.5f,()->p.bu*0.5f),
    };
  }
  public static <T extends Screen0012> Button<?>[] genButtons_0003(T p) {
    return new Button[] {
      new TextButton<T>(p,true,()->true,()-> {},()-> {},()-> {
        p.state(State0002.Game);
      },"开始游戏",p::getButtonUnitLength,()->p.width/4f*3-p.pu*2.5f,()->p.height/4f-p.bu/2f),
      new TextButton<T>(p,true,()->true,()-> {},()-> {},()-> {
        p.state(State0002.Announcement);
      },"　公告　",p::getButtonUnitLength,()->p.width/4f*3-p.pu*2.5f,()->p.height/2f-p.bu/2f),
      new TextButton<T>(p,true,()->true,()-> {},()-> {},()-> {
        p.state(State0002.Settings);
      },"　设置　",p::getButtonUnitLength,()->p.width/4f*3-p.pu*2.5f,()->p.height/4f*3-p.bu/2f),
    };
  }
  public static <T extends Screen0012> Button<?>[] genButtons_0001(T p) {
    return new Button[] {
      new TextButton<T>(p,true,()->true,()-> {},()-> {},()-> {
        p.state(State0002.StartMenu);
      },"返回",p::getButtonUnitLength,()->p.bu*0.5f,()->p.bu*0.5f),
    };
  }
}
