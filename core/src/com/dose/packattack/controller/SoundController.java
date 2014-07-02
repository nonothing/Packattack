package com.dose.packattack.controller;


public class SoundController {

//
//    private Sound sound;
//    private Sound backgroundSound;
//    private Sound menu;
    private boolean isSound = true;
   
    public SoundController() {
//        sound = new Sound();
    }
    
    public void playBackground(){
//        if(backgroundSound == null){
//           // backgroundSound = new Sound();
//           // backgroundSound.play(Wave.background, true, true);
//        }
    }
        
    
//    private void collisionNPC(World world) {
//        if (world.deadPlayer()) {
//          //  sound.play(Wave.death, false, isSound);
//        }
//      
//    }
//
//    public void play(World world){
//        collisionNPC(world);
//    }
//    
//    public void stop() {
//        if (backgroundSound != null) {
//          //  backgroundSound.stop();
//          //  backgroundSound = null;
//        }
//        if (sound != null) {
//         //   sound.stop();
//        }
//        if (menu != null){
//          //  menu.stop();
//          //  menu = null;
//            }
//    }
//    
//    public void playMenu(){
//        if(menu == null){
//      //  menu = new Sound();
//      //  menu.play(Wave.menuSound, true, isSound);
//        }
//    }
//    
//    public void playFire(){
////        sound.play(Wave.humanFire, false, isSound); 
//    }
    
    public void setSound(boolean isSound){
        this.isSound = isSound;
    }
    
    public boolean getSound(){
        return isSound;
    }
}
