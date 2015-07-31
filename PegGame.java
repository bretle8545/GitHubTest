import java.io.*;
import java.util.Arrays;

public class PegGame
{
    private static final int startOpen = 0;
    private static int[] board = new int[15];
    
    private static int[] target0 = {1,2};  
    private static int[] target1 = {3,4};  
    private static int[] target2 = {4,5};  
    private static int[] target3 = {1,4,6,7};  
    private static int[] target4 = {7,8};  
    private static int[] target5 = {2,4,8,9};  
    private static int[] target6 = {3,7};  
    private static int[] target7 = {4,8};  
    private static int[] target8 = {4,7};  
    private static int[] target9 = {5,8};  
    private static int[] target10 = {6,11};  
    private static int[] target11 = {7,12};  
    private static int[] target12 = {7,8,11,13};  
    private static int[] target13 = {8,12};  
    private static int[] target14 = {9,13};
    private static int[] targetdef = {};
    
    private static int[] jump0 = {3,5};  
    private static int[] jump1 = {6,8};  
    private static int[] jump2 = {7,9};  
    private static int[] jump3 = {0,5,10,12};  
    private static int[] jump4 = {11,13};  
    private static int[] jump5 = {0,3,12,14};  
    private static int[] jump6 = {1,8};  
    private static int[] jump7 = {2,9};  
    private static int[] jump8 = {1,6};  
    private static int[] jump9 = {2,7};  
    private static int[] jump10 = {3,12};  
    private static int[] jump11 = {4,13};  
    private static int[] jump12 = {3,5,10,14};  
    private static int[] jump13 = {4,11};  
    private static int[] jump14 = {5,12};  
    private static int[] jumpdef = {};
    
    public static void main(String[] args) throws IOException
    {
        //for(int asdf = 0; asdf < 20; asdf++)
        {
            int i = 0;
            boolean logic;
            long t0 = System.currentTimeMillis();
            do
            {
                logic = runBoard();
                //long t0 = System.currentTimeMillis();
                //while(System.currentTimeMillis() - t0 < 100){}
                i++;
                if(logic)
                {
                    System.out.println(i + " boards played in " + (System.currentTimeMillis() - t0) + " ms.");
                }
            }while(!logic); 
        }
    }

    private static boolean runBoard() throws IOException
    {
       initiate();
       int[] list;
       int c = 0;
       int piece;
       int[] move;
       int[][] history = new int[150][15];
       do
       {
           //displayBoard(board);
           list = createList(board);
           //displayList(list);
           piece = list[randint(list.length)];
           //System.out.println(piece);
           move = pickMove(board,piece);
           //System.out.println();
           if(move[0] != -1)
           {
               board[piece] = 0;
               board[move[0]] = 0;
               board[move[1]] = 1;
           }
           for(int i = 0; i < 15; i++) history[c][i] = board[i];
           c++;
       }while(list.length > 1 && c<150);
       
       //displayBoard(history[0]);
       displayBoard(board);
       
       if(list.length == 1)
       {
           //for(int i = 0; i < c; i++) displayBoard(history[i]);
           //System.out.println(c);
           //displayBoard(history[0]);
           logHistory(history, c);
           return true;
       }
       else return false;
    }
    
    
    
    
    private static void initiate()
    {
        for(int i = 0; i < 15; i++)
        {
            if(i == startOpen) board[i] = 0;
            else board[i] = 1;
        }
    }
    
    private static void displayBoard(int[] a)
    {
        //int[] a = bored;
        
        System.out.println("    " + a[0]);
        System.out.println("   " + a[1] + " " + a[2]);
        System.out.println("  " + a[3]+" "+a[4]+" "+a[5]);
        System.out.println(" "+a[6]+" "+a[7]+" "+a[8]+" "+a[9]);
        System.out.println(a[10]+" "+a[11]+" "+a[12]+" "+a[13]+" "+a[14]);
    }
    
    private static void displayList(int[] ls)
    {
        for(int i = 0; i < ls.length; i++)
        {
            System.out.print(ls[i] + " ");
        }
        System.out.println();
    }
    
    private static int randint(int num)
    {
        int out = (int)(Math.random() * num);
        return out;
    }
    
    private static int[] createList(int[] bored)
    {
        int listCount = 0;
        for(int i = 0; i < 15; i++)
        {
            if(bored[i] == 1) listCount++;
        }
        int[] out = new int[listCount];
        listCount = 0;
        for(int i = 0; i < 15; i++)
        {
            if(bored[i] == 1)
            {
                out[listCount] = i;
                listCount++;
            }
        }
        return out;
    }
    
    private static int[] pickMove(int[] bored, int loc)
    {
        int[] target;
        switch(loc)
        {
            case 0: target = target0; break; case 1: target = target1; break; case 2: target = target2; break; case 3: target = target3; break; case 4: target = target4; 
            break; case 5: target = target5; break; case 6: target = target6; break; case 7: target = target7; break; case 8: target = target8; break; case 9: target = target9; 
            break; case 10: target = target10; break; case 11: target = target11; break; case 12: target = target12; break; case 13: target = target13; break; case 14: target = target14; 
            break; default: target = targetdef;
        }
        
        int[] jump;
        switch(loc)
        {
            case 0: jump = jump0; break; case 1: jump = jump1; break; case 2: jump = jump2; break; case 3: jump = jump3; break; case 4: jump = jump4; 
            break; case 5: jump = jump5; break; case 6: jump = jump6; break; case 7: jump = jump7; break; case 8: jump = jump8; break; case 9: jump = jump9; 
            break; case 10: jump = jump10; break; case 11: jump = jump11; break; case 12: jump = jump12; break; case 13: jump = jump13; break; case 14: jump = jump14; 
            break; default: jump = jumpdef;
        }
        
        int[] tempJump;
        int[] tempTarget;
        int count = 0;
        for(int i = 0; i < target.length; i++)
        {
            if(  bored[target[i]] == 1   &&   bored[jump[i]] == 0   )
                count++;
        }
        tempJump = new int[count];
        tempTarget = new int[count];
        count = 0;
        for(int i = 0; i < target.length; i++)
        {
            if(  bored[target[i]] == 1   &&   bored[jump[i]] == 0   )
            {
                tempJump[count] = jump[i];
                tempTarget[count] = target[i];
                count++;
            }
        }
        int[] out = new int[2];
        if(count > 0)
        {
            int random = randint(count);
            out[0] = tempTarget[random];
            out[1] = tempJump[random];
        }
        else
        {
            out[0] = -1; 
            out[1] = -1;
        }
            
        return out;
    }
    
    private static void logHistory(int[][] h, int numMoves) throws IOException
    {
        //try
        //{
    		String fileName = "solution.txt";
            File file = new File(fileName);
            
            if (!file.exists()) {
                file.createNewFile();
            }
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            //PrintWriter out = new PrintWriter(file);
            
            
            for(int i = 0; i < numMoves; i++)
            {
                Object[] a = new Object[15];
                Object[] b = new Object[15];
                for(int j = 0; j < 15; j++)
                {
                    a[j] = h[i][j];
                    b[j] = h[i+1][j];
                }
                if(!Arrays.deepEquals(a, b))
                {
                    out.println("    " + a[0]);
                    out.println("   " + a[1] + " " + a[2]);
                    out.println("  " + a[3]+" "+a[4]+" "+a[5]);
                    out.println(" "+a[6]+" "+a[7]+" "+a[8]+" "+a[9]);
                    out.println(a[10]+" "+a[11]+" "+a[12]+" "+a[13]+" "+a[14]);
                }
            }
            
            out.close();
            
        //}
        //catch(IOException e) {  }
    }
}