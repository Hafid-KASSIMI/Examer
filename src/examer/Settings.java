/*
 * Copyright (C) 2020 Sicut
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package examer;

import examer.db.Sfl;
import examer.db.Center;
import examer.db.Exam;
import examer.db.Cheater;
import examer.db.Room;
import examer.db.Region;
import examer.db.Plan;
import examer.db.Exam_branch;
import examer.db.Matter;
import examer.db.Absent;
import examer.db.Candidate;
import examer.db.ViewLikeTable;
import examer.db.Plmb;
import examer.db.Branch;
import examer.util.TimeBoss;
import java.util.ResourceBundle;
import org.sicut.db.Preferences;
import org.sicut.util.EnvVariable;

public class Settings {
    public static final String DB_FOLDER_PATH = EnvVariable.APPDATADirectory() + "/Examer/";
    public static final String DB_NAME = "data.exam";
    public static final String PREF_DB_NAME = "preferences.exam";
    public static final String I18N_DB_NAME = "properties.exam";
    public static final String DB_PATH = DB_FOLDER_PATH + DB_NAME;
    public static final String PREF_DB_PATH = DB_FOLDER_PATH + PREF_DB_NAME;
    public static final String I18N_DB_PATH = DB_FOLDER_PATH + I18N_DB_NAME;
    
    public static final String APP_TITLE = "Examer";
    public static final String APP_YEAR = "2021";
    public static final String APP_VERSION = "1.0.0";
    public static final String APP_DATE = "16/06/2021";
    
    public static ResourceBundle I18N_BUNDLE;
    public static Preferences PREF_BUNDLE;
    
    public static String SELECTED_LANG;
    
    /* DB Tables */
    public static final Center CENTER = new Center();
    public static final Exam EXAM = new Exam();
    public static final Branch BRANCH = new Branch();
    public static final Matter MATTER = new Matter();
    public static final Region REGION = new Region();
    public static final Room ROOM = new Room();
    public static final Exam_branch EXAM_BRANCH = new Exam_branch();
    public static final Candidate CANDIDATE = new Candidate();
    public static final Plmb PLMB = new Plmb();
    public static final Plan PLAN = new Plan();
    public static final Absent ABSENT = new Absent();
    public static final Cheater CHEATER = new Cheater();
    public static final Sfl SFL = new Sfl();
    public static final ViewLikeTable VL_TABLE = new ViewLikeTable();
    public static final TimeBoss BOSS = new TimeBoss();
}
