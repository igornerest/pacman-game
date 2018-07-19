package engine; 

import java.io.File;		// for reading .txt document
import java.util.Scanner;	// for getting user input/read .txt document

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map {

	private int mapHeigth;
	private int mapWidth;

	private Tile[][] map;

	private ObservableList tilesList = FXCollections.observableArrayList();

	public void createMap(String mapString) throws Exception {
		File mapFile = new File(mapString);
		Scanner sc = new Scanner(mapFile);

		mapHeigth = Integer.parseInt(sc.nextLine());
		mapWidth = Integer.parseInt(sc.nextLine());
		map = new Tile[mapHeigth][mapWidth];

		for (int i = 0; i < mapHeigth; i++) {
			String tileString = sc.nextLine();

			for (int j = 0; j < mapWidth; j++) {
				switch(tileString.charAt(j)) {
					case '0':
						map[i][j] = new Path(true, j, i);
						break;

					case '-':
						map[i][j] = new Brick(j , i);
						break;

					case ' ':
						map[i][j] = new Path(false,  j, i);
						break;

					default:
						System.out.println("Por favor, verifique se o arquivo do level esta correto");
						break;
				}
				tilesList.addAll(map[i][j].getTileImageView());
			}
		}
	}

	public boolean isBrick(int xPos, int yPos) {
		if(map[xPos][yPos] instanceof Brick)
			return true;
		else 
			return false;
	}


	public ObservableList getTilesList() {
		return this.tilesList;
	}
	
}