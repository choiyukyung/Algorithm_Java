import java.io.*;
import java.util.*;

public class Main {

	static int n, t;
	static boolean[][][] food;
	static int[][] believe;

	static int[][] group;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static PriorityQueue<GroupDetail> repreDetail;

	static ArrayList<Coordinate> alreadySpreaded;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		food = new boolean[n][n][3];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < n; j++) {
				char c = str.charAt(j);
				switch (c) {
				case 'T':
					food[i][j][0] = true;
					break;
				case 'C':
					food[i][j][1] = true;
					break;
				case 'M':
					food[i][j][2] = true;
					break;
				}
			}
		}

		believe = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				believe[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int day = 0; day < t; day++) {
			// 아침
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					believe[i][j]++;
				}
			}

			// 점심
			group = new int[n][n];
			int groupNum = 0;
			// 대표자 저장 겸 신앙심 전파 순서 정하기
			repreDetail = new PriorityQueue<GroupDetail>();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (group[i][j] == 0) {
						groupNum++;
						lunchBfs(groupNum, i, j);
					}
				}
			}

			// printBelieve();
			// printRepre();

			// 저녁
			alreadySpreaded = new ArrayList<>();
			for (int i = 0; i < groupNum; i++) {
				// 신앙심 전파 순서 정하기
				GroupDetail now = repreDetail.poll();
				if (!alreadySpreaded.isEmpty() && alreadySpreaded.contains(new Coordinate(now.repX, now.repY))) {
					continue; // contains 함수 다시 체크
				}
				int d = now.repB % 4;
				int wantx = now.repB - 1;
				believe[now.repX][now.repY] = 1;

				// 전파
				int nx = now.repX;
				int ny = now.repY;
				while (wantx > 0) {

					nx += dx[d];
					ny += dy[d];

					if (nx < 0 || nx >= n || ny < 0 || ny >= n)
						break;

					// 전파 안하는 케이스
					if (food[nx][ny][0] == food[now.repX][now.repY][0] && food[nx][ny][1] == food[now.repX][now.repY][1]
							&& food[nx][ny][2] == food[now.repX][now.repY][2])
						continue;

					alreadySpreaded.add(new Coordinate(nx, ny));
					int wanty = believe[nx][ny];
					if (wantx > wanty) { // 강한 전파
						food[nx][ny][0] = food[now.repX][now.repY][0];
						food[nx][ny][1] = food[now.repX][now.repY][1];
						food[nx][ny][2] = food[now.repX][now.repY][2];

						wantx -= (wanty + 1);
						believe[nx][ny]++;
					} else { // 약한 전파
						if (food[now.repX][now.repY][0])
							food[nx][ny][0] = true;
						if (food[now.repX][now.repY][1])
							food[nx][ny][1] = true;
						if (food[now.repX][now.repY][2])
							food[nx][ny][2] = true;

						believe[nx][ny] += wantx;
						wantx = 0;
						break;
					}
				}

			}

			// 하루 끝
			int[] sum = new int[7];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int val = believe[i][j];
					boolean mint = food[i][j][0];
					boolean choco = food[i][j][1];
					boolean milk = food[i][j][2];

					if (mint && choco && milk) {
						sum[0] += val; // 민트초코우유
					} else if (mint && choco) {
						sum[1] += val; // 민트초코
					} else if (mint && milk) {
						sum[2] += val; // 민트우유
					} else if (choco && milk) {
						sum[3] += val; // 초코우유
					} else if (mint) {
						sum[6] += val; // 민트
					} else if (choco) {
						sum[5] += val; // 초코
					} else if (milk) {
						sum[4] += val; // 우유
					}
				}
			}

			for (int k = 0; k < 7; k++) {
				System.out.print(sum[k] + " ");
			}
			System.out.println();

		}

	}

	static void printBelieve() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(believe[i][j] + " ");
			}
			System.out.println();
		}

	}

	static void printRepre() {
		while (!repreDetail.isEmpty()) {
			GroupDetail g = repreDetail.poll();
			System.out.println(g.groupNum + " " + g.foodCnt + " " + g.repB);
		}
	}

	static void lunchBfs(int groupNum, int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x, y });
		boolean T = food[x][y][0];
		boolean C = food[x][y][1];
		boolean M = food[x][y][2];
		group[x][y] = groupNum;

		// 그룹 멤버
		ArrayList<int[]> members = new ArrayList<>();
		members.add(new int[] { x, y });

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n || group[nx][ny] != 0)
					continue;

				if (T == food[nx][ny][0] && C == food[nx][ny][1] && M == food[nx][ny][2]) {
					group[nx][ny] = groupNum;
					queue.add(new int[] { nx, ny });
					members.add(new int[] { nx, ny });
				}

			}
		}

		// 대표자 선정
		int repX = members.get(0)[0], repY = members.get(0)[1];
		for (int[] member : members) {
			int mx = member[0], my = member[1];
			if (believe[repX][repY] < believe[mx][my]) {
				repX = mx;
				repY = my;
			} else if (believe[repX][repY] == believe[mx][my]) {
				if (repX > mx) {
					repX = mx;
					repY = my;
				} else if (repX == mx && repY > my) {
					repX = mx;
					repY = my;
				}
			}
		}

		// 신앙심 대표자에게 전달
		for (int[] member : members) {
			believe[member[0]][member[1]]--;
			believe[repX][repY]++;
		}

		repreDetail.add(new GroupDetail(groupNum, believe[repX][repY], food[x][y], repX, repY));

	}

	static class GroupDetail implements Comparable<GroupDetail> {
		int groupNum;
		int repB;
		boolean[] repF;
		int foodCnt;
		int repX, repY;

		public GroupDetail(int groupNum, int repB, boolean[] repF, int repX, int repY) {
			this.groupNum = groupNum;
			this.repB = repB;
			this.repF = repF.clone();

			this.foodCnt = 0;
			for (boolean i : repF) {
				if (i) {
					this.foodCnt++;
				}
			}

			this.repX = repX;
			this.repY = repY;
		}

		@Override
		public int compareTo(GroupDetail o) {
			if (this.foodCnt != o.foodCnt)
				return this.foodCnt - o.foodCnt;
			if (this.repB != o.repB)
				return (-1) * (this.repB - o.repB);
			if (this.repX != o.repX)
				return this.repX - o.repX;
			if (this.repY != o.repY)
				return this.repY - o.repY;
			return 0;
		}

	}

	static class Coordinate {
		int x, y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Coordinate))
				return false;
			Coordinate c = (Coordinate) o;
			return this.x == c.x && this.y == c.y;
		}

	}

}

