import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        System.out.println("What mode would you like:");
        System.out.println("Beginner,Intermediate, or Expert?");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        /*System.out.println("Would you like to play in debug mode?");
        Scanner c = new Scanner(System.in);
        String next = c.nextLine();
        if(next.equals("yes")) {

        }*/
        if(input.equals("Beginner")){
            Board b = new Board(3);
            //b.placeBoats(3);
            Scanner debug = new Scanner(System.in);
            System.out.println("Would you like to play in debug mode, yes or no?");
            String debugInput = debug.nextLine();
            int z = 0;
            if (debugInput.equals("yes")) {
                System.out.println("Game beginning in debug mode");
                z=1;
                b.setDebug();
            } else if (debugInput.equals("no")) {//z=0
                System.out.println("Game beginning normally.");
            } else {
                System.out.println("Invalid input, game starting normally.");
            }
            while(b.getB_remain()>0){
                System.out.println("Turn " + b.getTurns() + ": Where would you like to hit?" );
                Scanner ss = new Scanner(System.in);
                String put = ss.nextLine();
                if(put.equals("missile")){
                    System.out.println("Where would you like to hit with the missle");
                    Scanner m = new Scanner(System.in);
                    String mi = m.nextLine();
                    int result = Integer.parseInt(mi);
                    int left = result%10;
                    result = result/10;
                    b.missile(result,left);
                    b.display();
                }
                else if(put.equals("drone")){
                    b.drone();
                    b.display();
                }
                else if(put.equals("submarine")){
                    System.out.println("Where would you like to attack with the submarine");
                    Scanner w = new Scanner(System.in);
                    String mi = w.nextLine();
                    int index0 = Integer.parseInt(mi);
                    int index1 = index0%10;
                    index0 = index0/10;
                    b.submarine(index0,index1);
                    b.display();
                }
                else{
                    //Scanner w = new Scanner(System.in);
                   // String mi = w.nextLine();
                    if(z==0){
                        int index0 = Integer.parseInt(put);
                        int index1 = index0%10;
                        index0 = index0/10;
                        b.fire(index0,index1);
                        b.print();
                    }
                    else{
                        int index0 = Integer.parseInt(put);
                        int index1 = index0%10;
                        index0 = index0/10;
                        b.fire(index0,index1);
                        b.display();
                    }

                }

            }

        }
        else if(input.equals("Intermediate")){
            Board b = new Board(6);
            Scanner debug = new Scanner(System.in);
            System.out.println("Would you like to play in debug mode, yes or no?");
            String debugInput = debug.nextLine();
            int z = 0;
            if (debugInput.equals("yes")) {
                System.out.println("Game beginning in debug mode");
                z=1;
                b.setDebug();
            } else if (debugInput.equals("no")) {//z=0
                System.out.println("Game beginning normally.");
            } else {
                System.out.println("Invalid input, game starting normally.");
            }
            while(b.getB_remain()>0){
                System.out.println("Turn " + b.getTurns() + ": Where would you like to hit?" );
                Scanner ss = new Scanner(System.in);
                String put = ss.nextLine();
                if(put.equals("missile")){
                    System.out.println("Where would you like to hit with the missle");
                    Scanner m = new Scanner(System.in);
                    String mi = m.nextLine();
                    int result = Integer.parseInt(mi);
                    int left = result%10;
                    result = result/10;
                    b.missile(result,left);
                    b.display();
                }
                else if(put.equals("drone")){
                    b.drone();
                    b.display();
                }
                else if(put.equals("submarine")){
                    System.out.println("Where would you like to attack with the submarine");
                    Scanner w = new Scanner(System.in);
                    String mi = w.nextLine();
                    int index0 = Integer.parseInt(mi);
                    int index1 = index0%10;
                    index0 = index0/10;
                    b.submarine(index0,index1);
                    b.display();
                }
                else{
                    if(z==0){
                        int index0 = Integer.parseInt(put);
                        int index1 = index0%10;
                        index0 = index0/10;
                        b.fire(index0,index1);
                        b.print();
                    }
                    else{
                        int index0 = Integer.parseInt(put);
                        int index1 = index0%10;
                        index0 = index0/10;
                        b.fire(index0,index1);
                        b.display();
                    }
                }

            }

        }
        else if(input.equals("Expert")){
            Board b = new Board(9);
            //b.placeBoats();
            b.display();
            while(b.getB_remain()>0){
                System.out.println("Turn " + b.getTurns() + ": Where would you like to hit?" );
                Scanner ss = new Scanner(System.in);
                String put = ss.nextLine();
                if(put.equals("missile")){
                    System.out.println("Where would you like to hit with the missle");
                    Scanner m = new Scanner(System.in);
                    String mi = m.nextLine();
                    int result = Integer.parseInt(mi);
                    int left = result%10;
                    result = result/10;
                    b.missile(result,left);
                    b.display();
                }
                else if(put.equals("drone")){
                    b.drone();
                    b.display();
                }
                else if(put.equals("submarine")){
                    System.out.println("Where would you like to attack with the submarine");
                    Scanner w = new Scanner(System.in);
                    String mi = w.nextLine();
                    int index0 = Integer.parseInt(mi);
                    int index1 = index0%10;
                    index0 = index0/10;
                    b.submarine(index0,index1);
                    b.display();
                }
                else{
                    //Scanner w = new Scanner(System.in);
                    // String mi = w.nextLine();
                    int index0 = Integer.parseInt(put);
                    int index1 = index0%10;
                    index0 = index0/10;
                    b.fire(index0,index1);
                    b.display();
                }

            }
        }
        else{
            System.out.println("Incorrect Mode Selected.");
        }

        //String input = s.nextLine();

    }
}
