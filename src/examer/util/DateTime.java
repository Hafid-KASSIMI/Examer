/*
 * Copyright (C) 2021 Sicut
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
package examer.util;

import examer.Settings;
import java.time.Duration;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Sicut
 */
public class DateTime {
    
    public static final String[] MONTHS = {"", "يناير", "فبراير", "مارس", "أبريل", "ماي", "يونيو", "يوليوز", "غشت", "شتنبر", "أكتوبر", "نونبر", "دجنبر"};
    public static final String[] DAYS = {"", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الحمعة", "السبت", "الأحد"};
    
    public static String getArabicDate(String isoDate) {
        Pattern iso =  Pattern.compile("^(\\d{2,4})-(\\d{1,2})-(\\d{1,2})$");
        Matcher m = iso.matcher(isoDate);
        if ( m.find() ) {
            try {
                int year = Integer.parseInt(m.group(1));
                int month = Integer.parseInt(m.group(2));
                int day = Integer.parseInt(m.group(3));
                return ( day < 10 ? "0" : "" ) + day + " " +
                    ( month < 13 && month > 0  ? MONTHS[month] : "") + " " + year;
            }
            catch( IllegalArgumentException e ) {
            }
        }
        return "";
    }
    
    public static String getTimeString(Duration duration) {
        if ( duration == null )
            return "";
        ArrayList<String> res = new ArrayList();
        long minutes = Math.abs(duration.toMinutes() % 60);
        long hours = Math.abs(duration.toHours() % 24);
        long days = Math.abs(duration.toDays());
        if ( days > 0 ) {
            if ( days == 1 ) {
                res.add(String.format(Settings.I18N_BUNDLE.getString("ONE_DAY"), days));
            }
            else if ( days == 2 ) {
                res.add(String.format(Settings.I18N_BUNDLE.getString("TWO_DAYS"), days));
            }
            else if ( days < 11 ) {
                res.add(String.format(Settings.I18N_BUNDLE.getString("3_TO_10_DAYS"), days));
            }
            else {
                res.add(String.format(Settings.I18N_BUNDLE.getString("ABOVE_10_DAYS"), days));
            }
        }
        if ( hours > 0 ) {
            if ( hours == 1 ) {
                res.add(String.format(Settings.I18N_BUNDLE.getString("ONE_HOUR"), hours));
            }
            else if ( hours == 2 ) {
                res.add(String.format(Settings.I18N_BUNDLE.getString("TWO_HOURS"), hours));
            }
            else if ( hours < 11 ) {
                res.add(String.format(Settings.I18N_BUNDLE.getString("3_TO_10_HOURS"), hours));
            }
            else {
                res.add(String.format(Settings.I18N_BUNDLE.getString("ABOVE_10_HOURS"), hours));
            }
        }
        if ( minutes >= 0 ) {
            if ( minutes == 0 ) {
                res.add(String.format(Settings.I18N_BUNDLE.getString("UNDER_ONE_MINUTE"), minutes));
            }
            else if ( minutes == 1 ) {
                res.add(String.format(Settings.I18N_BUNDLE.getString("ONE_MINUTE"), minutes));
            }
            else if ( minutes == 2 ) {
                res.add(String.format(Settings.I18N_BUNDLE.getString("TWO_MINUTES"), minutes));
            }
            else if ( minutes < 11 ) {
                res.add(String.format(Settings.I18N_BUNDLE.getString("3_TO_10_MINUTES"), minutes));
            }
            else {
                res.add(String.format(Settings.I18N_BUNDLE.getString("ABOVE_10_MINUTES"), minutes));
            }
        }
        return res.isEmpty() ? "" : res.stream().collect(Collectors.joining(Settings.I18N_BUNDLE.getString("AND")));
    }
    
}
