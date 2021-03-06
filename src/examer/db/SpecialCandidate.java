package examer.db;


import examer.Settings;
import java.util.ArrayList;
import java.util.HashMap;
import org.sicut.sqlite.Table;

/**
 *
 * Generated by: S2JConverter
 * at 2020-12-24, 16:22:08
 * @author Hafid KASSIMI
 */

public class SpecialCandidate extends Table {
    
    private int idCandidate;
    private int idMatter;
    private String message;
    protected String messageName;
    protected String concurrent;

    public SpecialCandidate() {
        super();
        setDatabase(Settings.DB_PATH);
    }
    
    public SpecialCandidate(int idCandidate, int idMatter, String message) {
        this();
        
        this.idCandidate = idCandidate;
        this.idMatter = idMatter;
        this.message = message;
    }
    
    public SpecialCandidate(int idCandidate, int idMatter) {
        this();
        HashMap<String, ArrayList> res = super.select("1 and idCandidate='" + idCandidate + "' and idMatter='" + idMatter + "'");
        
        this.idCandidate = idCandidate;
        this.idMatter = idMatter;
        if ( res.get(res.keySet().iterator().next()).size() > 0 ) {
            
            message = res.get(messageName).get(0).toString();
        }
        else {
            
            message = "";
        }
    }
    
    public ArrayList<SpecialCandidate> list() {
        HashMap<String, ArrayList> res = super.select("1 and idCandidate not in (select idCandidate from " + concurrent + ")");
        ArrayList<SpecialCandidate> l = new ArrayList();
        for ( int i = 0, n = res.get(res.keySet().iterator().next()).size(); i < n; i++ ) {
            l.add(new SpecialCandidate( (int) res.get("idCandidate").get(i), (int) res.get("idMatter").get(i), res.get(messageName).get(i).toString() ) );
        }
        return l;
    }
    
    public boolean delete() {
        return delete2("1 and idCandidate='" + idCandidate + "' and idMatter='" + idMatter + "'");
    }
    
    public boolean clean(int idRoom) {
        return delete2("1 and idCandidate in (select idCandidate from Candidate where idRoom='" + idRoom + "') and idMatter='" + idMatter + "'");
    }
    
    public boolean deleteAll(int idRoom) {
        return delete2("1 and idCandidate in (select idCandidate from Candidate where idRoom='" + idRoom + "')");
    }
    
    public boolean update() {
        
        fv_pairs.put("idCandidate", idCandidate);
        fv_pairs.put("idMatter", idMatter);
        fv_pairs.put(messageName, message);
        return update2("1 and idCandidate=" + idCandidate + " and idMatter=" + idMatter);
    }
    
    public boolean save() {
        
        fv_pairs.put("idCandidate", idCandidate);
        fv_pairs.put("idMatter", idMatter);
        fv_pairs.put(messageName, message);
        return insert();
    }
    
    
    public int count(int idCandidate, int idMatter) {
        HashMap<String, ArrayList> res = super.select(new String[] {"count(*) as n"}, "1 and idCandidate='" + idCandidate + "' and idMatter='" + idMatter + "'");
        try {
            return (int) res.get("n").get(0);
        }
        catch ( Exception e ) {
            return 0;
        }
    }
    
    public int count(int idCandidate) {
        HashMap<String, ArrayList> res = super.select(new String[] {"count(*) as n"}, "1 and idCandidate='" + idCandidate + "'");
        try {
            return (int) res.get("n").get(0);
        }
        catch ( Exception e ) {
            return 0;
        }
    }
    
    
    public int countConcurrent(int idCandidate, int idMatter) {
        int result;
        String tmp = name;
        name = concurrent;
        HashMap<String, ArrayList> res = super.select(new String[] {"count(*) as n"}, "idCandidate=" + idCandidate + " and idMatter=" + idMatter);
        try {
            result = (int) res.get("n").get(0);
        }
        catch ( Exception e ) {
            result = 0;
        }
        name = tmp;
        return result;
    }
    
    public int countConcurrent(int idCandidate) {
        int result;
        String tmp = name;
        name = concurrent;
        HashMap<String, ArrayList> res = super.select(new String[] {"count(*) as n"}, "idCandidate=" + idCandidate);
        try {
            result = (int) res.get("n").get(0);
        }
        catch ( Exception e ) {
            result = 0;
        }
        name = tmp;
        return result;
    }
    public int nextIdcandidate() {
        HashMap<String, ArrayList> res = super.select(new String[] {"max(idCandidate) as idCandidate"}, "1");
        try {
            return (int) res.get("idCandidate").get(0) + 1;
        }
        catch ( Exception e ) {
            return 1;
        }
    }
    public int nextIdmatter() {
        HashMap<String, ArrayList> res = super.select(new String[] {"max(idMatter) as idMatter"}, "1");
        try {
            return (int) res.get("idMatter").get(0) + 1;
        }
        catch ( Exception e ) {
            return 1;
        }
    }
    
    
    public void setIdcandidate(int idCandidate) {
        this.idCandidate = idCandidate;
    }

    public int getIdcandidate() {
        return idCandidate;
    }
    public void setIdmatter(int idMatter) {
        this.idMatter = idMatter;
    }

    public int getIdmatter() {
        return idMatter;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}