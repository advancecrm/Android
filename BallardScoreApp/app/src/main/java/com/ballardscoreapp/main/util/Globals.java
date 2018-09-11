package com.ballardscoreapp.main.util;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;

public class Globals extends Application {

    public boolean fullscreen = false;
    public boolean scorefragres = false;
    public boolean unlockfrag = false;
    public boolean monofrag = false;
    public boolean faqfrag = false;
    //---------------------------
    public int skinPos = 5465, lanugoPos = 456, plantPos = 798, breastPos = 65767, eyePos = 656, male_femalePos = 656, phyScore = 677;
    public boolean isSkin = false, isLanugo = false, isBreast = false, isEye = false, isPlant = false, ismale_female = false;
    public int posturePos = 4554, squarePos = 545, armPos = 7657, popPos = 545, scarfPos = 566, heelPos = 656, neuroScore = 6557;
    public boolean isPosture = false, isSquare = false, isarm = false, isPop = false, isScarf = false, isHeel = false;
    public boolean isNeuroCalculated = false, isPhyCalculated = false;
    public int totalScore = 789;
    ArrayList<HashMap<String, String>> queAnsList = new ArrayList<HashMap<String, String>>();
    public int pos = 0;

    public ArrayList<HashMap<String, String>> getQueAnsList() {
        return queAnsList;
    }

    public void setQueAnsList(ArrayList<HashMap<String, String>> queAnsList) {
        this.queAnsList = queAnsList;
    }
    
}
