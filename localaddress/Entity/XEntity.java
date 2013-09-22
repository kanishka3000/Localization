/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package localaddress.Entity;
import java.util.*;
/**
 *
 * @author kanishka
 */
public interface XEntity {
public Map<String,String>getAttributes();
public XEntity getInstance();
public XEntity getInstance(Map<String,String>map);
public String getId();
public void saveContact();
}
