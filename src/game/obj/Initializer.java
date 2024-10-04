package game.obj;


import game.DasMeteorit;
import game.DasMeteorit.State;
import game.Obj;

public class Initializer extends Obj {
    
    private long startTime;
    
    public Initializer(DasMeteorit game) {
        super(game);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void updateInitializing() {
        if (System.currentTimeMillis() - startTime > 100) {
            game.setState(State.TITLE);
        }
    }
    
}
