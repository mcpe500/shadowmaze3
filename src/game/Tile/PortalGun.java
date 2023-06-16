package src.game.Tile;

public class PortalGun<T extends Portal> {
    private T portal1;
    private T portal2;

    public void addPortal(T portal) {
        if (portal1 == null) {
            portal1 = portal;
        } else if (portal2 == null) {
            portal2 = portal;
        } else {
            T temp = portal1;
            portal1 = portal2;
            portal2 = portal;
            ((Portal) portal1).setImage(portal2.getPortalImage());
            ((Portal) portal2).setImage(temp.getPortalImage());
        }
    }

    public void removePortal(T portal) {
        if (portal1 == portal) {
            portal1 = null;
        } else if (portal2 == portal) {
            portal2 = null;
        }
    }

    public T getPortal1() {
        return portal1;
    }

    public T getPortal2() {
        return portal2;
    }
}