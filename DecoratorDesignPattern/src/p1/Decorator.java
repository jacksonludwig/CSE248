package p1;

public abstract class Decorator implements InterfaceComponent {
    private InterfaceComponent component;

    public void setComponent(InterfaceComponent component) {
        this.component = component;
    }

    public void doJob() {
        if(component != null) {
            component.doJob();
        }
    }
}
