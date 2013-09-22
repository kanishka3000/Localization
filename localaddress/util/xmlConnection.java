/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package localaddress.util;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerConfigurationException;
import localaddress.Entity.*;

import java.io.File;
import java.util.ArrayList;
import javax.naming.spi.DirStateFactory.Result;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

/**
 *
 * @author kanishka
 */
public class xmlConnection {

    private String[] attributes;
    private String Root;
    private File datafile;
    private Document doc;
    private String dtd;

    public xmlConnection(String root, String type) throws Exception {
        //   this.attributes = att;
        this.Root = root;
        if (type.equals("Contacts")) {
            datafile = new File("../App/Contacts.xml");
            this.dtd = "../App/Contacts.dtd";

        } else {
            datafile = new File("../App/Diary.xml");
            this.dtd = "../App/Diary.dtd";
        }
        File file = this.datafile;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        this.doc = document;
        // this.datafile = file;

    }

    public void remove(XEntity xen) {
        try {
            Document d = this.doc;
            Element el = d.getElementById(xen.getId());
            System.out.println(xen.getId());
            el.getParentNode().removeChild(el);
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            Source src = new DOMSource(doc);
            javax.xml.transform.stream.StreamResult dest = new StreamResult(this.datafile);
            aTransformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, this.dtd);
            aTransformer.transform(src, dest); // transform without paramers will save it to the same file. s
        } catch (Exception ex) {
            Logger.getLogger(xmlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(XEntity xen) {

        try {
            /*the entity is removed and then new one is added*/
            Document d = this.doc;
            Element el = d.getElementById(xen.getId());
            System.out.println(xen.getId());
            el.getParentNode().removeChild(el);
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            Source src = new DOMSource(doc);
            javax.xml.transform.stream.StreamResult dest = new StreamResult(this.datafile);
            aTransformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, this.dtd);
            aTransformer.transform(src, dest); // transform without paramers will save it to the same file. s
            xen.saveContact();
        } catch (Exception ex) {
            Logger.getLogger(xmlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Document getRoot() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(datafile);
        return document;
    }

    public void saveEntity(Map<String, String> entities) throws Exception {
        /*
        the first and the second stores the root and the parent values
         */

        String root = entities.get("Root");
        String parent = entities.get("Parent");
        Set<String> keys = entities.keySet();
        Document d = this.doc;
        NodeList root1 = doc.getElementsByTagName(root);
        Element root2 = (Element) root1.item(0);
        Element contact = d.createElement(parent);
        contact.setAttribute("id", Double.toString(Math.random()));

        for (String s : keys) {
            /*now the fields of all the elements can be retrieved*/
            if (!(s.equals("Root") | s.equals("Parent"))) {
                Element i = d.createElement(s);
                String val = entities.get(s);
                Text t = doc.createTextNode(val);
                i.appendChild(t);
                contact.appendChild(i);
            }


        }
        root2.appendChild(contact);

        /*saving the result*/
        TransformerFactory tranFactory = TransformerFactory.newInstance();
        Transformer aTransformer = tranFactory.newTransformer();

        Source src = new DOMSource(doc);
        javax.xml.transform.stream.StreamResult dest = new StreamResult(this.datafile);
        aTransformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, this.dtd);
        aTransformer.transform(src, dest);// transform without paramers will save it to the same file. s


    }

    public Contacts[] getContacts() {






        return null;
    }

    public List<XEntity> fillXML(XEntity en) {

        List<XEntity> xen = new ArrayList<XEntity>();
        try {
            File file = this.datafile;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            System.out.println("Root element " + document.getDocumentElement().getNodeName());
            NodeList node = document.getElementsByTagName(this.Root);
            System.out.println(this.Root);
            /*
            this will send an array list with map objects
             */
            System.out.println(node.getLength() + "contacts detected........");
            Map<String, String> att = en.getAttributes();/*get the attributes map*/

            for (int i = 0; i < node.getLength(); i++) {
                Node firstNode = node.item(i);

                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) firstNode;
                    String id = element.getAttribute("id");
                    Map<String, String> attmap = new HashMap();
                    attmap.put("ID", id);
                    for (String at : att.keySet()) {
                        String val = att.get(at);
                        NodeList firstNameElemntList = element.getElementsByTagName(val);
                        String s = this.getValue(firstNameElemntList, i);
                        attmap.put(at, s);
                    }
                    XEntity ent = en.getInstance(attmap);
                    xen.add(ent);


                }
            }








        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(xen.size() + "returned");
        return xen;

    }

    public String getValue(NodeList firstNameElemntList, int it) {
        Element firstNameElement = (Element) firstNameElemntList.item(0);
        NodeList firstName = firstNameElement.getChildNodes();
        String s = ((Node) firstName.item(0)).getNodeValue();
        return s;
    }

    public void transform() {
    }
}
    
    
    
    
    

