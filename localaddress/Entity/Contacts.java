/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package localaddress.Entity;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;
import localaddress.util.*;
import java.util.*;

/**
 *
 * @author kanishka
 * This class contains the information about the contact. Stores data loop
 * API
 * getAllContacts
 *update
 * remove *
 *sort
 *
 */
public class Contacts implements XEntity {

    public String Id;
    public String FName;
    public String LName;
    public String Address;
    public String Email;
    public String Telephone;
    public String Gender;
    public String BirthDay;
    public String Spouse;
    public String ChildrenName;
    public String Company;
    public String CompanyAddress;
    public String Job;
    public String Department;
    public String CompanyPhone;
    public String WebSite;
    private Map<String, String> attributes;
    private xmlConnection xml;

    public Contacts() {
        this.attributes = new HashMap<String, String>();
        this.attributes.put("FName", "firstname");
        this.attributes.put("LName", "lastname");
        this.attributes.put("Address", "address");
        this.attributes.put("Email", "email");
        this.attributes.put("Telephone", "telephone");

        this.attributes.put("Gender", "gender");
        this.attributes.put("BirthDay", "birthday");
        this.attributes.put("Spouse", "spouse");
        this.attributes.put("ChildrenName", "childname");
        this.attributes.put("Company", "company");
        this.attributes.put("CompanyAddress", "companyaddress");
        this.attributes.put("Job", "job");
        this.attributes.put("Department", "department");
        this.attributes.put("CompanyPhone", "companyphone");
        this.attributes.put("WebSite", "website");

        xmlConnection sm = null;
        try {
            sm = new xmlConnection("Contact", "Contacts");
        } catch (Exception ex) {
            Logger.getLogger(Contacts.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.xml = sm;

    }

    public List<XEntity> find(List<XEntity> contacts, int Cri, String val) {
        List<XEntity> retu = new ArrayList<XEntity>();
        for (XEntity e : contacts) {
            Contacts ct = (Contacts) e;
            if (Cri == 0) {
                if (ct.FName.equals(val)) {
                    retu.add(ct);
                }
            } else {
                if (ct.LName.equals(val)) {
                    retu.add(ct);
                }
            }
        }
        return retu;
    }

    public List<XEntity> sort(List contacts, int Cri) {
        ContactCompare cp = new ContactCompare();
        cp.sortcrieteria = Cri;
        Collections.sort(contacts, cp);
        return contacts;

    }

    public String getId() {
        return this.Id;
    }

    public void remove() {
        xmlConnection sm = this.xml;
        sm.remove(this);
    }

    public void update() throws Exception {
        xmlConnection sm = this.xml;
        sm.update(this);
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void saveContact() {
        /*
        create the values as maps and return in to the xml connection class  for  the savin perposes.
         *the first value is the root of the page.

         */

        Map<String, String> at1 = new HashMap();
        at1.put("Root", "Contacts");
        at1.put("Parent", "Contact");
        at1.put("firstname", FName);
        at1.put("lastname", LName);
        at1.put("address", Address);
        at1.put("email", Email);
        at1.put("telephone", Telephone);

        at1.put("gender", Gender);
        at1.put("birthday", BirthDay);
        at1.put("spouse", Spouse);
        at1.put("childname", ChildrenName);
        at1.put("company", Company);
        at1.put("companyaddress", CompanyAddress);
        at1.put("job", Job);
        at1.put("department", Department);
        at1.put("companyphone", CompanyPhone);
        at1.put("website", WebSite);



        try {
            this.xml.saveEntity(at1);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public Contacts getInstance() {
        return new Contacts();
    }

    public Contacts getInstance(Map<String, String> map) {
        Contacts con = new Contacts();
        con.Id = map.get("ID");
        con.FName = map.get("FName");
        con.LName = map.get("LName");
        con.Address = map.get("Address");
        con.Email = map.get("Email");
        con.Telephone = map.get("Telephone");

        con.Gender = map.get("Gender");
        con.BirthDay = map.get("BirthDay");
        con.Spouse = map.get("Spouse");
        con.ChildrenName = map.get("ChildrenName");
        con.Company = map.get("Company");
        con.CompanyAddress = map.get("CompanyAddress");
        con.Job = map.get("Job");
        con.Department = map.get("Department");
        con.CompanyPhone = map.get("CompanyPhone");
        con.WebSite = map.get("WebSite");




        return con;
    }

    public List<XEntity> getAllContacts() {
        xmlConnection sm = this.xml;


        List<XEntity> li = sm.fillXML(this);
        //   List<Contacts> lis = fillcontacts(li);

        return li;
    }

    public String getValue(NodeList firstNameElemntList, int it) {
        Element firstNameElement = (Element) firstNameElemntList.item(0);
        NodeList firstName = firstNameElement.getChildNodes();
        String s = ((Node) firstName.item(0)).getNodeValue();
        return s;
    }

    @Override
    public String toString() {
        return "================\n Name:" + this.FName + "    " + this.LName + "\nAddress" + this.Address + "\nemail:" + this.Email + "id" + this.Id + this.BirthDay + this.Spouse + this.Company + this.CompanyPhone;

    }
}
