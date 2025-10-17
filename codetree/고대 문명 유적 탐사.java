import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K, M;
	static final int S = 5, R = 3;
	static int[][] map;
	static boolean[][] visited;

	static ArrayList<Integer> wall;
	static int wallIdx;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[S][S];
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < S; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		wall = new ArrayList<>();
		wallIdx = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			wall.add(Integer.parseInt(st.nextToken()));
		}

		// input end

		for (int k = 0; k < K; k++) { // [3] 탐사 반복

			// [1] 탐사 진행
			// 회전 중심좌표 지정
			ArrayList<EachRotate> candidate = new ArrayList<>();
			for (int i = 1; i < S - 1; i++) {
				for (int j = 1; j < S - 1; j++) {
					for (int rt = 1; rt <= 3; rt++) {
						// 90*rt 회전
						int[][] rotate_map = rotate(i, j, rt);

						// 회전맵 유적 개수
						int yumuls = countYumul(rotate_map);
						candidate.add(new EachRotate(yumuls, rt, i, j));

					}
				}
			}
			Collections.sort(candidate);
			EachRotate bestRotate = candidate.get(0);

			if (bestRotate.yumulCnt == 0)
				return;

			// 실제 회전
			map = rotate(bestRotate.r, bestRotate.c, bestRotate.rotateCnt);

			// [2] 유물 획득
			int answer = 0;
			while (true) {// 유물 연쇄 획득

				// 유물 세고 지도 비우기
				int yumuls = countYumul(map);

				if (yumuls == 0)
					break;

				answer += yumuls;

				// 벽면 숫자로 지도 채우기
				for (int j = 0; j < S; j++) {
					for (int i = S - 1; i >= 0; i--) {
						if (map[i][j] == 0) {
							map[i][j] = wall.get(wallIdx);
							wallIdx++;
						}
					}
				}
			}

			// printMap(map);

			if (answer > 0)
				System.out.print(answer + " ");
		}

	}

	static class EachRotate implements Comparable<EachRotate> {
		int yumulCnt, rotateCnt, r, c;

		public EachRotate(int yumulCnt, int rotateCnt, int r, int c) {
			this.yumulCnt = yumulCnt;
			this.rotateCnt = rotateCnt;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(EachRotate o) {
			if (this.yumulCnt != o.yumulCnt)
				return (-1) * (this.yumulCnt - o.yumulCnt);
			if (this.rotateCnt != o.rotateCnt)
				return this.rotateCnt - o.rotateCnt;
			if (this.c != o.c)
				return this.c - o.c;
			return this.r - o.r;
		}
	}

	private static int countYumul(int[][] map_now) {

		visited = new boolean[S][S];
		int count = 0;
		for (int i = 0; i < S; i++) {
			for (int j = 0; j < S; j++) {
				if (!visited[i][j]) {
					count += groupYumul(i, j, map_now);
				}
			}
		}
		return count;

	}

	private static int groupYumul(int sx, int sy, int[][] map_now) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { sx, sy });
		visited[sx][sy] = true;
		int yumulType = map_now[sx][sy];
		int count = 1;
		ArrayList<int[]> members = new ArrayList<>();
		members.add(new int[] { sx, sy });

		while (!q.isEmpty()) {
			int[] now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if (nx < 0 || nx >= S || ny < 0 || ny >= S || visited[nx][ny])
					continue;

				if (map_now[nx][ny] == yumulType) {
					q.add(new int[] { nx, ny });
					visited[nx][ny] = true;
					count++;
					members.add(new int[] { nx, ny });
				}

			}

		}

		if (count >= 3) {
			for (int[] m : members) {
				map_now[m[0]][m[1]] = 0;
			}
			return count;
		}

		return 0;

	}

	private static void printMap(int[][] map_now) {
		for (int i = 0; i < S; i++) {
			for (int j = 0; j < S; j++) {
				System.out.print(map_now[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int[][] rotate(int x, int y, int rotateTime) {
		int[][] map_copy = new int[S][S];
		copyMap(map, map_copy);

		for (int rt = 0; rt < rotateTime; rt++) {
			map_copy = rotateOne(x, y, map_copy);
		}

		return map_copy;
	}

	private static int[][] rotateOne(int x, int y, int[][] map_now) {
		int[][] map_copy = new int[S][S];
		copyMap(map_now, map_copy);

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < R; j++) {
				map_copy[x - 1 + j][y - 1 + 2 - i] = map_now[x - 1 + i][y - 1 + j];
			}
		}

		return map_copy;
	}

	private static void copyMap(int[][] map_original, int[][] map_copy) {
		for (int i = 0; i < S; i++) {
			map_copy[i] = map_original[i].clone();
		}
	}

}

