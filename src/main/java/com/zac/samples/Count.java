package com.zac.samples;

public class Count {
	private int num;

	public void count() {
		for (int i = 1; i <= 10; i++) {
			num += i;
		}
		System.out.println(Thread.currentThread().getName() + "-" + num);
	}

	public void count2() {
	    System.out.println("将Count中num变成count方法的局部变量");
		int num = 0;
		for (int i = 1; i <= 10; i++) {
			num += i;
		}
		System.out.println(Thread.currentThread().getName() + "-" + num);
	}
}