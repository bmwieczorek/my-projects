import java.util.HashMap;
import java.util.Map;

enum MCType {
    MODIFY_STREET_NAME, SPEED_LIMIT;
}

class MCStatusConst {
    public final static MCState PENDING = new PendingMCState();
    public final static MCState APPROVED = new ApprovedMCState();
    public final static MCState REJECTED = new RejectedMCState();
}

class MCDO {
    MCState state;
    private final String name;
    private final MCType type;

    public MCDO(String name, MCType type) {
        this.name = name;
        this.type = type;
        this.state = MCStatusConst.PENDING;
    }

    public void approve() {
        state.approve(this);
    }

    public void reject() {
        state.reject(this);
    }

    @Override
    public String toString() {
        return "MCDO [name=" + name + ", state=" + state + ", type=" + type + "]";
    }
}

interface MCDAO {
    public void save(MCDO mcdo);

    public MCDO loadById(int id);
}

interface MCState {
    void approve(MCDO mcdo);

    void reject(MCDO mcdo);
}

class PendingMCState implements MCState {
    @Override
    public void approve(MCDO mcdo) {
        System.out.println("Approving pending mc: " + mcdo);
        mcdo.state = MCStatusConst.APPROVED;
        System.out.println("Approved pending mc: " + mcdo);
    }

    @Override
    public void reject(MCDO mcdo) {
        System.out.println("Rejecting pending mc: " + mcdo);
        mcdo.state = MCStatusConst.REJECTED;
        System.out.println("Rejected pending mc: " + mcdo);
    }

    @Override
    public String toString() {
        return "pending";
    }
}

class RejectedMCState implements MCState {
    @Override
    public void approve(MCDO mcdo) {
        System.out.println("Approving rejected mc: " + mcdo);
        mcdo.state = MCStatusConst.APPROVED;
        System.out.println("Approved rejected mc: " + mcdo);
    }

    @Override
    public void reject(MCDO mcdo) {
        throw new UnsupportedOperationException("Cannot reject already rejected mc: " + mcdo);
    }

    @Override
    public String toString() {
        return "rejected";
    }
}

class ApprovedMCState implements MCState {
    @Override
    public void approve(MCDO mcdo) {
        throw new UnsupportedOperationException("Cannot approve already approved mc: " + mcdo);
    }

    @Override
    public void reject(MCDO mcdo) {
        System.out.println("Rejecting approved mc: " + mcdo);
        mcdo.state = MCStatusConst.REJECTED;
        System.out.println("Rejected approved mc: " + mcdo);
    }

    @Override
    public String toString() {
        return "approved";
    }
}

class MCBO {
    MCDO mcdo;
    MCDAO mcdao;

    public MCBO(MCDO mcdo, MCDAO mcdao) {
        this.mcdao = mcdao;
        this.mcdo = mcdo;
    }

    public void approve() {
        mcdo.approve();
    }

    public void reject() {
        mcdo.reject();
    }

    public void save() {
        mcdao.save(mcdo);
    }
}

public class MC {

    public static void main(String[] args) {

        MCDAO mcdao = new MCDAO() {
            Map<Integer, MCDO> mcdos = new HashMap<Integer, MCDO>();
            int id = 0;

            @Override
            public void save(MCDO mcdo) {
                mcdos.put(id++, mcdo);
                System.out.println("saved" + mcdo);
            }

            @Override
            public MCDO loadById(int id) {
                return mcdos.get(id);
            }
        };
        MCDO mcdo = new MCDO("mc1", MCType.MODIFY_STREET_NAME);
        System.out.println(mcdo);
        MCBO mcbo = new MCBO(mcdo, mcdao);
        mcbo.approve();
        mcbo.save();
        MCDO newMcdo = mcdao.loadById(0);
        System.out.println(newMcdo);
        MCBO newMcbo = new MCBO(newMcdo, mcdao);
        newMcbo.reject();
        newMcbo.save();
        MCDO newMcdo2 = mcdao.loadById(1);
        System.out.println(newMcdo2);

    }
}
