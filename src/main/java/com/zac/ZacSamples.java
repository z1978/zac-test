import java.util.*;
import java.lang.*;
import java.io.*;

class ZacSamples { 
    public static void main(String[] args) throws java.lang.Exception {
        
        System.out.println("=== 配列のサンプル ===");
        // 変数namesに、配列を代入してください
        String[] names = {"にんじゃわんこ", "ひつじ仙人", "ベイビーわんこ"};
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
        

        // 次のサンプル
    }
}
