import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int n, m, k, c;
	static int[][] map;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][] killLast;

	static int[] diagox = { -1, -1, 1, 1 };
	static int[] diagoy = { -1, 1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		c++;

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// input end

		killLast = new int[n][n];
		int answer = 0;

		for (int year = 0; year < m; year++) {

			// 0. 제초제 차감
			minusKillLast();

			// 1. 나무 성장
			growTree();

			// 2. 나무 번식(제초제가 남은 공간에는 x)
			spreadTree();

			// 3. 제초제 뿌리기

			// 모든 칸에 제초제 뿌리는 테스트
			int[][] killCountMap = new int[n][n];
			int killMax = 0;
			int[] kill = { 0, 0 };
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					killCountMap[i][j] = killTreeFromHere(i, j, false);
					if (killCountMap[i][j] > killMax) {
						killMax = killCountMap[i][j];
						kill = new int[] { i, j };
					}
				}
			}

			// 실제 제초제 뿌리기
			answer += killTreeFromHere(kill[0], kill[1], true);
		}
		System.out.println(answer);

	}

	private static void minusKillLast() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (killLast[i][j] > 0)
					killLast[i][j]--;
			}
		}

	}

	private static int killTreeFromHere(int sx, int sy, boolean kill) {

		if (map[sx][sy] < 0)
			return 0;
		if (map[sx][sy] == 0) {
			if (kill)
				killLast[sx][sy] = c;
			return 0;
		}

		int killCount = 0;

		killCount += map[sx][sy];
		if (kill) {
			map[sx][sy] = 0;
			killLast[sx][sy] = c;
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= k; j++) {
				int nx = sx + diagox[i] * j;
				int ny = sy + diagoy[i] * j;

				if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] < 0)
					break;

				if (map[nx][ny] == 0) {
					if (kill) {
						map[nx][ny] = 0;
						killLast[nx][ny] = c;
					}
					break;
				}

				killCount += map[nx][ny];

				if (kill) {
					map[nx][ny] = 0;
					killLast[nx][ny] = c;
				}

			}
		}

		return killCount;

	}

	private static void printMap(int[][] map_print) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map_print[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void spreadTree() {
		ArrayList<String> treeList = saveTree();
		for (String tree : treeList) {
			int[] now = makeCoorIntArr(tree);
			boolean[] canSpread = new boolean[4];
			int count = 0;

			// 퍼트릴 수 있는 곳 찾기
			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n || treeList.contains(makeCoorString(nx, ny))
						|| map[nx][ny] < 0 || killLast[nx][ny] > 0)
					continue;

				canSpread[i] = true;
				count++;
			}

			// 퍼트리기
			if (count == 0)
				continue;

			int spreadCnt = map[now[0]][now[1]] / count;
			for (int i = 0; i < 4; i++) {
				if (canSpread[i]) {
					int nx = now[0] + dx[i];
					int ny = now[1] + dy[i];

					map[nx][ny] += spreadCnt;
				}

			}
		}
	}

	static String makeCoorString(int x, int y) {
		return x + "," + y;
	}

	static int[] makeCoorIntArr(String str) {
		String[] arr = str.split(",");
		return new int[] { Integer.parseInt(arr[0]), Integer.parseInt(arr[1]) };
	}

	private static ArrayList<String> saveTree() {
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 0)
					list.add(makeCoorString(i, j));
			}
		}
		return list;
	}

	private static void growTree() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > 0) {
					int count = 0;

					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];

						if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] <= 0)
							continue;

						count++;
					}

					map[i][j] += count;
				}
			}
		}
	}

}

