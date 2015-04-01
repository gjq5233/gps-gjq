import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

	public static int count = 0;

	public static volatile int volatileCount = 0;
	public static AtomicInteger atomicCount = new AtomicInteger(0);

	public static byte[] lock = new byte[0];
	public static int syCount = 0;

	public static void sleep(final int gap) {
		try {
			Thread.sleep(gap);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void incrInt(final int gap) {
		count++;
		sleep(gap);
	}

	public static void incrVolatileInt(int gap) {
		volatileCount++;// 和volatileCount上一个状态相关，不具于原子性
		sleep(gap);
	}

	public static void incrAtomicInt(final int gap) {
		// 第一种方式：
		// int tmp=atomicCount.get();
		// atomicCount.getAndSet(tmp+1);//注意：tmp+1不具原子性

		// 第二种方式:
		atomicCount.getAndIncrement();// 具备原子性的
		sleep(gap);
	}

	public static void incrSynInt(final int gap) {
		synchronized (lock) {
			syCount++;
			sleep(gap);
		}
	}

	public static void testInt(int count, final int gap) {

		Thread threads[] = new Thread[count];
		for (int i = 0; i < count; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						Counter.incrInt(gap);
					}
				}
			});

		}
		long st = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			
			threads[i].start();
		
	}
		try {
		for (int i = 0; i < count; i++) {
			
				threads[i].join();
			
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long et = System.currentTimeMillis();
		System.out.println("---运行时间＝" + (et - st));
		// 这里每次运行的值都有可能不同,可能为1000
		System.out.println("运行结果:Counter.count=" + Counter.count);
	}

	public static void testVolatileInt(int count, final int gap) {

		Thread threads[] = new Thread[count];
		for (int i = 0; i < count; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						Counter.incrVolatileInt(gap);
					}

				}
			});

		}
		long st = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			threads[i].start();
		}
		try {
			for (int i = 0; i < count; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long et = System.currentTimeMillis();
		System.out.println("---运行时间＝" + (et - st));
		System.out.println("运行结果:Counter.volatileCount="
				+ Counter.volatileCount);
	}

	public static void testAtomicInt(int count, final int gap) {

		Thread threads[] = new Thread[count];
		for (int i = 0; i < count; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						Counter.incrAtomicInt(gap);
					}
				}
			});

		}
		long st = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			
			threads[i].start();
		
	}
		try {
		for (int i = 0; i < count; i++) {
			
				threads[i].join();
			
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long et = System.currentTimeMillis();
		System.out.println("---运行时间＝" + (et - st));
		System.out.println("运行结果:Counter.atomicCount="
				+ Counter.atomicCount.get());
	}

	public static void testSynInt(int count, final int gap) {

		Thread threads[] = new Thread[count];
		for (int i = 0; i < count; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						Counter.incrSynInt(gap);
					}
				}
			});

		}
		long st = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			
			threads[i].start();
		
	}
		try {
		for (int i = 0; i < count; i++) {
			
				threads[i].join();
			
		}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long et = System.currentTimeMillis();
		System.out.println("---运行时间＝" + (et - st));
		System.out.println("运行结果:Counter.syCount=" + Counter.syCount);
	}

	public static void main(String[] args) {
		int cnt = 32;
		int gap = 1;
		testInt(cnt, gap);
		System.out.println("-------------------------------------------");
		testVolatileInt(cnt, gap);
		System.out.println("-------------------------------------------");
		testAtomicInt(cnt, gap);
		System.out.println("-------------------------------------------");
		testSynInt(cnt, gap);
	}
}