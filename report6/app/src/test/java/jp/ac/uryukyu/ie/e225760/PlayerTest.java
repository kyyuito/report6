package jp.ac.uryukyu.ie.e225760;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
class PlayerTest {
    
    @Test void aceTest(){
        Player a = new Player("a");
        ArrayList<Integer> di = new ArrayList<>();//代数
        for(int i=0;i<a.dices.size();i++){
            a.dices.get(i).rollTheDice();
            a.dices.get(i).showDice();
            di.add(a.dices.get(i).roll);//代入
        }
        a.ace_six(0);
        a.myScore.showScoreSeat();
        //スコアシートのエースのところに値が保存されているか
        if(di.contains(1)){
            assertTrue(a.myScore.scores[0].roleScores>=1);
        } else{
            assertTrue(a.myScore.scores[0].roleScores==0);
        }
        
    }
    
    //エースからシックスまでの合計が63を超えたらボーナスの値に点数が入るか
    @Test void bonusTest(){
        Player a = new Player("a");
        for(int i=0;i<a.dices.size();i++){
            a.myScore.scores[i].roleScores+=15;
        }
        a.bonus();
        a.myScore.showScoreSeat();
        assertTrue(a.myScore.scores[12].roleScores==35);
    }
    //サイコロの目がsストレートになる場合にsストレートの値に点数が入るか
    @Test void sStraightTeut(){//sStraight()と動作はあまり変わらない
        Player a = new Player("a");
        ArrayList<Integer> di = new ArrayList<>();//代数
        ArrayList<Integer> check_11 = new ArrayList<>();
        ArrayList<Integer> check_22 = new ArrayList<>();
        ArrayList<Integer> check_33= new ArrayList<>();
        //場合分け
        for(int s_11=1;s_11<=4;s_11++){
            check_11.add(s_11);
        }
        for(int s_22=2;s_22<=5;s_22++){
            check_22.add(s_22);
        }
        for(int s_33=3;s_33<=6;s_33++){
            check_33.add(s_33);
        }

        for(int i=0;i<a.dices.size();i++){
            a.dices.get(i).rollTheDice();
            a.dices.get(i).showDice();
            di.add(a.dices.get(i).roll); //代入
        }
        a.sStraight();
        a.myScore.showScoreSeat();
        if(di.containsAll(check_11)||di.containsAll(check_22)||di.containsAll(check_33)){
            assertTrue(a.myScore.scores[9].roleScores==15);
        }else{
            assertTrue(a.myScore.scores[9].roleScores==0);
        }

    }
}    