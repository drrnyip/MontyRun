package com.whixpyl.montyrun.Textures;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Darren on 5/27/2016.
 */
public class FlightBar {

    private Texture aa,ab,ac,ad,ae,af,ag,ah,ai,aj,ak,al,am,an,ao,ap,aq,ar,as,at,au,av,aw,ax,ay,az,ba,bb,bc,bd,be,bf;
    private int flightTime;

    public FlightBar(int flightTime){
        aa = new Texture("flightBars/100.png");
        ab = new Texture("flightBars/97.png");
        ac = new Texture("flightBars/94.png");
        ad = new Texture("flightBars/90.png");
        ae = new Texture("flightBars/87.png");
        af = new Texture("flightBars/84.png");
        ag = new Texture("flightBars/80.png");
        ah = new Texture("flightBars/77.png");
        ai = new Texture("flightBars/74.png");
        aj = new Texture("flightBars/70.png");
        ak = new Texture("flightBars/67.png");
        al = new Texture("flightBars/64.png");
        am = new Texture("flightBars/60.png");
        an = new Texture("flightBars/57.png");
        ao = new Texture("flightBars/54.png");
        ap = new Texture("flightBars/50.png");
        aq = new Texture("flightBars/47.png");
        ar = new Texture("flightBars/44.png");
        as = new Texture("flightBars/40.png");
        at = new Texture("flightBars/37.png");
        au = new Texture("flightBars/34.png");
        av = new Texture("flightBars/30.png");
        aw = new Texture("flightBars/27.png");
        ax = new Texture("flightBars/24.png");
        ay = new Texture("flightBars/20.png");
        az = new Texture("flightBars/17.png");
        ba = new Texture("flightBars/14.png");
        bb = new Texture("flightBars/10.png");
        bc = new Texture("flightBars/7.png");
        bd = new Texture("flightBars/4.png");
        be = new Texture("flightBars/2.png");
        bf = new Texture("flightBars/0.png");

        this.flightTime = flightTime;

    }

    public void updateFlightTime(FlightBar fbar, int x){
        flightTime = x;
    }

    public Texture getBar(FlightBar fbar){

        if (fbar.flightTime > 97){
            return aa;
        }
        else if (fbar.flightTime <= 97 && fbar.flightTime > 94){
            return ab;
        }
        else if (fbar.flightTime <= 94 && fbar.flightTime > 90){
            return ac;
        }
        else if (fbar.flightTime <= 90 && fbar.flightTime > 87){
            return ad;
        }
        else if (fbar.flightTime <= 87 && fbar.flightTime > 84){
            return ae;
        }
        else if (fbar.flightTime <= 84 && fbar.flightTime > 80){
            return af;
        }
        else if (fbar.flightTime <= 80 && fbar.flightTime > 77){
            return ag;
        }
        else if (fbar.flightTime <= 77 && fbar.flightTime > 74){
            return ah;
        }
        else if (fbar.flightTime <= 74 && fbar.flightTime > 70){
            return ai;
        }
        else if (fbar.flightTime <= 70 && fbar.flightTime > 67){
            return aj;
        }
        else if (fbar.flightTime <= 67 && fbar.flightTime > 64){
            return ak;
        }
        else if (fbar.flightTime <= 64 && fbar.flightTime > 60){
            return al;
        }
        else if (fbar.flightTime <= 60 && fbar.flightTime > 57){
            return am;
        }
        else if (fbar.flightTime <= 57 && fbar.flightTime > 54){
            return an;
        }
        else if (fbar.flightTime <= 54 && fbar.flightTime > 50){
            return ao;
        }
        else if (fbar.flightTime <= 50 && fbar.flightTime > 47){
            return ap;
        }
        else if (fbar.flightTime <= 47 && fbar.flightTime > 44){
            return aq;
        }
        else if (fbar.flightTime <= 44 && fbar.flightTime > 40){
            return ar;
        }
        else if (fbar.flightTime <= 40 && fbar.flightTime > 37){
            return as;
        }
        else if (fbar.flightTime <= 37 && fbar.flightTime > 34){
            return at;
        }
        else if (fbar.flightTime <= 34 && fbar.flightTime > 30){
            return au;
        }
        else if (fbar.flightTime <= 30 && fbar.flightTime > 27){
            return av;
        }
        else if (fbar.flightTime <= 27 && fbar.flightTime > 24){
            return aw;
        }
        else if (fbar.flightTime <= 24 && fbar.flightTime > 20){
            return ax;
        }
        else if (fbar.flightTime <= 20 && fbar.flightTime > 17){
            return ay;
        }
        else if (fbar.flightTime <= 17 && fbar.flightTime > 14){
            return az;
        }
        else if (fbar.flightTime <= 14 && fbar.flightTime > 10){
            return ba;
        }
        else if (fbar.flightTime <= 10 && fbar.flightTime > 7){
            return bb;
        }
        else if (fbar.flightTime <= 7 && fbar.flightTime > 4){
            return bc;
        }
        else if (fbar.flightTime <= 4 && fbar.flightTime > 2){
            return bd;
        }
        else if (fbar.flightTime <= 2 && fbar.flightTime > 0){
            return be;
        }
        else {
            return bf;
        }
    }

    public void dispose(){
        aa.dispose();
        ab.dispose();
        ac.dispose();
        ad.dispose();
        ae.dispose();
        af.dispose();
        ag.dispose();
        ah.dispose();
        ai.dispose();
        aj.dispose();
        ak.dispose();
        al.dispose();
        am.dispose();
        an.dispose();
        ao.dispose();
        ap.dispose();
        ar.dispose();
        as.dispose();
        at.dispose();
        au.dispose();
        av.dispose();
        aw.dispose();
        ax.dispose();
        ay.dispose();
        az.dispose();
        ba.dispose();
        bb.dispose();
        bc.dispose();
        bd.dispose();
        be.dispose();
        bf.dispose();
    }
}

