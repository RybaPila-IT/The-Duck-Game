package The.Duck.Game;

import java.util.ArrayList;
import java.util.List;

public class BoardElements {

    private List<BoardObject> boardObjects;
    private static BoardElements instance;

    private BoardElements() {
        this.boardObjects = new ArrayList<>();
    }

    public static BoardElements getInstance() {

        if (instance == null)
            instance = new BoardElements();

        return instance;
    }

    public void addBoardObject(BoardObject object) {
        boardObjects.add(object);
    }

    public List<BoardObject> collidedWith(Rectangle r) {

        List<BoardObject> collided = new ArrayList<>();

        for (BoardObject o : boardObjects)
            if (o.intersects(r))
                collided.add(o);

        return collided;
    }

    public void onTic() {

        List<BoardObject> invalid = new ArrayList<>();

        for (BoardObject o : boardObjects) {
            o.onTic();
            if (!o.isObjectValid())
                invalid.add(o);
        }

        removeInvalidObjects(invalid);
    }

    private void removeInvalidObjects(List<BoardObject> invalid) {
        for (BoardObject o : invalid)
            boardObjects.remove(o);
    }

    public void setBoardObjectsList(List<BoardObject> l) {
        this.boardObjects = l;
    }

}
