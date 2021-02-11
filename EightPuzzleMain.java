package 人工智能;

import java.util.Scanner;

public class EightPuzzleMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /*//手动输入起始状态
        int[][] init_arr = new int[3][3];
        System.out.println("请输入起始状态(9位数),其中0表示空格：");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                init_arr[i][j] = input.nextInt();
            }
        }
        //输入最终状态
        int[][] target_arr = new int[3][3];
        System.out.println("请输入目标状态(9位数),其中0表示空格：");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                target_arr[i][j] = input.ne xtInt();
            }
        }*/
        int[][] init_arr = new int[][]{//固定输入状态
                {2,3,8},{6,1,4},{0,7,5}
        };
        /*int[]a = new int[9];//随机生成九宫格
        do {
            int k = 0;
            Random random=new Random();
            Set set=new HashSet();
            while(set.size() < 8) {
                int n=random.nextInt(8)+1;
                if(!set.contains(n)) {
                    set.add(n);
                    a[k++] = n;
                }
            }
            a[k] = 0;
        }while(!isSolvable(a));
        int[][] init_arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                init_arr[i][j] = a[i*3+j];
                System.out.print(init_arr[i][j]);
            }
            System.out.println();
        }*/
        int[][] target_arr = new int[][]{
                {1,2,3},{8,0,4},{7,6,5}
        };
        int[] test = new int[9];//用于可行性判断
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                test[index++] = init_arr[i][j];
            }
        }
        if(isSolvable(test)){
            EightPuzzle init_ep = new EightPuzzle(init_arr);
            EightPuzzle target_ep = new EightPuzzle(target_arr);
            EightPuzzleAlgorithm epa = new EightPuzzleAlgorithm(init_ep,target_ep);
            epa.bfs();
            epa.boundedDfs();
            epa.A_Star();
        }else {
            System.out.println("八数码无解！！！");
        }
    }
    public static boolean isSolvable(int []a) {//八数码可行性判断
        int reverse=0;
        for(int i=0; i < 9; i++)
            for(int j=i+1; j < 9; j++)
                if(a[j] != 0 && a[i] != 0 && a[i] > a[j])
                    reverse++;
        if(reverse % 2 == 1) {
            return true;
        }
        return false;
    }
}
