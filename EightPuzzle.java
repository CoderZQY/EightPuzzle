package 人工智能;

import java.util.Arrays;

public class EightPuzzle {
    public int[][] data;
    private int depth;//为深度优先搜索确定界限
    private int evaluation;//估计函数f(n)：从起始状态到目标的最小估计值
    private int misposition;//启发函数 h(n)：到目标的最小估计(记录和目标状态有多少个数不同)

    public EightPuzzle() {
        data = new int[3][3];
    }

    public EightPuzzle(int[][] data) {
        this.data = data;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evalution) {
        this.evaluation = evalution;
    }

    public int getMisposition() {
        return misposition;
    }

    public void setMisposition(int misposition) {
        this.misposition = misposition;
    }

    public boolean isSame(EightPuzzle ep) {
        return Arrays.deepEquals(this.data, ep.data);
    }

    public void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(data[i][j] + " ");
            }
            //System.out.println();
        }
        System.out.println();
    }

    // 获取空格的坐标
    public int[] getBlankPostion() {
        int[] position = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.data[i][j] == 0) {
                    position[0] = i;
                    position[1] = j;
                    break;
                }
            }
        }
        return position;
    }
    //浅拷贝
    @Override
    protected EightPuzzle clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return new EightPuzzle(Arrays.copyOf(this.data, this.data.length));
    }
    //深拷贝
    public EightPuzzle depthClone() {
        EightPuzzle new_ep = new EightPuzzle();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                new_ep.data[i][j] = this.data[i][j];
        return new_ep;
    }

    @Override
    public boolean equals(Object obj) {
        return this.isSame((EightPuzzle)obj);
    }
}