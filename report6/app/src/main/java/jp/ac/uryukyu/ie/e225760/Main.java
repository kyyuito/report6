package jp.ac.uryukyu.ie.e225760;
import java.util.ArrayList;

public class Main { 
    public static void main(String[] args){
        Player a = new Player("a");
        Player b = new Player("b");
        ArrayList<Player> turn = new ArrayList<>();
        turn.add(a);
        turn.add(b);
        for(int i=1;i<=12;i++){
            System.out.println(i+"ターン目");
            for(int in=0;in<turn.size();in++){
                System.out.println(turn.get(in).name+"の番");
                turn.get(in).myScore.showScoreSeat();
                turn.get(in).rollDice();
                turn.get(in).decideRole();
            }
        }
        for(int inn=0;inn<turn.size();inn++){
            turn.get(inn).sum();
            turn.get(inn).myScore.showScoreSeat();
        }
        
    }
        
       
    
}