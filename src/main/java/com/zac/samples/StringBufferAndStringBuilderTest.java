package com.zac.samples;

import java.util.concurrent.CountDownLatch;

/*
 * StringBuffer与StringBuilder的线程安全性验证
 * 此示例程序做了如下事情：
 * 基于初始字符串“1234567890”分别构建StringBuffer和StringBuilder对象
 * 分别启动10个线程，调用StringBuffer和StringBuilder的reverse方法，进行字符串反转
 * 所有线程执行完后打印结果，由于反转偶数次，线程安全的对象输出应与初始值相同，线程不安全的对象则可能产生乱序
 * 发现StringBuffer输出与初始值相同，StringBuilder输出产生乱序。
 * 多次执行或调大线程数StringBuffer输出结果不变，由此二者线程安全性得证。
 * 
 * StringBuilderはスレッドセーフではないシンプルなケースに適用でき、それ以外の場合は StringBuffer を使用すると良い
 * StringBuilderは文字の可変シーケンスです。このクラスは、StringBufferと互換性があるAPIを提供しますが、同期化は保証されません。
 * このクラスは、文字列バッファが単一のスレッド(一般的なケース)により使用されていた場合のStringBufferの簡単な代替として使用されるよう設計されています。
 * このクラスは、ほとんどの実装で高速に実行されるので、可能な場合は、StringBufferよりも優先して使用することをお薦めします。
 * 
 * StringBuffer
 * スレッドセーフであるので複数スレッドから参照されるような場合に適切
 * スレッド間の排他制御を実装している。
 * StringBuilder
 * シングルスレッドで排他処理の必要がないシンプルな処理の場合に適切
 * 使用できる場合はこちらのクラスを使用したほうがStringBuilderよりも高速に処理が行える
 */
public class StringBufferAndStringBuilderTest {
	private static final int THREAD_NUM = 10;

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String strToReverse = "1234567890";

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
