package Q1;
/*
 Q. Implement singleton design pattern, to ensure that a class have only one instance
and provide global point of access to it
 */

import java.io.*;

enum MySingleton {
	INSTANCE;
}

// Singleton: Ensure that a class has only one instance and provide a global
// point of access to it

class Singleton implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	// Use volatile as double lock checking is also broken
	private static volatile Singleton singleton = null; // Lazy instantiation

	private Singleton() { // private constructor
		if (singleton != null) {

			// Reflection Problem-Throw IllegalStateException if someone tries to break our
			// singleton by accessing private constructor
			throw new IllegalStateException();
		}
	}

	public static Singleton getSingleton10() {
		if (singleton == null) {
			if (singleton == null) {
				// using double lock checking with synchronized block to tackle multithreading
				// issue
				synchronized (Singleton.class) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
	// Serialization Problem - Constructor does not get called while deserialization
	// so we define readResolve()
	// Now JVM will not deserialize but invoke the readResolve() method when someone
	// tries to deserialize our object.

	private Object readResolve() {
		return singleton;
	}

	// Clonning Problem- We will throw exception in clone method and return the
	// singleton object so that no one can make clone of our object
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return singleton;
	}
}

public class Q1 {
	public static void main(String[] args) {
		// Enum singleton pattern, Enum is safest way but not flexible
		// It is a misuse of Enum concept yet it se better choice for singleton pattern.
		MySingleton singletonEnum = MySingleton.INSTANCE;
		System.out.println(singletonEnum.hashCode());

		MySingleton singletonEnum2 = MySingleton.INSTANCE;
		System.out.println(singletonEnum2.hashCode());
	}
}