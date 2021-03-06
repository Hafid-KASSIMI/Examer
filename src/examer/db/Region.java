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

public final class Region extends Table {
    
    private int idRegion;
    private final int dummyIdRegion = 0;
    private String labelAR;
    private String labelTA;

    public Region() {
        super();
        setDatabase(Settings.DB_PATH);
        name = "Region";
    }

    public Region(int idRegion, String labelAR, String labelTA) {
        this();
        this.idRegion = idRegion;
        this.labelAR = labelAR;
        this.labelTA = labelTA;
    }
    
    public Region(int idRegion) {
        this();
        HashMap<String, ArrayList> res = super.select("idRegion='" + idRegion + "'");
        this.idRegion = idRegion;
        if ( res.get(res.keySet().iterator().next()).size() > 0 ) {
            labelAR = res.get("labelAR").get(0).toString();
            labelTA = res.get("labelTA").get(0).toString();
        }
        else {
            labelAR = "";
            labelTA = "";
        }
    }
    
    public ArrayList<Region> list() {
        HashMap<String, ArrayList> res = super.select("idRegion<>" + dummyIdRegion);
        ArrayList<Region> l = new ArrayList();
        for ( int i = 0, n = res.get(res.keySet().iterator().next()).size(); i < n; i++ ) {
            l.add(new Region( 
                    (int) res.get("idRegion").get(i), 
                    res.get("labelAR").get(i).toString(), 
                    res.get("labelTA").get(i).toString()) );
        }
        return l;
    }
    
    public boolean delete() {
        return delete2("idRegion=" + idRegion);
    }
    
    public boolean update() {
        fv_pairs.put("idRegion", idRegion);
        fv_pairs.put("labelAR", labelAR);
        fv_pairs.put("labelTA", labelTA);
        return update2("idRegion=" + idRegion);
    }
    
    public boolean save() {
        fv_pairs.put("idRegion", idRegion);
        fv_pairs.put("labelAR", labelAR);
        fv_pairs.put("labelTA", labelTA);
        return insert();
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getLabelAR() {
        return labelAR;
    }

    public void setLabelAR(String labelAR) {
        this.labelAR = labelAR;
    }

    public String getLabelTA() {
        return labelTA;
    }

    public void setLabelTA(String labelTA) {
        this.labelTA = labelTA;
    }

    @Override
    public String toString() {
        return labelAR;
    }

}