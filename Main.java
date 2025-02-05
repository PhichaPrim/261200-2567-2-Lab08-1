import java.util.Random;

public class Main {
    public static void main(String[] args) {
        NumberPrinter thread1 = new NumberPrinter(1);
        NumberPrinter thread2 = new NumberPrinter(2);
        NumberPrinter thread3 = new NumberPrinter(3);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class NumberPrinter extends Thread {
    private int threadNumber;

    public NumberPrinter(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 1; i <= 50; i++) {
            try {
                Thread.sleep(random.nextInt(91) + 10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println("thread #" + threadNumber + " : " + i);
        }
    }
}

//   Before adding Thread.sleep() : ตอนแรก threads จะทำการพิมพ์ข้อความโดยไม่ชะลอเวลา การแสดงผลจะไม่เป็นระเบียบและการพิมพ์จะขึ้นอยู่กับการจัดสรรเวลา ผลลัพธ์จะแสดงให้เห็นว่า thread 1 2 3 อาจจะมาในลำดับใดก็ได้
//   After adding Thread.sleep() : การพิมพ์แต่ละหมายเลขจะชะลอเวลาแบบสุ่ม โดยแต่ละ thread จะหยุดพักระหว่าง 10-100 มิลลิวินาทีหลังจากการพิมพ์ ผลลัพธ์จะมีความหลากหลายมากขึ้น โดยผลลัพธ์จะแสดงเป็นลำดับที่ไม่คงที่

// Multithreading ใน Java คือการให้โปรแกรมทำงานหลายงานพร้อมกัน โดยใช้ Thread หรือ Runnable ในการสร้างและควบคุม threads.
// ลำดับผลลัพธ์ที่เปลี่ยนแปลง เกิดจากการที่ระบบปฏิบัติการจัดการเวลาการทำงานของ threads ต่างกันในแต่ละครั้งที่รันโปรแกรม