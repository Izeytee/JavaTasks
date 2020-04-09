package ru.sibsutis;
import java.util.*;

public class GCAndCollections {
	public static void main(String[] args) {
		//System.out.println("Hello world");
		/* normally, overflows after 41800
		for (int i = 1; i < 100000; i += 100) {
			f(i);
			System.out.println(i);
		}*/

		TreeSet<Integer> ts = new TreeSet<>();
        	HashSet<Integer> hs = new HashSet<>();
        	ArrayList<Integer> al = new ArrayList<>();
        	LinkedList<Integer> ll = new LinkedList<>();
       	 	LinkedHashSet<Integer> lhs = new LinkedHashSet<>();
	
      	  	long t = System.nanoTime();
        	for (int i = 0; i < 1000000; ++i)
            		ts.add(i);
        	System.out.println(System.nanoTime() - t);
        	t = System.nanoTime();
        	for (int i = 0; i < 1000000; ++i)
            		ts.remove(i);
        	System.out.println(System.nanoTime() - t);
		System.gc();

        	for (int i = 0; i < 1000000; ++i)
            		hs.add(i);
        	System.out.println(System.nanoTime() - t);
        	t = System.nanoTime();
        	for (int i = 0; i < 100000; ++i)
            		hs.remove(i);
        	System.out.println(System.nanoTime() - t);
		System.gc();

        	for (int i = 0; i < 100000; ++i)
            		al.add(i);
       	 	System.out.println(System.nanoTime() - t);
        	t = System.nanoTime();
        	for (int i = 0; i < 100000; ++i)
            		al.remove(0);
        	System.out.println(System.nanoTime() - t);
		System.gc();

        	for (int i = 0; i < 100000; ++i)
            		ll.add(i);
        	System.out.println(System.nanoTime() - t);
        	t = System.nanoTime();
        	for (int i = 0; i < 100000; ++i)
            		ll.remove(0);
        	System.out.println(System.nanoTime() - t);
		System.gc();

        	for (int i = 0; i < 1000000; ++i)
            		lhs.add(i);
        	System.out.println(System.nanoTime() - t);
        	t = System.nanoTime();
        	for (int i = 0; i < 1000000; ++i)
            		lhs.remove(i);
        	System.out.println(System.nanoTime() - t);
	}
	static int f(int i) {
		return i != 1 ? i * f(i - 1) : 1;
	}
}

/* from javap -c ***
public class Main {
  public Main();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String Hello world
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}
*/