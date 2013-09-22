/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package localaddress.Entity;

import java.util.*;
import localaddress.util.*;

/**
 *
 * @author kanishka
 */
public class Diary implements XEntity {

    public String Id;
    public String Date;
    public String Value;
    private Map<String, String> attributes;
    private xmlConnection xml;

    public Diary() {
        this.attributes = new HashMap<String, String>();
        this.attributes.put("Date", "date");
        this.attributes.put("Value", "value");
        xmlConnection sm = null;
        try {
            sm = new xmlConnection("Entry", "Diary");
        } catch (Exception ex) {
            System.out.println("falire in diary xml connection");
        }
        this.xml = sm;
    }

    @Override
    public void saveContact() {

        Map<String, String> at1 = new HashMap();
        at1.put("Root", "Diary");
        at1.put("Parent", "Entry");
        at1.put("date", Date);
        at1.put("value", Value);
        try {
            this.xml.saveEntity(at1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {

        return this.Id;
    }

    public Diary getInstance(Map<String, String> map) {
        Diary con = new Diary();
        con.Id = map.get("ID");
        con.Date = map.get("Date");
        con.Value = map.get("Value");
        return con;
    }

    public Diary getInstance() {
        return new Diary();
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public List<XEntity> getAllContacts() {
        xmlConnection sm = this.xml;


        List<XEntity> li = sm.fillXML(this);
        //   List<Contacts> lis = fillcontacts(li);

        return li;
    }

    @Override
    public String toString() {
        return "date: " + this.Date + "value" + this.Value + "Id" + this.Id;
    }
}

 