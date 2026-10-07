public class ViewSeatMap {
    public void viewSeatMap(CineData viewMap) {
        for(int i = 0; i < viewMap.seatMap.length; i++) {
            System.out.println("Screen n0 : " + (i + 1));
            for(int j = 0; j < viewMap.seatMap[i].length; j++) {
                for(int k = 0; k < viewMap.seatMap[i][j].length; k++){
                    System.out.print(viewMap.seatMap[i][j][k]);
                }
            }
        }
    }
}
