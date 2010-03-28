package mapcorrection.domain;

public class DO{
    State state;    
    private final String name;
    private final Type type;
    public DO(String name, Type type) {
        this.name = name;
        this.type = type;        
        this.state = State.PENDING;
    }
    public void approve(){
        state.approve(this);
    }
    
    public void reject(){
        state.reject(this);
    }
    @Override
    public String toString() {
        return "MCDO [name=" + name + ", state=" + state + ", type=" + type + "]";
    }
}
