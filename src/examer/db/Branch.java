
package examer.db;


import examer.Settings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.sicut.sqlite.Table;

/**
 *
 * Generated by: S2JConverter
 * at 2020-12-24, 16:22:08
 * @author Hafid KASSIMI
 */

public final class Branch extends Table {
    
    
    private int idBranch;
    private String label;

    public Branch() {
        super();
        name = "Branch";
        setDatabase(Settings.DB_PATH);
    }
    
    public Branch(int idBranch, String label) {
        this();
        
        this.idBranch = idBranch;
        this.label = label;
    }
    
    public Branch(int idBranch) {
        this();
        HashMap<String, ArrayList> res = super.select("1 and idBranch='" + idBranch + "'");
        
        this.idBranch = idBranch;
        if ( res.get(res.keySet().iterator().next()).size() > 0 ) {
            
            label = res.get("label").get(0).toString();
        }
        else {
            
            label = "";
        }
    }
    public ArrayList<Branch> list() {
        HashMap<String, ArrayList> res = super.select();
        ArrayList<Branch> l = new ArrayList();
        for ( int i = 0, n = res.get(res.keySet().iterator().next()).size(); i < n; i++ ) {
            l.add( new Branch( (int) res.get("idBranch").get(i), res.get("label").get(i).toString() ) );
        }
        return l;
    }
    /**
     * Returns a list (as ArrayList) of branches that are stored in the database. 
     * It retrieves records from the "Branch" table inner joined to "Exam_branch" table.
     * @param idExam the examen id
     * @param includeIdExam when <tt>true</tt>, all branches assigned to <tt>idExam</tt> are listed. 
     * Otherwise, all branches that are not assigned to <tt>idExam</tt> are retrieved.
     * @return 
    **/
    public ArrayList<Branch> list(Integer idExam, Boolean includeIdExam) {
        HashMap<String, ArrayList> res = super.select("idBranch in (select idBranch from Exam_branch where idExam" + ( includeIdExam ? "=":"<>" ) + idExam + ")");
        ArrayList<Branch> l = new ArrayList();
        for ( int i = 0, n = res.get(res.keySet().iterator().next()).size(); i < n; i++ ) {
            l.add( new Branch( (int) res.get("idBranch").get(i), res.get("label").get(i).toString() ) );
        }
        return l;
    }
    
    public ArrayList<Branch> list(Integer idExam) {
        HashMap<String, ArrayList> res = super.select("idBranch in (select idBranch from Exam_branch where idExam=" + idExam + ")");
        ArrayList<Branch> l = new ArrayList();
        for ( int i = 0, n = res.get(res.keySet().iterator().next()).size(); i < n; i++ ) {
            l.add( new Branch( (int) res.get("idBranch").get(i), res.get("label").get(i).toString() ) );
        }
        return l;
    }
    
    public ArrayList<Branch> todayList(Integer idExam) {
        HashMap<String, ArrayList> res = super.select("idBranch in (select idBranch from PLMB where idExam=" + idExam + " and planDate=date('now'))");
        ArrayList<Branch> l = new ArrayList();
        for ( int i = 0, n = res.get(res.keySet().iterator().next()).size(); i < n; i++ ) {
            l.add( new Branch( (int) res.get("idBranch").get(i), res.get("label").get(i).toString() ) );
        }
        return l;
    }
    
    public ArrayList<Branch> currentList(Integer idExam) {
        HashMap<String, ArrayList> res = super.select("idBranch in ("
                + "select idBranch from PLMB p, Plan pl "
                + "where idExam=" + idExam + " and p.idPlan=pl.idPlan "
                + "and p.planDate=date('now') and "
                + "pl.startTime<=strftime('%H:%M', time('now')) and time('now') <= "
                + "time(pl.startTime, strftime('%H', time(pl.duration)) || ' hours', "
                + "strftime('%M', time(pl.duration)) || ' minutes'))");
        ArrayList<Branch> l = new ArrayList();
        for ( int i = 0, n = res.get(res.keySet().iterator().next()).size(); i < n; i++ ) {
            l.add( new Branch( (int) res.get("idBranch").get(i), res.get("label").get(i).toString() ) );
        }
        return l;
    }
    
