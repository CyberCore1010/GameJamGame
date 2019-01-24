package objects.handlers;

public class EventHandler {
    private StateHandler stateHandler;
    private ObjectHandler objectHandler;

    public EventHandler(StateHandler stateHandler, ObjectHandler objectHandler) {
        this.stateHandler = stateHandler;
        this.objectHandler = objectHandler;
    }
}
