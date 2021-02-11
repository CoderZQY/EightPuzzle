package 人工智能;

public class EightPuzzleMove {
    public static boolean isMovable(int x,int y,char dir){
        return (dir != 'U' || x != 0) && (dir != 'D' || x != 2)
                && (dir != 'R' || y != 2) && (dir != 'L' || y != 0);
    }
    public static EightPuzzle move(EightPuzzle ep, char dir) {
        EightPuzzle new_ep = ep.depthClone();
        int[] pos = ep.getBlankPostion();
        int blankPos_x = pos[0];
        int blankPos_y = pos[1];

        //指令为向上移动
        if (dir == 'U') {
            int temp = new_ep.data[blankPos_x][blankPos_y];
            new_ep.data[blankPos_x][blankPos_y] = new_ep.data[blankPos_x - 1][blankPos_y];
            new_ep.data[blankPos_x - 1][blankPos_y] = temp;
            //表示移动成功
        }
        //指令为向下移动
        else if (dir == 'D') {
            int temp = new_ep.data[blankPos_x][blankPos_y];
            new_ep.data[blankPos_x][blankPos_y] = new_ep.data[blankPos_x + 1][blankPos_y];
            new_ep.data[blankPos_x + 1][blankPos_y] = temp;
            //表示移动成功
        }
        //指令为向右移动
        else if (dir == 'R') {
            int temp = new_ep.data[blankPos_x][blankPos_y];
            new_ep.data[blankPos_x][blankPos_y] = new_ep.data[blankPos_x][blankPos_y + 1];
            new_ep.data[blankPos_x][blankPos_y + 1] = temp;
            //表示移动成功

        }
        //指令为向左移动
        else if (dir == 'L') {
            int temp = new_ep.data[blankPos_x][blankPos_y];
            new_ep.data[blankPos_x][blankPos_y] = new_ep.data[blankPos_x][blankPos_y - 1];
            new_ep.data[blankPos_x][blankPos_y - 1] = temp;
            //表示移动成功
        }
        //指令输入错误
        else {
            return null;
        }
        return new_ep;
    }

}