    public String listAsString(Integer idExam, Boolean includeIdExam) {
        HashMap<String, ArrayList> records = super.select("idBranch in (select idBranch from Exam_branch where idExam" + ( includeIdExam ? "=":"<>" ) + idExam + ")");
        ArrayList<String> res = new ArrayList();
        for ( int i = 0, n = records.get(records.keySet().iterator().next()).size(); i < n; i++ ) {
            res.add(records.get("label").get(i).toString());
        }
        return res.stream().collect(Collectors.joining(" | "));
    }
    
    public ArrayList<Branch> listNotPlanned(Integer idExam) {
        HashMap<String, ArrayList> res = super.select("idBranch not in (select idBranch from Exam_branch where idExam='" + idExam + "')");
        ArrayList<Branch> l = new ArrayList();
        for ( int i = 0, n = res.get(res.keySet().iterator().next()).size(); i < n; i++ ) {
            l.add( new Branch( (int) res.get("idBranch").get(i), res.get("label").get(i).toString() ) );
        }
        return l;
    }
    
    public boolean deleteAll(int idExam) {
        return delete2("1 and idBranch in (select idBranch from Exam_branch where idExam='" + idExam + "')");
    }
    
    public boolean delete() {
        return delete2("1 and idBranch='" + idBranch + "'");
    }
    
    public boolean update() {
        
        fv_pairs.put("idBranch", idBranch);
        fv_pairs.put("label", label);
        return update2("1 and idBranch='" + idBranch + "'");
    }
    
    public boolean save() {
        
        fv_pairs.put("idBranch", idBranch);
        fv_pairs.put("label", label);
        return insert();
    }
    
    
    public int nextIdbranch() {
        HashMap<String, ArrayList> res = super.select(new String[] {"max(idBranch) as idBranch"}, "1");
        try {
            return (int) res.get("idBranch").get(0) + 1;
        }
        catch ( Exception e ) {
            return 1;
        }
    }
    
    
    public int count(String branch) {
        HashMap<String, ArrayList> res = super.select(new String[] {"count(idBranch) as n"}, "label like '" + branch + "'");
        try {
            return (int) res.get("n").get(0);
        }
        catch ( Exception e ) {
            return 0;
        }
    }
    
    public int count(String branch, int idExam) {
        HashMap<String, ArrayList> res = super.select(new String[] {"count(idBranch) as n"}, "label like '" + branch + "' and idBranch in (select idBranch from Exam_branch where idExam='" + idExam + "')");
        try {
            return (int) res.get("n").get(0);
        }
        catch ( Exception e ) {
            return 0;
        }
    }
    
    public Boolean isUnique(String branch) {
        HashMap<String, ArrayList> res = super.select(new String[] {"count(idBranch) as n"}, "label like '" + branch + "' and idBranch<>'" + idBranch + "'");
        try {
            return (int) res.get("n").get(0) == 0;
        }
        catch ( Exception e ) {
            return true;
        }
    }
    
    public Boolean isUnique(String branch, int idExam) {
        HashMap<String, ArrayList> res = super.select(new String[] {"count(idBranch) as n"}, "label like '" + branch + "' and idBranch<>'" + idBranch + "' and idBranch in (select idBranch from Exam_branch where idExam='" + idExam + "')");
        try {
            return (int) res.get("n").get(0) == 0;
        }
        catch ( Exception e ) {
            return true;
        }
    }
    
    
    public void setIdbranch(int idBranch) {
        this.idBranch = idBranch;
    }

    public int getIdbranch() {
        return idBranch;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

    public void clone(Branch branch) {
        this.idBranch = branch.getIdbranch();
        this.label = branch.getLabel();
    }

}