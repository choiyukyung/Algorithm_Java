import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n, q;
	static int[][] map;
	static int[] area;
	static boolean[][] visited;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		area = new int[q + 1];

		for (int microNum = 1; microNum <= q; microNum++) {

			// 1. 미생물 투입
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());

			Set<Integer> deleteCandidate = insertMicro(microNum, sx, sy, ex, ey);

			// 2. 새 배양 용기로 옮기지 않을 micro 찾기
			ArrayList<Integer> deletedMicro = removeMicro(deleteCandidate);

			// 3. 배양 용기로 옮기기
			// 순서를 정하기 위해, 넓이 배열을 넓이 arraylist로 옮기고 정렬
			moveMicro(deletedMicro);
			// printMap(map);

			// 4. 실험 결과 구하기
			System.out.println(calculateResult());
		}

	}

	private static int calculateResult() {

		ArrayList<String> close = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 0)
					continue;
				for (int d = 2; d < 4; d++) { // 아래, 오른쪽 확인
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == 0 || map[nx][ny] == map[i][j])
						continue;

					int[] closeTwo = { map[i][j], map[nx][ny] };
					Arrays.sort(closeTwo);
					String str = closeTwo[0] + " " + closeTwo[1];
					if (close.isEmpty() || !close.contains(str))
						close.add(str);

				}
			}
		}

		int result = 0;
		for (int i = 0; i < close.size(); i++) {
			String[] closeMicros = close.get(i).split(" ");
			int m1 = Integer.parseInt(closeMicros[0]);
			int m2 = Integer.parseInt(closeMicros[1]);

			result += area[m1] * area[m2];
		}

		return result;

	}

	private static void moveMicro(ArrayList<Integer> deletedMicro) {
		ArrayList<int[]> arealist = new ArrayList<int[]>();
		for (int i = 1; i <= q; i++) {
			if (area[i] > 0 && !deletedMicro.contains(i))
				arealist.add(new int[] { area[i], i });
		}
		arealist.sort((a, b) -> {
			if (a[0] != b[0])
				return (-1) * (a[0] - b[0]);
			return a[1] - b[1];
		});

		// 새 배양 용기로 옮기기
		int[][] newMap = new int[n][n];
		for (int[] m : arealist) {
			int micro = m[1];

			// 좌표 모두 저장하기
			int minX = Integer.MAX_VALUE;
			int minY = Integer.MAX_VALUE;
			ArrayDeque<int[]> microList = new ArrayDeque<int[]>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == micro) {
						microList.add(new int[] { i, j });

						minX = Math.min(minX, i);
						minY = Math.min(minY, j);
					}
				}
			}

			// 좌표를 (0,0)을 시작으로 바꾸기
			for (int j = 0; j < microList.size(); j++) {
				int[] now = microList.poll();
				now[0] -= minX;
				now[1] -= minY;
				microList.add(now);
			}

			boolean canbreak = false;
			for (int startX = 0; startX < n; startX++) {
				for (int startY = 0; startY < n; startY++) {
					if (canmove(newMap, microList, startX, startY)) {
						realmove(newMap, microList, startX, startY, micro);
						canbreak = true;
						break;
					}
				}
				if (canbreak)
					break;

			}

		}

		// 배양용기 바꾸기
		for (int i = 0; i < n; i++) {
			map[i] = newMap[i].clone();
		}

	}

	private static void realmove(int[][] newMap, ArrayDeque<int[]> microList, int startX, int startY, int micro) {
		for (int[] coor : microList) {
			int x = coor[0] + startX;
			int y = coor[1] + startY;
			newMap[x][y] = micro;
		}

	}

	private static boolean canmove(int[][] newMap, ArrayDeque<int[]> microList, int startX, int startY) {
		for (int[] coor : microList) {
			int x = coor[0] + startX;
			int y = coor[1] + startY;
			if (x < 0 || y < 0 || x >= n || y >= n || newMap[x][y] != 0) {
				return false;
			}
		}
		return true;
	}

	private static ArrayList<Integer> removeMicro(Set<Integer> deleteCandidate) {
		ArrayList<Integer> deletedMicro = new ArrayList<Integer>();

		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j] && deleteCandidate.contains(map[i][j])) {
					int count = findArea(i, j);
					// 새 배양 용기로 옮기지 않을 micro
					if (count != area[map[i][j]])
						deletedMicro.add(map[i][j]);
				}
			}
		}

		return deletedMicro;
	}

	private static Set<Integer> insertMicro(int microNum, int sx, int sy, int ex, int ey) {
		area[microNum] = (ex - sx) * (ey - sy);
		Set<Integer> deleteCandidate = new HashSet<Integer>();

		for (int x = sx; x < ex; x++) {
			for (int y = sy; y < ey; y++) {
				if (map[x][y] != 0) {
					area[map[x][y]]--;
					deleteCandidate.add(map[x][y]);
				}
				map[x][y] = microNum;
			}
		}

		return deleteCandidate;

	}

	private static int findArea(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		visited[x][y] = true;
		int kind = map[x][y];
		int count = 1;

		while (!q.isEmpty()) {
			int[] now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny] || map[nx][ny] != kind)
					continue;

				q.add(new int[] { nx, ny });
				visited[nx][ny] = true;
				count++;

			}
		}

		return count;

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

}

