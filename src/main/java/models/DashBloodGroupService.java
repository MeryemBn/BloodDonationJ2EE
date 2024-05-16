package models;

public class DashBloodGroupService {
    private String groupeSanguin;
    private int qte;

    public DashBloodGroupService(String groupeSanguin, int qte) {
        this.groupeSanguin = groupeSanguin;
        this.qte = qte;
    }

    public String getGroupeSanguin() {
        return groupeSanguin;
    }

    public void setGroupeSanguin(String groupeSanguin) {
        this.groupeSanguin = groupeSanguin;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
