package 人工智能;

import java.util.*;

public class EightPuzzleAlgorithm {
    final static String direction = "LURD";
    private EightPuzzle ep,tar_ep;
    private Stack<EightPuzzle> ep_stack;//用于深度优先
    private Queue<EightPuzzle> ep_queue;//用于宽度优先
    private ArrayList<EightPuzzle> ep_array;//用于启发式搜索
    private LinkedList<EightPuzzle> searched_list;//CLOSED表
    private int depth;
    private int[] pre; //记录当前节点的父节点指针
    private char[] dirs; //记录方向
    private StringBuffer rs;
    private int id;
    private int index;
    long startTime;
    long endTime;
    public EightPuzzleAlgorithm(EightPuzzle init_ep,EightPuzzle target_ep){
        ep = init_ep;
        tar_ep = target_ep;
        init(ep);
    }
    public void init(EightPuzzle ep){//初始化状态信息
        depth = 0;
        id = 0;
        index = -1;
        rs = new StringBuffer();
        pre = new int[100010];
        dirs = new char[100010];
        ep_stack = new Stack<>();
        ep_queue = new LinkedList<>();
        ep_array = new ArrayList<>();
        searched_list = new LinkedList<>();
        ep.setDepth(depth);
        //设置栈底元素
        ep_stack.push(ep);
        //设置队首元素
        ep_queue.offer(ep);
        //设置数组首元素
        ep_array.add(ep);
        setEvaluation(ep,tar_ep);
    }
    public void setEvaluation(EightPuzzle ep,EightPuzzle tar_ep){
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(ep.data[i][j]!=tar_ep.data[i][j]){
                    count++;
                }
            }
        }
        ep.setMisposition(count);//设置当前节点与目标节点差异的度量
        ep.setEvaluation(ep.getDepth()+ep.getMisposition());//返回当前状态的估计值
    }
    //有界深度优先搜索
    public void boundedDfs(){
        System.out.println("<--深度优先搜索路径方法-->");
        init(ep);
        startTime=System.currentTimeMillis();
        if(dfs_find(ep_stack.peek())){
            return;
        }
        while(!ep_stack.isEmpty()){
            EightPuzzle ep_move = ep_stack.pop();
            depth = ep_move.getDepth();
            int[] pos = ep_move.getBlankPostion();
            int x = pos[0];
            int y = pos[1];
            //ep_move.print();
            searched_list.add(ep_move);
            if(depth < 30){
                depth++;
                for (int i = 0; i < direction.length(); i++) {
                    char ch = direction.charAt(i);
                    if(EightPuzzleMove.isMovable(x, y, ch)){
                        EightPuzzle new_ep = EightPuzzleMove.move(ep_move, ch);
                        new_ep.setDepth(depth);
                        if (!searched_list.contains(new_ep)){
                            ep_stack.push(new_ep);
                            dirs[id] = ch;
                            if(dfs_find(new_ep)){
                                return;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("没有搜索目标解!");
    }
    //宽度优先搜索
    public void bfs(){
        System.out.println("<--宽度优先搜索路径方法-->");
        init(ep);
        startTime = System.currentTimeMillis();
        pre[id] = -1;
        id++;
        if(bfs_find(ep_queue.peek())){
            return;
        }
        while (!ep_queue.isEmpty()){
            EightPuzzle ep_move = ep_queue.poll();
            int[] pos = ep_move.getBlankPostion();
            int x = pos[0];
            int y = pos[1];
            //ep_move.print();
            searched_list.add(ep_move);
            index++;
            for (int i = 0; i < direction.length(); i++) {
                char ch = direction.charAt(i);
                if(EightPuzzleMove.isMovable(x, y, ch)){
                    EightPuzzle new_ep = EightPuzzleMove.move(ep_move, ch);
                    if (!searched_list.contains(new_ep)) {
                        ep_queue.offer(new_ep);
                        pre[id] = index;
                        dirs[id] = ch;
                        id++;
                        if(bfs_find(new_ep)){
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("没有搜索到目标解!");
    }
    public void A_Star(){
        System.out.println("<--启发式搜索路径方法-->");
        init(ep);
        startTime = System.currentTimeMillis();
        if(dfs_find(ep_array.get(0))){
            return;
        }
        while (!ep_array.isEmpty()){
            //EightPuzzle ep_move = ep_array.remove(0);
            EightPuzzle ep_move = Collections.min(ep_array,Comparator.comparingInt(EightPuzzle::getEvaluation));
            ep_array.remove(ep_move);
            int[] pos = ep_move.getBlankPostion();
            int x = pos[0];
            int y = pos[1];
            depth = ep_move.getDepth();
            depth++;
            ep_move.print();
            searched_list.add(ep_move);
            for (int i = 0; i < direction.length(); i++) {
                char ch = direction.charAt(i);
                if(EightPuzzleMove.isMovable(x, y, ch)){
                    EightPuzzle new_ep = EightPuzzleMove.move(ep_move, ch);
                    if (!searched_list.contains(new_ep)) {
                        setEvaluation(new_ep,tar_ep);
                        ep_array.add(new_ep);
                        if(dfs_find(new_ep)){
                            return;
                        }
                    }
                }
            }
            //Collections.sort(ep_array, Comparator.comparingInt(EightPuzzle::getEvaluation));
        }
        System.out.println("没有搜索到目标解!");
    }
    public boolean dfs_find(EightPuzzle ep){
        if(ep.isSame(tar_ep)){
            endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("找到目标解!");
            System.out.println("OPEN表大小为："+ep_stack.size());
            System.out.println("CLOSED表大小为："+searched_list.size());
            System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
            return true;
        }else{
            return false;
        }
    }
    public boolean bfs_find(EightPuzzle ep){
        if(ep.isSame(tar_ep)){
            endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("找到目标解!");
            System.out.println("OPEN表大小为："+ep_queue.size());
            System.out.println("CLOSED表大小为："+searched_list.size());
            System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
            System.out.print("走法为：");
            for(int i = id - 1; i != 0; i = pre[i]){//查找祖先节点
                rs.append(dirs[i]);
                if(pre[i]!=0){
                    rs.append(" ");
                }
            }
            System.out.println(rs.reverse().toString());
            return true;
        }else {
            return false;
        }
    }
}
