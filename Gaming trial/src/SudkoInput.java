
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;;

public class SudkoInput {
	public static String readFileAsString(String fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}

	static int matrix[][] = new int[9][9];

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new File("C:\\Users\\kumarmur\\Desktop\\input.txt"));
		ArrayList<String> ar = new ArrayList<String>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			ar.add(line);
		}
		parse(ar);

	}

	private static void parse(ArrayList<String> ar) throws IOException {
		for (int i = 0; i < ar.size(); i++) {
			String ans = ar.get(i);
			String bc = ans.replaceAll("[-a-zA-Z]", "").trim();
			if (bc.matches("[0-9]*")) {
				if (bc.length() > 2)

				{
					int x = Integer.parseInt(Character.toString(bc.charAt(0)));
					int y = Integer.parseInt(Character.toString(bc.charAt(1)));
					int val = Integer.parseInt(Character.toString(bc.charAt(2)));
					matrix[x][y] = val;
				}
			}

		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
		if (solveSuduko(matrix, 0, 0))
			print(matrix);
		else
			System.out.println("Sorry Dont try to be smart");
		StringBuilder sb = new StringBuilder(); 

		for (int i = 0; i <= 80; i++) {
			sb.append("*");
		}
		sb.append("\n");
		for (int[] int1 : matrix) {
			sb.append("*").append("\t");
			for (int j = 0; j < int1.length; j++) {
				sb.append(int1[j]).append("\t");
			}
			sb.append("*");
			sb.append("\r\n\r\n");
		}

		for (int i = 0; i <= 80; i++) {
			sb.append("*");
		}
		System.out.println(sb);

		Path path = Paths.get("C:\\Users\\kumarmur\\Desktop\\input.txt");

		Files.write(path, sb.toString().getBytes());
	}

	static boolean solveSuduko(int grid[][], int row, int col) {

		if (row == 9 - 1 && col == 9)
			return true;

		if (col == 9) {
			row++;
			col = 0;
		}

		if (grid[row][col] != 0)
			return solveSuduko(grid, row, col + 1);

		for (int num = 1; num < 10; num++) {

			if (isSafe(grid, row, col, num)) {
				grid[row][col] = num;

				if (solveSuduko(grid, row, col + 1))
					return true;
			}

			grid[row][col] = 0;
		}
		return false;
	}

	static void print(int[][] grid) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				System.out.print(grid[i][j] + " ");
			System.out.println();
		}
	}

	static boolean isSafe(int[][] grid, int row, int col, int num) {

		for (int x = 0; x <= 8; x++)
			if (grid[row][x] == num)
				return false;

		for (int x = 0; x <= 8; x++)
			if (grid[x][col] == num)
				return false;

		int startRow = row - row % 3, startCol = col - col % 3;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (grid[i + startRow][j + startCol] == num)
					return false;

		return true;
	}

}
