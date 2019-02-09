import java.util.*;
import java.lang.*;

class Main
{  
    private static Train train;
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int jk = -1;
        lable: while(jk != 0)
        {
            System.out.println("0 - exit, 1 - set, 2 - count");
            jk = scanner.nextInt();
            switch (jk)
            {
                case 0:
                    break lable;
                case 1:
                    setTrain();
                    break;
                case 2:
                    
                    System.out.println("-------------------");
                    
                    for (Carriage cur : train.getTrain())
                    {
                        System.out.print("    ");
                    }
                    
                    System.out.println("oooooOOO");
                    
                    for (Carriage cur : train.getTrain())
                    {
                        System.out.print("    ");
                    }
                    
                    System.out.println("      ||");
                    
                    for (Carriage cur : train.getTrain())
                    {
                        System.out.print("~[" + (cur.getValue() ? "*" : " ") + "]");
                    }
        
                    System.out.println("~[_]~[=\\");
        
                    for (Carriage cur : train.getTrain())
                    {
                        System.out.print(" O O");
                    }
        
                    System.out.println(" O O O O");
        
                    System.out.println("-------------------");
                    
                    System.out.println("Number of vagons:");
                    System.out.println(count());
                    
                    break;
            }
        }
    }
    
    private static int count()
    {
        int res = 0;
        int temp = 1;
        boolean side = true;
        
        if (!train.isLight())
        {
            train.root.setValue(true);
        }
        
        while(true)
        {
            if (side)
                train.goToNext();
            else
                train.goToPrev();
            
            if (train.isLight()) {
                res = temp;
                temp = 1;
                if (side)
                    train.turnLight();
                side = !side;
                continue;
            }
            else temp += 1;
            
            if (!side)
            {
                if (temp > res) return res;
            }
        }
    }
    
    private static void setTrain()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of vagons");
        train = new Train(scanner.nextInt());
    }
}

final class Carriage {
    private Carriage prev;
    private Carriage next;
    private boolean value;
    Carriage() {
        Random random = new Random();
        this.value = random.nextBoolean();
    }
    
    Carriage(Carriage ne, Carriage pre)
    {
        Random random = new Random();
        this.value = random.nextBoolean();
        this.prev = pre;
        this.next = ne;
    }
    
    void setNext(Carriage un)
    {
        this.next = un;
    }
    
    Carriage getNext()
    {
        return this.next;
    }
    
    void setPrev(Carriage yb)
    {
        this.prev = yb;
    }
    
    Carriage getPrev()
    {
        return this.prev;
    }
    
    boolean getValue()
    {
        return this.value;
    }
    
    void setValue(boolean bn)
    {
        this.value = bn;
    }
}

final class Train {
    protected Carriage root;
    protected Carriage[] train;
    
    Carriage[] getTrain()
    {
        return this.train;
    }
    
    Train(int size) {
        Random random = new Random();
        
        this.train = new Carriage[size];
    
        for (int i = 0; i < size; i ++)
        {
            train[i] = new Carriage();
        }
    
        for (int i = 0; i < size; i ++)
        {
            train[i].setNext(train[ (i + 1) % size ]);
            train[ (i + 1) % size ].setPrev(train[i]);
        }
        
        //System.out.println(random.nextInt() % size);
        int ran = random.nextInt();
        if (ran < 0) ran *= -1;
        this.root = train[ran % size];
    }
    
    void goToNext()
    {
        this.root = this.root.getNext();
    }
    
    void goToPrev()
    {
        this.root = this.root.getPrev();
    }
    
    boolean isLight()
    {
        return this.root.getValue();
    }
    
    void turnLight()
    {
        this.root.setValue(!this.root.getValue());
    }
}
