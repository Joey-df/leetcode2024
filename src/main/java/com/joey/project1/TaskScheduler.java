package com.joey.project1;

import java.util.*;

//有多个任务要完成，每个任务有三个属性，优先级、到达时间、完成所需时间。你同时只能处理一项任务，求每项任务被完成的时间。当任务出现冲突时，遵循：
//1、先处理优先级高的（优先级数值越小，优先级越高）
//2、优先级相同，优先处理更早到达的
//3、优先级相同且同时到达，先处理任务id更小的（更早出现在输入流里的）
//任务处理是抢占式的，当完成所需时间为10s的任务持续了3s后被中断，下次再运行只需要7s。
//示例：
//输入：[[0,15,5],[3,0,10],[0,5,10],[0,5,5]]
//输出：[25,30,15,20]

//20240619第三题
public class TaskScheduler {
    static class Task {
        int id;
        int priority;
        int arriveTime;
        int duration;

        public Task(int id, int priority, int arriveTime, int duration) {
            this.id = id;
            this.priority = priority;
            this.arriveTime = arriveTime;
            this.duration = duration;
        }
    }

    public static int[] taskCompletionTime(int[][] tasks) {
        int n = tasks.length;
        int[] completionTimes = new int[n];

        // 将任务存储为对象，并按到达时间排序
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            taskList.add(new Task(i, tasks[i][0], tasks[i][1], tasks[i][2]));
        }
        taskList.sort(Comparator.comparingInt(t -> t.arriveTime));

        // 优先队列，用于存储可执行的任务
        PriorityQueue<Task> pq = new PriorityQueue<>((t1, t2) -> {
            if (t1.priority != t2.priority) {
                return Integer.compare(t1.priority, t2.priority); // 优先级小的优先
            }
            if (t1.arriveTime != t2.arriveTime) {
                return Integer.compare(t1.arriveTime, t2.arriveTime); // 到达时间早的优先
            }
            return Integer.compare(t1.id, t2.id); // ID 小的优先
        });

        int currentTime = 0;
        int i = 0; // 遍历任务列表的指针

        while (i < n || !pq.isEmpty()) {
            // 将所有到达时间 <= currentTime 的任务加入优先队列
            while (i < n && taskList.get(i).arriveTime <= currentTime) {
                pq.offer(taskList.get(i));
                i++;
            }

            if (pq.isEmpty()) {
                // 如果队列为空，跳到下一个任务的到达时间
                currentTime = taskList.get(i).arriveTime;
                continue;
            }

            // 从优先队列中取出最高优先级的任务
            Task task = pq.poll();

            // 下一个任务到达的时间
            int nextArrivalTime = (i < n) ? taskList.get(i).arriveTime : Integer.MAX_VALUE;
            int timeToProcess = Math.min(task.duration, nextArrivalTime - currentTime);

            // 模拟执行任务
            currentTime += timeToProcess;
            task.duration -= timeToProcess;

            if (task.duration == 0) {
                // 任务完成
                completionTimes[task.id] = currentTime;
            } else {
                // 任务未完成，重新加入队列
                pq.offer(task);
            }
        }

        return completionTimes;
    }


    public static int[] taskCompletionTime2(int[][] tasks) {
        int n = tasks.length;
        List<Task> taskList = new ArrayList<>();
        int minArriveTime = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int[] task = tasks[i];
            minArriveTime = Math.min(minArriveTime, task[1]);
            taskList.add(new Task(i, task[0], task[1], task[2]));
        }
        taskList.sort(Comparator.comparingInt(a -> a.arriveTime));
        PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> {
            if (a.priority != b.priority) return Integer.compare(a.priority, b.priority);
            if (a.arriveTime != b.arriveTime) return Integer.compare(a.arriveTime, b.arriveTime);
            return a.id - b.id;
        });
        int[] ans = new int[n];
        int currentTime = minArriveTime;
        int i = 0;
        while (i < n || !pq.isEmpty()) {
            //System.out.println(currentTime);
            //把当前时间可以解锁的任务全部加入小根堆
            while (i < n && taskList.get(i).arriveTime <= currentTime) {
                pq.offer(taskList.get(i));
                i++;
            }
            //i来到第一个next arriveTime的位置
            int nextArriveTime = i < n ? taskList.get(i).arriveTime : Integer.MAX_VALUE;
            int restTime = nextArriveTime - currentTime; //可以处理任务的时间
            //能处理多少就处理多少，把restTime耗尽
            while (!pq.isEmpty() && restTime > 0) {
                Task node = pq.poll();
                if (node.duration <= restTime) {
                    //可以处理完
                    currentTime += node.duration;
                    ans[node.id] = currentTime;
                    restTime -= node.duration;
                    node.duration = 0;
                } else {
                    //处理不完一整个任务，处理多少就处理多少，剩余放回
                    node.duration -= restTime;
                    currentTime += restTime;
                    restTime = 0; //当前时间到达 next arriveTime
                    pq.offer(node);
                }
            }
            //while出来两种情况：
            //1.队列不空，但是restTime耗尽了，此时currentTime刚刚来到nextArriveTime
            //2.restTime没耗尽，但是队列空了，此时currentTime < nextArriveTime，需要将currentTime跳到nextArriveTime时刻
            if (pq.isEmpty()) {
                // 如果队列为空，跳到下一个任务的到达时间
                currentTime = nextArriveTime;
            }
        }
        return ans;
    }

    public static int[][] tasks(int maxN, int maxArrive, int maxDuration) {
        int n = (int) (Math.random() * maxN) + 1;
        int[][] tasks=new int[n][3];
        for (int i = 0; i < n; i++) {
            int priority=(int) (Math.random() * 16);
            int arrive = (int) (Math.random() * maxArrive) + 1;
            int duration = (int) (Math.random() * maxDuration) + 10;
            tasks[i]=new int[]{priority, arrive, duration};
        }
        return tasks;
    }

    public static boolean same(int[] arr1, int[] arr2) {
        return Arrays.toString(arr1)
                .equals(Arrays.toString(arr2));
    }

    public static void main(String[] args) {
//        int[][] tasks = {
//                {0, 15, 5},
//                {3, 0, 10},
//                {0, 5, 10},
//                {0, 5, 5}
//        };
//
//        int[] result = taskCompletionTime(tasks);
//        int[] result2 = taskCompletionTime2(tasks);
//        System.out.println(Arrays.toString(result)); // 输出：[25, 30, 15, 20]
//        System.out.println(Arrays.toString(result2)); // 输出：[25, 30, 15, 20]
        int maxN=10000;
        int maxArrive=10000;
        int maxDuration=1000;
        int testTimes=5000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[][] tasks = tasks(maxN, maxArrive, maxDuration);
            int[] ans1 = taskCompletionTime(tasks);
            int[] ans2 = taskCompletionTime2(tasks);
            if (!same(ans1, ans2)) {
                System.out.println("出错了");
                break;
            } else {
//                System.out.println(Arrays.toString(ans1));
//                System.out.println(Arrays.toString(ans2));
//                System.out.println("==========");
            }
        }
        System.out.println("测试结束");
    }
}

