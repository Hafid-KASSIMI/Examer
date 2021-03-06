package examer.db;


import examer.Settings;

/**
 *
 * Generated by: S2JConverter
 * at 2020-12-24, 16:22:08
 * @author Hafid KASSIMI
 */

public final class Absent extends SpecialCandidate{
    
    public Absent() {
        super();
        name = "Absent";
        setDatabase(Settings.DB_PATH);
        messageName = "justification";
        concurrent = "Cheater";
    }
    
    public Absent(int idCandidate, int idMatter, String justification) {
        this();
        setIdcandidate(idCandidate);
        setIdmatter(idMatter);
        setMessage(justification);
    }
    public void setJustification(String justification) {
        setMessage(justification);
    }

    public String getJustification() {
        return getMessage();
    }

}