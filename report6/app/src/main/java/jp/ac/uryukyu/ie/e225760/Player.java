package jp.ac.uryukyu.ie.e225760;
import java.util.ArrayList;
public class Player {
    String name;
    ArrayList <Dice> dices = new ArrayList<>();//サイコロ５つ使う
    ScoreSeat myScore = new ScoreSeat();
    CommandSelector co_role = new CommandSelector();//役のコマンド
    CommandSelector co_dice = new CommandSelector();//サイコロのコマンド

    Player(String name){
        this.name = name;
        dices.add(new Dice("サイコロa",0));
        dices.add(new Dice("サイコロb",0));
        dices.add(new Dice("サイコロc",0));
        dices.add(new Dice("サイコロd",0));
        dices.add(new Dice("サイコロe",0));
        for(int i_dice=0;i_dice<12;i_dice++){
            co_role.addCommand(myScore.scores[i_dice].roleName);
        }
        for(int i=0;i<dices.size();i++){
            co_dice.addCommand(dices.get(i).name);
        }   
    }
    //サイコロふる
    void rollDice(){
        System.out.println("サイコロを振る");
        for(int ii=0;ii<dices.size();ii++){
            dices.get(ii).rollTheDice();
            dices.get(ii).showDice();
        }
        for(int iii=0;iii<2;iii++){
            var dice =co_dice.waitForUsersCommandDice("どのサイコロを振り直す？");
            if(dice.contains(5)==false){
               for(int iiii=0;iiii<dice.size();iiii++){
                   dices.get(dice.get(iiii)).rollTheDice();
                } 
            }
            for(int in=0;in<dices.size();in++){
                dices.get(in).showDice();
            }
            
        }
    }
    //役をきめる
    void decideRole(){
        myScore.showScoreSeat();
        var ro = co_role.waitForUsersCommandRole("どの役にする？");
        switch(ro){
            case 0: ace_six(ro); 
                    bonus();break;
            case 1: ace_six(ro); 
                    bonus();break;
            case 2: ace_six(ro);
                    bonus();break;
            case 3: ace_six(ro); 
                    bonus();break;
            case 4: ace_six(ro); 
                    bonus();break;
            case 5: ace_six(ro); 
                    bonus();break; 
            case 6: choice();break;
            case 7: fourDice();break;
            case 8: fullHouse();break;
            case 9: sStraight();break;
            case 10: bStraight();break;
            case 11: yacht();break;   
        }
        myScore.showScoreSeat();
        co_role.commands.set(ro, "選択済み");
    }
    //エース〜シックスの時の処理
    void ace_six(int index){
        for(int a=0;a<dices.size();a++){
            if(dices.get(a).roll==index+1){
               myScore.scores[index].roleScores += dices.get(a).roll; 
            } 
        }
    }
    
    //ボーナスの時の処理
    void bonus(){
        int bon=0;//適当な変数　なんでもいい
        for(int b=0;b<6;b++){
            bon+=myScore.scores[b].roleScores;
        }
        if(bon>=63){
            myScore.scores[12].roleScores+=35;
        }
    }
    //チョイスの時の処理
    void choice(){
        for(int c=0;c<dices.size();c++){
            myScore.scores[6].roleScores += dices.get(c).roll;
        }
    }
    //フォーダイスの時の処理・・・思い付かない
    void fourDice(){
        for (int fu=0;fu<dices.size();fu++){
            myScore.scores[7].roleScores +=dices.get(fu).roll;
        }
    }
    //フルハウスの時の処理・・・思い付かない
    void fullHouse(){
        for (int fu=0;fu<dices.size();fu++){
            myScore.scores[8].roleScores +=dices.get(fu).roll;
        }
    }
    //sストレートの時の処理  代数　algebra
    void sStraight(){
        ArrayList<Integer> check_1 = new ArrayList<>();
        ArrayList<Integer> check_2 = new ArrayList<>();
        ArrayList<Integer> check_3 = new ArrayList<>();
        ArrayList <Integer> alg = new ArrayList<>();
        //sストレートになる時の場合わけ
        for(int s_1=1;s_1<=4;s_1++){
            check_1.add(s_1);
        }
        for(int s_2=2;s_2<=5;s_2++){
            check_2.add(s_2);
        }
        for(int s_3=3;s_3<=6;s_3++){
            check_3.add(s_3);
        }
        //dicesのままじゃ比較できない　dices.rollを取り出す
        for(int s_4=0;s_4<dices.size();s_4++){
            alg.add(dices.get(s_4).roll);
        }
        //containsメソッド
        if(alg.containsAll(check_1)||alg.containsAll(check_2)||alg.containsAll(check_3)){
            myScore.scores[9].roleScores += 15;
        }   
    }
    //bストレートの時の処理　sストレートとだいたい一緒
    void bStraight(){
        ArrayList<Integer> check_1 = new ArrayList<>();
        ArrayList<Integer> check_2 = new ArrayList<>();
        ArrayList <Integer> alge = new ArrayList<>();
        for(int b_1=1;b_1<=5;b_1++){
            check_1.add(b_1);
        }
        for(int b_2=2;b_2<=6;b_2++){
            check_2.add(b_2);
        }
        for(int b_3=0;b_3<dices.size();b_3++){
            alge.add(dices.get(b_3).roll);
        }
        if(alge.containsAll(check_1)||alge.containsAll(check_2)){
            myScore.scores[10].roleScores+=30;
        }
    }
    //ヨットの時の処理
    void yacht(){
        ArrayList<Integer> algeb = new ArrayList<>();
        ArrayList<Integer> check = new ArrayList<>();
        for(int y=0;y<dices.size();y++){
            algeb.add(dices.get(y).roll);
        }
        for(int y_1=1;y_1<=6;y_1++){
            for(int y_2=0;y_2<5;y_2++){
                check.add(y_1);
            }
            if(algeb==check){
                myScore.scores[11].roleScores+=50;
            }
            check.clear();
        }
    }
    //合計の処理
    int sum(){
        for(int s=0;s<myScore.scores.length-1;s++){
            myScore.scores[13].roleScores += myScore.scores[s].roleScores;
        }
        return myScore.scores[13].roleScores;
    }
}