package com.zac.samples;

import java.util.concurrent.CountDownLatch;

/*
 * StringBuffer与StringBuilder的线程安全性验证
 * 此示例程序做了如下事情：
 * 基于初始字符串“AAAABBBB”分别构建StringBuffer和StringBuilder对象
 * 分别启动1000个线程，调用StringBuffer和StringBuilder的reverse方法，进行字符串反转
 * 所有线程执行完后打印结果，由于反转偶数次，线程安全的对象输出应与初始值相同，线程不安全的对象则可能产生乱序
 * 发现StringBuffer输出与初始值相同，StringBuilder输出产生乱序。多次执行或调大线程数StringBuffer输出结果不变，由此二者线程安全性得证。
 * 
 */
public class StringBufferAndStringBuilderTest {
	private static final int THREAD_NUM = 10;

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String strToReverse = "AAAABBBB";

		StringBuffer stringBuffer = new StringBuffer(strToReverse);
		StringBuilder stringBuilder = new StringBuilder(strToReverse);
		
		CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
		CountDownLatch countDownLatch2 = new CountDownLatch(THREAD_NUM);

		for (int i = 0; i < THREAD_NUM; i++) {
			new SbTaskThread(stringBuilder, countDownLatch).start();
			new SbTaskThread(stringBuffer, countDownLatch2).start();
		}

		try {
			countDownLatch.await();
			countDownLatch2.await();
			System.out.println("StringBuffer toString: "
					+ stringBuffer.toString());
			System.out.println("StringBuilder toString: "
					+ stringBuilder.toString());
			long endTime = System.currentTimeMillis();
			System.out.println("Running time: " + (endTime - startTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class SbTaskThread extends Thread {
	private static final String STARTER = "-start";
	private static final String ENDER = "-end";
	private Object zObject = null;
	private CountDownLatch countDownLatch; // 记载运行线程数

	public SbTaskThread(StringBuilder stringBuilder,
			CountDownLatch countDownLatch) {
		super();
		this.zObject = stringBuilder;
		this.countDownLatch = countDownLatch;
	}

	public SbTaskThread(StringBuffer stringBuffer,
			CountDownLatch countDownLatch) {
		super();
		this.zObject = stringBuffer;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + STARTER);
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(200);
				if (zObject instanceof StringBuffer) {
					((StringBuffer) zObject).reverse();
					System.out.println("Buffer->" + zObject.toString());
				} else if (zObject instanceof StringBuilder) {
					((StringBuilder) zObject).reverse();
					System.out.println("Builder->" + zObject.toString());
				}
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + ENDER);
		countDownLatch.countDown();
	}
}
