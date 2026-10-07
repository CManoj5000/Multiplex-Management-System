public class CineData {
    String[][][] seatMap = new String[3][5][8];
    int[][] weeklySales = new int[7][3];
    String[] time = {"10 AM", "1 PM", "4 PM", "7 PM", "10 PM"};
    String[][] persons = new String[5][];
    CineData() {
        persons[0] = new String[3];
        persons[1] = new String[2];
        persons[2] = new String[4];
        persons[3] = new String[1];
        persons[4] = new String[3];
        for(int i = 0; i < seatMap.length; i++) {
            for(int j = 0; j < seatMap[i].length; j++) {
                char row = (char) ('A' + j);
                for(int k = 0; k < seatMap[i][j].length; k++){
                    int column = k;
                    seatMap[i][j][k] = row + "" + column;
                }
            }
        }
    }
}