package com.zac.samples;

/*
 * 线程安全与不安全
 * http://blog.csdn.net/ghsau/article/details/7421217
 */
public class ThreadTest {
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			Count count = new Count();

			public void run() {
				count.count();
			}
		};
		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}

		//
		System.out.println("将线程类成员变量拿到run方法中，这时count引用是线程内的局部变量");
		Runnable runnable2 = new Runnable() {
			public void run() {
				Count count = new Count();
				count.count();
			}
		};
		for (int i = 0; i < 10; i++) {
			new Thread(runnable2).start();
		}
	}
}
