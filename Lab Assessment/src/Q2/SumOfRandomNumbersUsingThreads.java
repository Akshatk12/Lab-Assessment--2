package Q2;

import java.util.Random;

/*
 Q. Write a program that runs 5 threads, each thread randomizes a number 
between 1 and 10. The main thread waits for all the others to finish, calculates 
the sum of the numbers which were randomized and prints that sum. You will 
need to implement a Runnable class that randomizes a number and store it in a 
member field. When all the threads have done, your main program can go over 
all the objects and check the stored values in each object
 */

class Sum implements Runnable {
	Random random = new Random();
	private int sum;

	private boolean isDone = false;
	int count = 0;

	@Override
	public void run() {
		int rNumber = random.nextInt(10) + 1;
		System.out.println("Number : " + rNumber);
		count++;
		sum = sum + rNumber;
		if (count == 5) {

			isDone = true;
			synchronized (this) {
				notifyAll();
			}
		}
	}

	public synchronized int getSum() {
		if (!isDone) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return sum;
	}
}

public class SumOfRandomNumbersUsingThreads {
	public static void main(String[] args) {
		Sum sum = new Sum();
		Thread thread1 = new Thread(sum, "Thread 1");
		Thread thread2 = new Thread(sum, "Thread 2");
		Thread thread3 = new Thread(sum, "Thread 3");
		Thread thread4 = new Thread(sum, "Thread 4");
		Thread thread5 = new Thread(sum, "Thread 5");
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();

		System.out.println("Sum of Numbers using threads:" + sum.getSum());
	}

}