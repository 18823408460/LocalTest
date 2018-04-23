import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2018/4/23.
 */

public class JavaMain {
        private static  List<String> list = new ArrayList<>();
        public static void main(String[] args) {
                getData();
                setData();
        }

//        public static void
        private static void setData(){
                for (int i = 0; i < 10; i++) {
                        new Thread(new Runnable() {
                                @Override
                                public void run() {
                                        for (int j = 0; j < 3; j++) {
                                                setData1("threadName="+Thread.currentThread().getName()+ "  data="+j);
                                        }
                                }
                        },"threadName-"+i).start();
                }
        }
        private static void getData(){
                new Thread(new Runnable() {
                        @Override
                        public void run() {
                           while (true){
//                                   synchronized (list){
//                                           Iterator<String> iterator = list.iterator();
//                                           while (iterator.hasNext()){
//                                                   String next = iterator.next();
//                                                   iterator.remove();
//                                                   System.out.println("data  = "+ next);
//                                           }
//                                   }
                                   try {
                                           Thread.sleep(1000);
                                   } catch (InterruptedException e) {
                                           e.printStackTrace();
                                   }
                                   getData1();
                           }
                        }
                }).start();

        }
        private  static void getData1(){
                Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()){
                        String next = iterator.next();
                        iterator.remove();
                        System.out.println("data  = "+ next);
                }
        }

        private synchronized static void getData2(){
                Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()){
                        String next = iterator.next();
                        iterator.remove();
                        System.out.println("data  = "+ next);
                }
        }

        private static  void setData(String name){
                synchronized (list){
                        list.add(name);
                }
        }

        private static synchronized void setData1(String name){
                list.add(name);
        }
}
