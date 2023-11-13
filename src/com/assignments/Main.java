package com.assignments;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		int[] curr = new int[] { 2, 1, 3, 5, 4 };
		int[] goal = new int[] { 5, 3, 1, 2, 4 };

		System.out.println("BFS Count: " + bfs(curr, goal));
		System.out.println("A* Count: " + a_star(curr, goal));
	}

	private static int bfs(int[] curr, int[] g) {
		Queue<int[]> pq = new PriorityQueue<int[]>((a, b) -> (f(b, g) - f(a, g)));
		pq.add(curr);
		int cnt = 0;

		while (!pq.isEmpty()) {
			int[] c = pq.poll();

			print(c);

			if (f(c, g) == g.length)
				break;

			Queue<int[]> tPQ = new PriorityQueue<int[]>((a, b) -> (f(b, g) - f(a, g)));

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

			pq.clear();
			pq.add(tPQ.poll());

			tPQ.clear();
			++cnt;
		}

		return cnt;
	}

	private static int a_star(int[] curr, int[] g) {
		Queue<int[]> pq = new PriorityQueue<int[]>((a, b) -> (f(b, g) + h(b, g) - f(a, g) - h(a, g)));

		pq.add(curr);
		int cnt = 0;

		while (!pq.isEmpty()) {
			int[] c = pq.poll();

			print(c);

			if (f(c, g) == g.length)
				break;

			for (int i = 0; i < c.length; i++) {
				int copy[] = new int[g.length];
				for (int j = 0; j <= i; j++) {
					copy[j] = c[i - j];
				}
				for (int j = i + 1; j < g.length; j++) {
					copy[j] = c[j];
				}
				if (f(copy, g) == f(c, g)) continue;
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

	private static int h(int[] curr, int[] goal) {
		int cnt = 0;
		for (int i = 0; i < curr.length; i++) {
			if (curr[i] != goal[i])
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
