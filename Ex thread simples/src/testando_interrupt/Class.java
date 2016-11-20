package testando_interrupt;

public class Class  implements Runnable {
	public void run() {
      try {
         System.out.println(Thread.currentThread().getName()+" - iniciou, executou metodo2()");
         metodo2();
         System.out.println(Thread.currentThread().getName()+" - terminou metodo2()");
      }
      catch (InterruptedException x) {
         System.out.println(Thread.currentThread().getName()+" -interrompida durante o metodo2");
         return;
      }
      System.out.println(Thread.currentThread().getName()+" - final do run()");
      System.out.println(Thread.currentThread().getName()+"- leaving normally");
   }

	public void metodo2() throws InterruptedException {
		int i=0;
		while (i<1000) {
			i++;
			Thread.sleep(900);
			System.out.println(i);
			if (Thread.currentThread().isInterrupted()) {
				System.out.println(Thread.currentThread().getName() + " isInterrupted()= " + Thread.currentThread().isInterrupted());
				
			}
		}
	}

	public static void main(String[] args) {
      Class si = new Class();
      Thread t = new Thread(si);
      t.start();
      System.out.println("iniciou a thread 0, colocando a thread "+Thread.currentThread().getName()+ " para dormir 4s");
      try {
         Thread.sleep(4000);
      }
      catch (InterruptedException x) {
      }
      
      System.out.println(Thread.currentThread().getName()+" - interrompendo a thread 0");
      t.interrupt();
      
      
      try {
		Thread.sleep(8000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      
      System.out.println("in main() - leaving");
   }
}