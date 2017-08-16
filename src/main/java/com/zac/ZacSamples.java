import java.util.*;
import java.lang.*;
import java.io.*;

class ZacSamples { 
    public static void main(String[] args) throws java.lang.Exception {
        
        System.out.println("=== 配列のサンプル ===");
        // 変数numbersに、与えられた数字の配列を代入してください
        int[] numbers = {1, 4, 6, 9, 13, 16};
        int oddSum = 0;
        int evenSum = 0;
        // for文を用いて、配列numbersの偶数の和と奇数の和を求めてください
        for (int number:numbers){
            if (number % 2 == 0){
                evenSum = evenSum + number;
            } else {
                oddSum = oddSum + number;
            }
        }
        System.out.println("奇数の和は" + oddSum + "です");
        System.out.println("偶数の和は" + evenSum + "です");
 


        // 変数namesに、配列を代入してください
        String[] names = {"にんじゃわんこ", "ひつじ仙人", "ベイビーわんこ"};
        System.out.println("names length = " + names.length);
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
        // 拡張for文
        System.out.println("=== 拡張for文 ===");
        for (String name:names) {
            System.out.println(name);
        }
        // インデックス番号が0の要素を出力してください
        System.out.println(names[0]);
        // インデックス番号が2の要素を出力してください
        System.out.println(names[2]);        
        
        // breakを使って強制的に終了させる方法
        System.out.println("=== break ===");
        for (int i = 1; i <= 10; i++) {
            if (i > 5) {
                break;
            }
            System.out.println(i);
        }
        
        // continueを使ってその周の処理だけをスキップして、次の周を実行する
        System.out.println("=== continue ===");
        for (int i = 1; i <= 10; i++) {
            if (i % 3 == 0) {
                continue;
            }
            System.out.println(i);
        }
        
        // while
        System.out.println("=== while文 ===");
        int i = 1;
        while (i < 10) {
            // iが5の倍数のとき、繰り返し処理を終了してください
            if (i % 5 == 0) {
                break;
            }
            System.out.println(i);
            i++;
        }
        
        // 入力のサンプル
        System.out.println("=== input ===");
        System.out.print("名前：");
        //Scanner scan = new Scanner(System.in);
        //String firstName = scan.next();
        //System.out.println("名前は" + firstName + "です");

        // StringBuilderとStringBufferの違い
        // StringBuffer JDK1.0
        // 可変文字列を扱うためのクラス。スレッドセーフ。
        // StringBuilder JDK1.5
        // 可変文字列を扱うためのクラス。同期化は保証されませんが、その分高速に処理できます。
        // APIリファレンスでは単一スレッドの場合はこちらを使用することが推奨されています。
          private static void dispHantei(String name, int tokuten){
    StringBuilder sb = new StringBuilder();

    sb.append(name);
    sb.append("さんの成績は");
    sb.append(tokuten);
    sb.append("点です。結果は");
    if (tokuten > 75){
      sb.append("合格");
    }else{
      sb.append("不合格");
    }
    sb.append("です。");

    System.out.println(new String(sb));
  }
    }
}
