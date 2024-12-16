package Main;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<GameListener> observers = new ArrayList<>();

    public void addObserver(GameListener observer) {
        observers.add(observer);
    }

    public void notifyObservers(Board board) {
        for (GameListener observer : observers) {
            observer.updateBoard(board);
        }
    }
}
