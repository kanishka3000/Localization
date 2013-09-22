/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package localaddress.ctl;

import java.util.logging.Level;
import java.util.logging.Logger;
import localaddress.*;
import localaddress.Entity.*;
import java.util.*;

/**
 *
 * @author kanishka
 */
public class ContactHomeCtl {

    public ResourceBundle sinhala = ResourceBundle.getBundle("IMG/English");
    ContactHome home;
    AddContact addcontact;
    ViewContact viewcontact;
    FindContact find;
    public String langu = "English";

    public void login(Login log) {
        String password = log.password.getText();
        if (password.equals("123")) {
            String lang = (String) log.Language.getSelectedItem();
            ResourceBundle res;
            if (lang.equals("English")) {
                res = ResourceBundle.getBundle("IMG/English");
            } else {
                res = ResourceBundle.getBundle("IMG/Sinhala");
            }
            new ContactHome(null).main(res);
            log.dispose();
        } else {
            log.pasres.setText("Incorrect password");
        }
        System.out.println(password);
    }

    public List<XEntity> getAllContacts() {
        List<XEntity> contacts = new Contacts().getAllContacts();
        return contacts;
    }

    public List<XEntity> sortByFirstName(List<XEntity> list) {
        List<XEntity> a = new Contacts().sort(list, 0);
        return a;
    }

    public List<XEntity> sortByLasName(List<XEntity> list) {
        List<XEntity> a = new Contacts().sort(list, 1);
        return a;
    }

    public void saveContact() {
        String FName = this.addcontact.Fname.getText();
        this.addcontact.Fname.setText("");
        String LName = this.addcontact.Lanme.getText();
        this.addcontact.Lanme.setText("");
        String Address = this.addcontact.Road.getText() + "," + this.addcontact.City.getText() + "," + this.addcontact.Province.getText() + "," + this.addcontact.State.getText();
        this.addcontact.Road.setText("");
        this.addcontact.City.setText("");
        this.addcontact.Province.setText("");
        this.addcontact.State.setText("");

        String Email = this.addcontact.Email.getText();
        this.addcontact.Email.setText("");
        String Gender = (String) this.addcontact.Gender.getSelectedItem();
        String BirthDay = this.addcontact.Bday.getText();
        this.addcontact.Bday.setText("");
        String Spouce = this.addcontact.Spouse.getText();
        this.addcontact.Spouse.setText("");
        String ChildName = this.addcontact.ChildName.getText();
        this.addcontact.ChildName.setText("");
        String Company = this.addcontact.company.getText();
        this.addcontact.company.setText("");
        String CompanyAddress = this.addcontact.CompanyAddress.getText();
        this.addcontact.CompanyAddress.setText("");
        String Job = this.addcontact.Job.getText();
        this.addcontact.Job.setText("");
        String Department = this.addcontact.Department.getText();
        this.addcontact.Department.setText("");
        String Phone = this.addcontact.Telephone.getText();
        this.addcontact.Telephone.setText("");
        String compnyPhone = this.addcontact.CPhone.getText();
        this.addcontact.CPhone.setText("");
        String Web = this.addcontact.Web.getText();
        this.addcontact.Web.setText("");
        this.addcontact.addconfirm.setText(sinhala.getString("CONTACT_SUCCESSFULLY_ADDED....."));
        Contacts ct = new Contacts();
        ct.Address = Address;
        ct.FName = FName;
        ct.LName = LName;
        ct.Email = Email;
        ct.BirthDay = BirthDay;
        ct.ChildrenName = ChildName;
        ct.Company = Company;
        ct.CompanyAddress = CompanyAddress;
        ct.CompanyPhone = compnyPhone;
        ct.Department = Department;
        ct.Gender = Gender;
        ct.Job = Job;
        ct.Spouse = Spouce;
        ct.Telephone = Phone;
        ct.WebSite = Web;
        ct.saveContact();


    }

