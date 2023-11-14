package com.assignments;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static boolean flag = false;

	public static void main(String[] args) {
		int[] curr = new int[] { 2, 1, 3, 5, 4 };
		int[] goal = new int[] { 1, 2, 3, 4, 5 };

		flag = false;
		System.out.println("BFS Count: " + bfs(curr, goal));
		flag = false;
		System.out.println("A* Count: " + a_star(curr, goal));
	}

	private static int bfs(int[] curr, int[] g) {
		Queue<int[]> pq = new LinkedList<int[]>();
		pq.add(curr);
		int cnt = 0;

		while (!pq.isEmpty() & !flag) {
			Queue<int[]> tPQ = new LinkedList<int[]>();

			while (!pq.isEmpty() && !flag) {
				int[] c = pq.poll();

				if (f(c, g) == g.length) {
					flag = true;
					break;
				}

				for (int i = 0; i < c.length; i++) {
					int copy[] = new int[g.length];
					for (int j = 0; j <= i; j++) {
						copy[j] = c[i - j];
					}
					for (int j = i + 1; j < g.length; j++) {
						copy[j] = c[j];
					}
					tPQ.add(copy);
				}
			}
			pq = tPQ;
			++cnt;
		}

		return cnt;
	}

	private static int a_star(int[] curr, int[] g) {
		Queue<int[]> pq = new PriorityQueue<int[]>((a, b) -> (f(b, g) - f(a, g)));

		pq.add(curr);
		int cnt = 0;

		while (!pq.isEmpty() & !flag) {
			int[] c = pq.poll();

//			print(c);

			if (f(c, g) == g.length) {
				flag = true;
				break;
			}

			for (int i = 0; i < c.length; i++) {
				int copy[] = new int[g.length];
				for (int j = 0; j <= i; j++) {
					copy[j] = c[i - j];
				}
				for (int j = i + 1; j < g.length; j++) {
					copy[j] = c[j];
				}
				if (f(copy, c) == c.length)
					continue;

				pq.add(copy);
			}
			++cnt;
		}

		return cnt;
	}

	private static int f(int[] curr, int[] goal) {
		int cnt = 0;
		for (int i = 0; i < curr.length; i++) {
			if (curr[i] == goal[i])
				++cnt;
		}
		return cnt;
	}

	private static void print(int[] arr) {
		StringBuilder res = new StringBuilder();
		Arrays.stream(arr).forEach(x -> res.append(x).append(" "));

		System.out.println(res);
	}
}
