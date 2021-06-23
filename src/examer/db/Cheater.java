package examer.db;


import examer.Settings;

/**
 *
 * Generated by: S2JConverter
 * at 2020-12-24, 16:22:08
 * @author Hafid KASSIMI
 */

public final class Cheater extends SpecialCandidate {
    
    public Cheater() {
        super();
        setDatabase(Settings.DB_PATH);
        name = "Cheater";
        messageName = "sanction";
        concurrent = "Absent";
    }
    
    public Cheater(int idCandidate, int idMatter, String sanction) {
        this();
        setIdcandidate(idCandidate);
        setIdmatter(idMatter);
        setMessage(sanction);
    }
    
    public void setSanction(String sanction) {
        setMessage(sanction);
    }

    public String getSanction() {
        return getMessage();
    }

}