    public void updateContact(Contacts cts) {
        String FName = this.viewcontact.Fname.getText();
        String LName = this.viewcontact.Lanme.getText();
        String Address = this.viewcontact.Road.getText();


        String Email = this.viewcontact.Email.getText();
        //String Gender=(String)this.viewcontact.Gender.getSelectedItem();
        String BirthDay = this.viewcontact.Bday.getText();
        String Spouce = this.viewcontact.Spouse.getText();
        String ChildName = this.viewcontact.ChildName.getText();
        String Company = this.viewcontact.company.getText();
        String CompanyAddress = this.viewcontact.CompanyAddress.getText();
        String Job = this.viewcontact.Job.getText();
        String Department = this.viewcontact.Department.getText();
        String Phone = this.viewcontact.Telephone.getText();
        String compnyPhone = this.viewcontact.CPhone.getText();
        String Web = this.viewcontact.Web.getText();
        this.viewcontact.addconfirm.setText(sinhala.getString("CONTACT_EDITED,_CHANGES_WILL_BE_VISIBLE_ON_RESTART....."));
        Contacts ct = cts;
        ct.Address = Address;
        ct.FName = FName;
        ct.LName = LName;
        ct.Email = Email;
        ct.BirthDay = BirthDay;
        ct.ChildrenName = ChildName;
        ct.Company = Company;
        ct.CompanyAddress = CompanyAddress;
        ct.CompanyPhone = compnyPhone;
        ct.Department = Department;
        // ct.Gender=Gender;
        ct.Job = Job;
        ct.Spouse = Spouce;
        ct.Telephone = Phone;
        ct.WebSite = Web;
        try {
            ct.update();
        } catch (Exception ex) {
            this.viewcontact.addconfirm.setText(sinhala.getString("UPDATE_FAILED_...........PLEASE_TRY_AGAIN_LATER"));
            Logger.getLogger(ContactHomeCtl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeContact(Contacts cts) {
        cts.remove();
        this.viewcontact.addconfirm.setText(sinhala.getString("CONTACT_WAS_REMOVED....._"));
    }

    public void findContacts(FindContact fin, int Cri) {
        List a = new Contacts().find(home.conts, Cri, fin.Name.getText());
        home.intilizeContactTableBody(a);
    }

    public void showAddContacts(ContactHome home) {
        this.home = home;
        if (this.addcontact == null) {
            this.addcontact = new AddContact(this.sinhala);
            this.addcontact.setControl(this);
            this.addcontact.setLanguage(langu);
        }
        this.addcontact.setSize(home.getWidth(), home.getHeight());
        this.addcontact.setLocation(home.getX(), home.getY());
        this.addcontact.setVisible(true);
        this.home.setVisible(false);

    }

    public void showHome(ViewContact home) {
        this.viewcontact = home;
        this.home.setSize(this.viewcontact.getWidth(), this.viewcontact.getHeight());
        this.home.setLocation(this.viewcontact.getX(), this.viewcontact.getY());
        this.home.setVisible(true);
        this.viewcontact.setVisible(false);

    }

    public void showHome(AddContact home) {
        this.addcontact = home;
        this.home.setSize(this.addcontact.getWidth(), this.addcontact.getHeight());
        this.home.setLocation(this.addcontact.getX(), this.addcontact.getY());
        this.home.setVisible(true);
        this.addcontact.setVisible(false);

    }

    public void showViewContact(ContactHome home, Contacts cts) {
        this.home = home;
        if (this.viewcontact == null) {
            this.viewcontact = new ViewContact(this.sinhala);
            this.viewcontact.setLanguage(langu);
        }
        this.viewcontact.setControl(this);
        this.viewcontact.setSize(home.getWidth(), home.getHeight());
        this.viewcontact.setLocation(home.getX(), home.getY());
        this.viewcontact.setContact(cts);
        this.viewcontact.setVisible(true);
        home.setVisible(false);

    }

    public void showFindContact(ContactHome home) {
        this.home = home;
        if (this.find == null) {
            this.find = new FindContact(home);
            this.find.setLanguage(langu);
        }
        this.find.setLocation(home.getX() + home.getWidth(), home.getY());
        this.find.setVisible(true);

    }
}
