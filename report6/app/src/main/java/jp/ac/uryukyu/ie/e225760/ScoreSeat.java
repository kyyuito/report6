package jp.ac.uryukyu.ie.e225760;

class Score {
    String roleName;
    int roleScores;
    Score(String roleName,int roleScores){
        this.roleName = roleName;
        this.roleScores = roleScores;
    }
}

public class ScoreSeat {
    Score[] scores; 
    ScoreSeat(){
        this.scores = new Score[14];
        scores[0] = new Score("エース",0);
        scores[1] = new Score("デュース",0);
        scores[2] = new Score("トレイ",0);
        scores[3] = new Score("フォー",0);
        scores[4] = new Score("ファイブ",0);
        scores[5] = new Score("シックス",0);
        scores[6] = new Score("チョイス",0);
        scores[7] = new Score("フォーダイス",0);
        scores[8] = new Score("フルハウス",0);
        scores[9] = new Score("Sストレート",0);
        scores[10] = new Score("Bストレート",0);
        scores[11] = new Score("ヨット",0);
        scores[12] = new Score("ボーナス",0);
        scores[13] = new Score("合計",0);
    }
    void showScoreSeat(){
        System.out.println("ーーーーーーー");
        for(int i=0;i<scores.length;i++){ 
            System.out.println(scores[i].roleName+"："+scores[i].roleScores);
        }
        System.out.println("ーーーーーーー");
    }
    
}