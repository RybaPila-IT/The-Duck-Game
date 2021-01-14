package The.Duck.Game.GameManagers;

import The.Duck.Game.BoardObjects.RegularBoardObjects.BoardObject;
import The.Duck.Game.BoardObjects.Utility.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all BoardObjects present at the game scene.
 */
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

    /**
     * Performing onTic operation for all BoardObjects.
     * <p>
     * This procedure also erases all invalid objects
     * present on the object list.
     * </p>
     */
    public void onTic() {

        List<BoardObject> invalid = new ArrayList<>();

        for (BoardObject o : boardObjects) {
            o.onTic();
            if (!o.isValid())
                invalid.add(o);
        }

        removeInvalidObjects(invalid);
    }

    public void setBoardObjectsList(List<BoardObject> l) {
        this.boardObjects = l;
    }

    private void removeInvalidObjects(List<BoardObject> invalid) {
        for (BoardObject o : invalid)
            boardObjects.remove(o);
    }

}
